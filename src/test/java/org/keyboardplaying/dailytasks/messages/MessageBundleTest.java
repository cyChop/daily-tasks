package org.keyboardplaying.dailytasks.messages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.keyboardplaying.dailytasks.preferences.AppPreferences;
import org.keyboardplaying.dailytasks.ui.MessageBundle;

/**
 * Test class for {@link MessageBundle}.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class MessageBundleTest {

	/** The title field, used for locale testing. */
	private static final String TITLE = "title";
	/** French value for the title. */
	private static final String TITLE_FR = "Tâches";
	/** English value for the title. */
	private static final String TITLE_EN = "Tasks";

	/** Tests the switching of locale with or without a country specified. */
	@Test
	public void testLocaleSwitching() {
		// Do not assert content on this level, you cannot know which locale the
		// testing system has loaded.
		assertNotNull(MessageBundle.get(TITLE));

		/* Test the various locale switching. */
		// Assume the testing machine has an English locale (this should be more
		// widespread than French-localed testing machines)
		AppPreferences.setLocale("fr", "CA");
		MessageBundle.refreshLocale();
		assertEquals(TITLE_FR, MessageBundle.get(TITLE));
		AppPreferences.setLocale("en", "US");
		// Should not work without a refresh.
		assertEquals(TITLE_FR, MessageBundle.get(TITLE));
		MessageBundle.refreshLocale();
		assertEquals(TITLE_EN, MessageBundle.get(TITLE));
		AppPreferences.setLocale("fr");
		MessageBundle.refreshLocale();
		assertEquals(TITLE_FR, MessageBundle.get(TITLE));
		AppPreferences.setLocale("en");
		MessageBundle.refreshLocale();
		assertEquals(TITLE_EN, MessageBundle.get(TITLE));

		/* Test with some extravagant cases, just to make sure. */
		AppPreferences.setLocale("fR", "Fr");
		MessageBundle.refreshLocale();
		assertEquals(TITLE_FR, MessageBundle.get(TITLE));
		AppPreferences.setLocale("EN", "uK");
		MessageBundle.refreshLocale();
		assertEquals(TITLE_EN, MessageBundle.get(TITLE));
		AppPreferences.setLocale("FR");
		MessageBundle.refreshLocale();
		assertEquals(TITLE_FR, MessageBundle.get(TITLE));
		AppPreferences.setLocale("En");
		MessageBundle.refreshLocale();
		assertEquals(TITLE_EN, MessageBundle.get(TITLE));
	}
}
