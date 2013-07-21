package org.keyboardplaying.dailytasks.messages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;

/**
 * Test class for {@link MessageBundle}.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class MessageBundleTest {

	/** The title field, used for locale testing. */
	private static final String TITLE = "app.title";
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
		MessageBundle.setLocale(new Locale("fr", "CA"));
		assertEquals(TITLE_FR, MessageBundle.get(TITLE));
		MessageBundle.setLocale(new Locale("en", "US"));
		// Should not work without a refresh.
		assertEquals(TITLE_EN, MessageBundle.get(TITLE));
		MessageBundle.setLocale(new Locale("fr"));
		assertEquals(TITLE_FR, MessageBundle.get(TITLE));
		MessageBundle.setLocale(new Locale("en"));
		assertEquals(TITLE_EN, MessageBundle.get(TITLE));

		/* Test with some extravagant cases, just to make sure. */
		MessageBundle.setLocale(new Locale("fR", "Fr"));
		assertEquals(TITLE_FR, MessageBundle.get(TITLE));
		MessageBundle.setLocale(new Locale("EN", "uK"));
		assertEquals(TITLE_EN, MessageBundle.get(TITLE));
		MessageBundle.setLocale(new Locale("FR"));
		assertEquals(TITLE_FR, MessageBundle.get(TITLE));
		MessageBundle.setLocale(new Locale("En"));
		assertEquals(TITLE_EN, MessageBundle.get(TITLE));
	}
}
