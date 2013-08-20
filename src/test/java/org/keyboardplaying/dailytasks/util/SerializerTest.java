package org.keyboardplaying.dailytasks.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.UUID;

import org.junit.Test;
import org.keyboardplaying.dailytasks.model.DailyTask;
import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.model.TaskSet;

/**
 * Tests the {@link Serialization} class on the objects which will be serialised
 * when running the application.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class SerializerTest {

	/** Tests serialization and deserialization of {@link Task}. */
	@Test
	public void testTaskSerialization() {
		Task original = new Task("Some label", true);
		byte[] serialized = Serializer.serialize(original);
		Task deserialized = Serializer.deserialize(serialized);

		// Do not test for object equality: original and deserialized will not
		// have the same ID.
		// Make sure label and state are fine, though.
		assertEquals(original.getTodo(), deserialized.getTodo());
		assertEquals(original.isDone(), deserialized.isDone());
	}

	/** Tests serialization and deserialization of dailies. */
	@Test
	public void testDailySerialization() {
		DailyTask original = new DailyTask("Some label", false);
		byte[] serialized = Serializer.serialize(original);
		DailyTask deserialized = Serializer.deserialize(serialized);

		// Do not test for object equality: original and deserialized will not
		// have the same ID.
		// Make sure label and state are fine, though.
		assertEquals(original.getTodo(), deserialized.getTodo());
		// deserialized's done should always be false.
		assertFalse(original.isDone());
		assertFalse(deserialized.isDone());

		original.setDone(true);
		serialized = Serializer.serialize(original);
		deserialized = Serializer.deserialize(serialized);

		// Do not test for object equality: original and deserialized will not
		// have the same ID.
		// Make sure label and state are fine, though.
		assertEquals(original.getTodo(), deserialized.getTodo());
		// deserialized's done should always be false.
		assertTrue(original.isDone());
		assertFalse(deserialized.isDone());
	}

	/** Tests serialization and deserialization of {@link Task}. */
	@Test
	public void testTaskSetSerialization() {
		// Some randomization to make things even better...
		TaskSet original = new TaskSet();
		int nbTasks = (int) (20 * Math.random()) + 1;
		for (int i = 0; i < nbTasks; i++) {
			Task randomTask = new Task(UUID.randomUUID().toString(),
					Math.random() < 0.5 ? false : true);
			original.addTask(randomTask);
		}

		byte[] serialized = Serializer.serialize(original);

		TaskSet result = Serializer.deserialize(serialized);

		assertEquals(original.size(), result.size());
		// Ensure all tasks have been serialized.
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
