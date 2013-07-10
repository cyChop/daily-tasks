package org.keyboardplaying.dailytasks.ui;

import java.util.Locale;
import java.util.ResourceBundle;

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
	 * Returns the single instance of this object.
	 * 
	 * @return the single instance of this object
	 */
	protected synchronized static MessageBundle getInstance() {
		if (instance == null) {
			instance = new MessageBundle();
		}
		return instance;
	}

	/** Creates a new instance, loading the system locale. */
	protected MessageBundle() {
		setInstanceLocale(Locale.getDefault());
	}

	/**
	 * Loads messages for another locale.
	 * 
	 * @param locale
	 *            the locale to load
	 */
	public static void setLocale(Locale locale) {
		getInstance().setInstanceLocale(locale);
	}

	/**
	 * Loads messages for another locale.
	 * 
	 * @param language
	 *            the language to load
	 */
	public static void setLocale(String language) {
		setLocale(new Locale(language));
	}

	/**
	 * Loads messages for another locale.
	 * 
	 * @param language
	 *            the language to load
	 * @param country
	 *            the country specifics
	 */
	public static void setLocale(String language, String country) {
		setLocale(new Locale(language, country));
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
