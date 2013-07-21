package org.keyboardplaying.dailytasks.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.keyboardplaying.dailytasks.messages.Message;

/**
 * Tests the parsing of the properties file.
 * 
 * @author cyChop (http://keyboardplaying.org)
 */
public class TaskPropertiesTest {

	/** Tests for the behavior in case the file is absent. */
	@Test
	@SuppressWarnings("deprecation")
	public void testNoFile() {
		TaskProperties prop = new TaskProperties("dummy.properties");

		assertEquals(1, prop.getMessages().size());
		assertTrue(prop.getMessages().contains(Message.ERROR_READING_FILE));
	}

	/** Tests for the behavior in the case of a correct file. */
	@Test
	@SuppressWarnings("deprecation")
	public void testCorrectFile() {
		TaskProperties prop = new TaskProperties(this.getClass()
				.getResourceAsStream("tasks.properties"));

		assertEquals(0, prop.getMessages().size());
		assertEquals(3, prop.getTasks().length);
		// make sure tasks are ordered
		assertEquals("Task 1", prop.getTasks()[0]);
		assertEquals("Task 2", prop.getTasks()[1]);
		assertEquals("Task 3", prop.getTasks()[2]);
	}

	/** Tests for the behaviour when the tasks could not be parsed. */
	@Test
	@SuppressWarnings("deprecation")
	public void testIncorrectFile() {
		TaskProperties prop = new TaskProperties(this.getClass()
				.getResourceAsStream("tasks-errors.properties"));

		assertEquals(1, prop.getMessages().size());
		assertTrue(prop.getMessages().contains(Message.ERROR_READING_TASKS));
	}
}
