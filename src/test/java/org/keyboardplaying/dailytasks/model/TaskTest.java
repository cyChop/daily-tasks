package org.keyboardplaying.dailytasks.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.keyboardplaying.dailytasks.util.Serializer;

/**
 * Tests the {@link Task} class, along with the {@link Serializer} methods.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class TaskTest {

	@Test
	public void testTaskEquals() {
		Task task1 = new Task("Common label", true);
		Task task2 = new Task("Common label", false);
		Task task3 = new Task("Different label", false);

		assertEquals(task1, task2);
		assertFalse(task1.equals(task3));
		assertFalse(task2.equals(task3));
		assertFalse(task2.equals(null));
	}

	@Test
	public void testTaskSerialization() {
		Task original = new Task("Some label", true);
		byte[] serialized = Serializer.serialize(original);
		Task result = Serializer.deserialize(serialized);

		assertEquals(original.getTodo(), result.getTodo());
		assertEquals(original.isDone(), result.isDone());
	}
}
