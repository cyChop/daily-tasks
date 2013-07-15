package org.keyboardplaying.dailytasks.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.rmi.UnexpectedException;
import java.util.Iterator;
import java.util.UUID;

import org.junit.Test;
import org.keyboardplaying.dailytasks.util.Serializer;

/**
 * Tests the {@link TaskSet} class, along with the {@link Serializer} methods.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class TaskSetTest {

	@Test
	public void testSetManipulation() throws UnexpectedException {
		Task task1 = new Task("Common label", true);
		Task task2 = new Task("Common label", false);
		Task task3 = new Task("Different label", false);

		TaskSet set = new TaskSet();
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());

		// Add first task.
		set.addTask(task1);
		assertFalse(set.isEmpty());
		assertEquals(1, set.size());
		assertTrue(isTaskDone(set, task1));

		// Try to update an absent task.
		try {
			set.updateTaskState(task3);
			fail();
		} catch (Exception e) {
			// TODO make a catch selective for the expected exceptions
			// this is expected
		}

		// Add an equal task > no change.
		set.addTask(task2);
		assertEquals(1, set.size());
		assertTrue(isTaskDone(set, task1));

		// Add another task.
		set.addTask(task3);
		assertEquals(2, set.size());

		// Update task.
		assertFalse(isTaskDone(set, task3));
		set.updateTaskState(task3, true);
		assertTrue(isTaskDone(set, task3));

		// Remove a task.
		assertEquals(2, set.size());
		set.removeTask(task3);
		assertEquals(1, set.size());
	}

	/**
	 * Checks a task state in a set.
	 * 
	 * @param set
	 *            the set to search upon
	 * @param task
	 *            the task to look for
	 * @return the task {@link Task#isDone()} method result
	 * @throws UnexpectedException
	 *             when the task is not found in the set
	 */
	public boolean isTaskDone(TaskSet set, Task task)
			throws UnexpectedException {
		for (Task taskInSet : set) {
			if (taskInSet.equals(task)) {
				return taskInSet.isDone();
			}
		}
		throw new UnexpectedException(
				"The test class was poorly coded and an unexpected case arised.");
	}

	@Test
	public void testTaskSetSerialization() {
		// Some randomization to make things even better...
		TaskSet original = new TaskSet();
		int nbTasks = (int) (100 * Math.random()) + 1;
		for (int i = 0; i < nbTasks; i++) {
			Task randomTask = new Task(UUID.randomUUID().toString(),
					Math.random() < 0.5 ? false : true);
			original.addTask(randomTask);
		}

		byte[] serialized = Serializer.serialize(original);

		TaskSet result = Serializer.deserialize(serialized);

		assertEquals(original.size(), result.size());
		Iterator<Task> iterL = original.iterator();
		Iterator<Task> iterR = result.iterator();
		while (iterL.hasNext()) {
			Task left = iterL.next();
			Task right = iterR.next();
			assertEquals(left.getTodo(), right.getTodo());
			assertEquals(left.isDone(), right.isDone());
		}
	}
}
