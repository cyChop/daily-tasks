package org.keyboardplaying.dailytasks.preferences;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Locale;
import java.util.prefs.BackingStoreException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.keyboardplaying.dailytasks.core.TaskManager;
import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.model.TaskSet;
import org.keyboardplaying.dailytasks.ui.Theme;

/**
 * Tests the {@link AppPreferences} class.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class AppPreferencesTest {

	/** Clears the whole preferences set before and after testing. */
	@BeforeClass
	@AfterClass
	public static void cleaning() throws BackingStoreException {
		AppPreferences.clear();
	}

	/** Tests the locale setting. */
	@Test
	public void testLocale() {
		// Test default value
		assertEquals(Locale.getDefault(), AppPreferences.getLocale());

		// Test saving and retrieving
		AppPreferences.setLocale("fr");
		assertEquals(new Locale("fr"), AppPreferences.getLocale());
		AppPreferences.setLocale("en");
		assertEquals(new Locale("en"), AppPreferences.getLocale());
		AppPreferences.setLocale("fr", "FR");
		assertEquals(new Locale("fr", "FR"), AppPreferences.getLocale());
		AppPreferences.setLocale("en", "GB");
		assertEquals(new Locale("en", "GB"), AppPreferences.getLocale());
		// try removing the country
		AppPreferences.setLocale("en");
		assertEquals(new Locale("en"), AppPreferences.getLocale());
	}

	/** Tests the theme setting. */
	@Test
	public void testTheme() {
		// Test default value
		assertSame(Theme.ORANGE, AppPreferences.getTheme());

		// Test saving and retrieving
		AppPreferences.setTheme(Theme.DARK);
		assertSame(Theme.DARK, AppPreferences.getTheme());
	}

	/** Tests the on top setting. */
	@Test
	public void testOnTop() {
		// Test default value
		assertTrue(AppPreferences.isAlwaysOnTop());

		// Test saving an retrieving
		AppPreferences.setAlwaysOnTop(false);
		assertFalse(AppPreferences.isAlwaysOnTop());
		AppPreferences.setAlwaysOnTop(true);
		assertTrue(AppPreferences.isAlwaysOnTop());
	}

	/** Tests the tasks */
	@Test
	public void testTasks() {
		// Test default
		assertDefaultTaskSet(AppPreferences.getTasks());

		// Test saving and retrieving
		// - An empty task set shoudl return default task set
		AppPreferences.setTasks(new TaskSet());
		assertDefaultTaskSet(AppPreferences.getTasks());
		// - A non-empty task set
		TaskSet tasks = new TaskSet();
		tasks.addTask(new Task("Task"));
		AppPreferences.setTasks(tasks);
		tasks = AppPreferences.getTasks();
		assertEquals(1, tasks.size());
		Iterator<Task> iter = tasks.iterator();
		assertEquals("Task", iter.next().getTodo());
		assertFalse(iter.hasNext());
		// - Ensure order and state are preserved
		tasks = new TaskSet();
		tasks.addTask(new Task("Task 1", true));
		tasks.addTask(new Task("Task 2", false));
		AppPreferences.setTasks(tasks);
		tasks = AppPreferences.getTasks();
		assertEquals(2, tasks.size());
		iter = tasks.iterator();
		Task task = iter.next();
		assertEquals("Task 1", task.getTodo());
		assertTrue(task.isDone());
		task = iter.next();
		assertEquals("Task 2", task.getTodo());
		assertFalse(task.isDone());
		assertFalse(iter.hasNext());
	}

	/**
	 * Asserts a {@link TaskSet} is the default task set.
	 * 
	 * @param tasks
	 *            the task set to test
	 * 
	 * @see TaskManager#getDefault()
	 */
	private static void assertDefaultTaskSet(TaskSet tasks) {
		assertEquals(1, tasks.size());
		Iterator<Task> iter = tasks.iterator();
		assertEquals(MessageBundle.get("task.default"), iter.next().getTodo());
	}
}
