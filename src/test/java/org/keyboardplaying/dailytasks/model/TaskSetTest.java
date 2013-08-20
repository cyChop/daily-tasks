/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.keyboardplaying.dailytasks.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.rmi.UnexpectedException;

import org.junit.Test;

/**
 * Tests the {@link TaskSet} class.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class TaskSetTest {

	@Test
	public void testSetManipulation() throws UnexpectedException {
		Task task1 = new Task("Common label", true);
		Task task2 = new Task("Common label", true);
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
		task1.setDone(false);
		set.addTask(task1);
		assertEquals(1, set.size());
		// state has changed has the first version was overwritten
		assertFalse(isTaskDone(set, task1));

		// Can have two tasks with the same label and state, does not make them
		// equal.
		set.addTask(task2);
		assertEquals(2, set.size());
		assertTrue(isTaskDone(set, task2));

		// Add another task.
		set.addTask(task3);
		assertEquals(3, set.size());

		// Update task.
		assertFalse(isTaskDone(set, task3));
		set.updateTaskState(task3.getId(), true);
		assertTrue(isTaskDone(set, task3));

		// Remove a task.
		assertEquals(3, set.size());
		set.removeTask(task3);
		assertEquals(2, set.size());
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
}
