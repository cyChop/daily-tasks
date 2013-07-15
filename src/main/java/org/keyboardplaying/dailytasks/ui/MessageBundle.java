package org.keyboardplaying.dailytasks.ui;

import java.util.Locale;
import java.util.ResourceBundle;

import org.keyboardplaying.dailytasks.preferences.AppPreferences;

/**
 * Utility class for the management of localized messages.
 * <p/>
 * This classes uses the system locale as a default, but another locale can be
 * specified at any moment.
 * <p/>
 * Only static methods are exposed, but the class actually uses a Singleton
 * design-pattern, and all static methods refer to {@code protected} instance
 * methods which can be overridden or mocked for the purpose of unit-testing.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class MessageBundle {

	/** The base name of the message bundle. */
	private static final String BUNDLE_BASE_NAME = "org.keyboardplaying.dailytasks.ui.messages.MessageBundle";

	/** The unique instance of this object, using the Singleton design-pattern. */
	private static MessageBundle instance;

	/** The actual message {@link ResourceBundle}. */
	private ResourceBundle bundle;

	/**
	 * Returns the single instance of this object, after creating if not
	 * initialized yet.
	 * 
	 * @return the single instance of this object
	 */
	protected synchronized static MessageBundle getInstance() {
		if (instance == null) {
			instance = new MessageBundle();
		}
		return instance;
	}

	/** Creates a new instance, loading the preferred locale. */
	protected MessageBundle() {
		setInstanceLocale(AppPreferences.getLocale());
	}

	/**
	 * Refreshes the locale from the preferences
	 */
	public static void refreshLocale() {
		getInstance().setInstanceLocale(AppPreferences.getLocale());
	}

	/**
	 * Reloads the bundle to match the specified locale.
	 * 
	 * @param locale
	 *            the locale to use
	 */
	protected void setInstanceLocale(Locale locale) {
		bundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME, locale);
	}

	/**
	 * Returns the {@link String} for the specified key.
	 * 
	 * @param key
	 *            the key for the message
	 * @return the message for the supplied key
	 */
	public static String get(String key) {
		return getInstance().getString(key);
	}

	/**
	 * Returns the {@link String} for the specified key.
	 * 
	 * @param key
	 *            the key for the message
	 * @return the message for the supplied key
	 */
	protected String getString(String key) {
		return bundle.getString(key);
	}
}
