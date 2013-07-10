package org.keyboardplaying.dailytasks.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;
import org.keyboardplaying.dailytasks.messages.Message;
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
		assertNull(prop.getLocale());
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
	 * <p/>
	 * Incidently also tests for the message level setting.
	 */
	@Test
	public void testDefaultProperties() {
		/* Testing when not specified. */
		TaskProperties prop1 = new TaskProperties(this.getClass()
				.getResourceAsStream("tasks-defaults1.properties"));
		assertEquals(2, prop1.getMessages().size());
		assertTrue(prop1.getMessages().contains(Message.UNSPECIFIED_ONTOP));
		assertTrue(prop1.isAlwaysOnTop());
		assertTrue(prop1.getMessages().contains(Message.UNSPECIFIED_THEME));
		assertEquals(Theme.GRAY, prop1.getTheme());

		/* testing when specified incorrectly. */
		TaskProperties prop2 = new TaskProperties(this.getClass()
				.getResourceAsStream("tasks-defaults2.properties"));
		assertTrue(prop2.getMessages().contains(Message.INCORRECT_MSG_LVL));
		// onTop is false when not 'true', behaviour of Boolean.valueOf(String)
		assertEquals(2, prop2.getMessages().size());
		assertFalse(prop2.isAlwaysOnTop());
		assertTrue(prop2.getMessages().contains(Message.INCORRECT_THEME));
		assertEquals(Theme.GRAY, prop2.getTheme());
	}

	/** Locale-focused testing. */
	@Test
	public void testLocale() {
		assertLocale(null, "frc");
		assertLocale(new Locale("en"), "en_lower");
		assertLocale(new Locale("en"), "en_upper");
		assertLocale(new Locale("fr", "CA"), "fr_CA");
		assertLocale(new Locale("fr", "_A"), "fr__A");
	}

	/**
	 * Ensures the locale obtained from a specific properties file is the one
	 * expected for the specified configuration file.
	 * 
	 * @param locale
	 *            the expected locale
	 * @param localeFile
	 *            the configuration file identifier
	 */
	private void assertLocale(Locale locale, final String localeFile) {
		TaskProperties p = new TaskProperties(this.getClass()
				.getResourceAsStream(
						"tasks-locale-" + localeFile + ".properties"));
		assertEquals(locale, p.getLocale());
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
