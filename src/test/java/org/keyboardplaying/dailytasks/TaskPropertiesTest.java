package org.keyboardplaying.dailytasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.keyboardplaying.dailytasks.ui.Theme;

/** @author cyChop (http://keyboardplaying.org) */
public class TaskPropertiesTest {

	@Test
	public void testIncorrectFile() {
		TaskProperties prop = new TaskProperties("dummy.properties");
		assertNotNull(prop.getErrorMessage());
	}

	// @Test
	public void testCorrectFile() {
		// can't get to load the test file
		// will have to come back to that
		TaskProperties prop = new TaskProperties("tasks.properties");
		assertNull(prop.getErrorMessage());
		assertEquals(false, prop.isAlwaysOnTop());
		assertEquals(Theme.LIGHT, prop.getTheme());
		assertEquals(3, prop.getTasks());
		assertEquals("Task 1", prop.getTasks()[0]);
	}
}
