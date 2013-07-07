package org.keyboardplaying.dailytasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.keyboardplaying.dailytasks.properties.Message;
import org.keyboardplaying.dailytasks.properties.TaskProperties;
import org.keyboardplaying.dailytasks.ui.Theme;

/**
 * Tests the parsing of the properties file.
 * 
 * @author cyChop (http://keyboardplaying.org)
 */
public class TaskPropertiesTest {

	/** Tests for the behavior in case the file is absent. */
	@Test
	public void testNoFile() {
		TaskProperties prop = new TaskProperties("dummy.properties");

		assertEquals(1, prop.getMessages().size());
		assertTrue(prop.getMessages().contains(Message.ERROR_READING_FILE));
	}

	/** Tests for the behaviour in the case of a correct file. */
	@Test
	public void testCorrectFile() {
		TaskProperties prop = new TaskProperties(this.getClass()
				.getResourceAsStream("tasks.properties"));

		assertEquals(0, prop.getMessages().size());
		assertFalse(prop.isAlwaysOnTop());
		assertEquals(Theme.LIGHT, prop.getTheme());
		assertEquals(3, prop.getTasks().length);
		// make sure tasks are ordered
		assertEquals("Task 1", prop.getTasks()[0]);
		assertEquals("Task 2", prop.getTasks()[1]);
		assertEquals("Task 3", prop.getTasks()[2]);
	}

	/**
	 * Tests for the behaviour in case onTop and theme have not been specified,
	 * or have been incorrectly specified.
	 */
	@Test
	public void testDefaultProperties() {
		TaskProperties prop1 = new TaskProperties(this.getClass()
				.getResourceAsStream("tasks-defaults1.properties"));
		assertsDefaultProperties(prop1);

		TaskProperties prop2 = new TaskProperties(this.getClass()
				.getResourceAsStream("tasks-defaults2.properties"));
		assertsDefaultProperties(prop2);
	}

	private void assertsDefaultProperties(TaskProperties prop) {
		assertEquals(2, prop.getMessages().size());
		assertTrue(prop.getMessages().contains(Message.UNSPECIFIED_ONTOP));
		assertTrue(prop.isAlwaysOnTop());
		assertTrue(prop.getMessages().contains(Message.UNSPECIFIED_THEME));
		assertEquals(Theme.GRAY, prop.getTheme());
	}

	/** Tests for the behaviour when the tasks could not be parsed. */
	@Test
	public void testIncorrectFile() {
		TaskProperties prop = new TaskProperties(this.getClass()
				.getResourceAsStream("tasks-errors.properties"));

		assertEquals(1, prop.getMessages().size());
		assertTrue(prop.getMessages().contains(Message.ERROR_READING_TASKS));
	}
}
