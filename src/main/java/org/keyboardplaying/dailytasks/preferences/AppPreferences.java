package org.keyboardplaying.dailytasks.preferences;

import java.util.Locale;
import java.util.prefs.Preferences;

import org.keyboardplaying.dailytasks.App;
import org.keyboardplaying.dailytasks.ui.Theme;

/**
 * Utility class for the management of preferences.
 * <p/>
 * This class is based on Java's implementation of {@link Preferences}.
 * <p/>
 * Only static methods are exposed, but the class actually uses a Singleton
 * design-pattern, and all static methods refer to {@code protected} instance
 * methods which can be overridden or mocked for the purpose of unit-testing.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class AppPreferences {

	/**
	 * A prefix to prepend to all fields, to avoid confusion with the
	 * preferences of another application.
	 */
	private static final String FLD_PREFIX = "daily-tasks.";
	/** The field storing the language to use. */
	private static final String FLD_LOCALE_LANG = FLD_PREFIX + "locale.lang";
	/** The field storing the country for the locale to use. */
	private static final String FLD_LOCALE_COUNTRY = FLD_PREFIX
			+ "locale.country";
	/**
	 * The field storing the preference specifying if the todo-list should
	 * remain on top of other windows.
	 */
	private static final String FLD_ON_TOP = FLD_PREFIX + "ontop";
	/** The field storing the selected theme. */
	private static final String FLD_THEME = FLD_PREFIX + "theme";

	/**
	 * The default preference specifying if the todo-list should remain on top
	 * of other windows.
	 */
	private static final boolean DEF_ON_TOP = true;
	/** The default theme if none is specified. */
	private static final Theme DEF_THEME = Theme.ORANGE;

	/** The unique instance of this object, using the Singleton design-pattern. */
	private static AppPreferences instance;

	/** The actual preferences for the application. */
	private Preferences prefs;

	/**
	 * Returns the single instance of this object, after creating if not
	 * initialized yet.
	 * 
	 * @return the single instance of this object
	 */
	protected synchronized static AppPreferences getInstance() {
		if (instance == null) {
			instance = new AppPreferences();
		}
		return instance;
	}

	/** Creates a new instance. */
	protected AppPreferences() {
		prefs = Preferences.userRoot().node(App.class.getName());
	}

	/**
	 * Returns the locale stored in the preferences, or the system's default
	 * locale if none.
	 * 
	 * @return the locale to use
	 */
	public static Locale getLocale() {
		return getInstance().fetchLocale();
	}

	/**
	 * Returns the locale stored in the preferences, or the system's default
	 * locale if none.
	 * 
	 * @return the locale to use
	 */
	protected Locale fetchLocale() {
		String lang = prefs.get(FLD_LOCALE_LANG, null);
		String country = prefs.get(FLD_LOCALE_COUNTRY, null);

		Locale locale;
		if (lang == null) {

			/* Locale has never been set. */
			// 1- initialize the preferences with system default
			// 2- return default
			locale = Locale.getDefault();
			prefs.put(FLD_LOCALE_LANG, locale.getLanguage());
			prefs.put(FLD_LOCALE_COUNTRY, locale.getCountry());

		} else {

			/* Use locale as stored in preferences. */
			locale = country == null ? new Locale(lang) : new Locale(lang,
					country);
		}

		return locale;
	}

	/**
	 * Sets the locale to use.
	 * 
	 * @param language
	 *            the locale's language
	 * @param country
	 *            the locale's country
	 */
	public static void setLocale(String language, String country) {
		getInstance().setInstanceLocale(language, country);
	}

	/**
	 * Sets the locale to use.
	 * 
	 * @param language
	 *            the locale's language
	 */
	public static void setLocale(String language) {
		setLocale(language, null);
	}

	/**
	 * Sets the locale to use.
	 * 
	 * @param language
	 *            the locale's language
	 * @param country
	 *            the locale's country
	 */
	protected void setInstanceLocale(String language, String country) {
		prefs.put(FLD_LOCALE_LANG, language);
		if (country == null) {
			prefs.remove(FLD_LOCALE_COUNTRY);
		} else {
			prefs.put(FLD_LOCALE_COUNTRY, country);
		}
	}

	/**
	 * Returns the theme stored as a preference, or the default theme if none.
	 * 
	 * @return the theme to use
	 */
	public static Theme getTheme() {
		return getInstance().getInstanceTheme();
	}

	/**
	 * Returns the theme stored as a preference, or the default theme if none.
	 * 
	 * @return the theme to use
	 */
	protected Theme getInstanceTheme() {
		String themeName = prefs.get(FLD_THEME, null);
		Theme theme;
		if (themeName == null) {
			// No theme chosen, use default
			theme = DEF_THEME;
		} else {
			try {
				theme = Theme.valueOf(themeName);
			} catch (IllegalArgumentException iae) {
				// The stored value is incorrect.
				// Remove and use default.
				prefs.remove(FLD_THEME);
				theme = DEF_THEME;
			}
		}
		return theme;
	}

	/**
	 * Sets the theme to use.
	 * 
	 * @param theme
	 *            the theme to use
	 */
	public static void setTheme(Theme theme) {
		getInstance().setInstanceTheme(theme);
	}

	/**
	 * Sets the theme to use.
	 * 
	 * @param theme
	 *            the theme to use
	 */
	protected void setInstanceTheme(Theme theme) {
		prefs.put(FLD_THEME, theme.name());
	}

	/**
	 * Specifies whether the to-do list should always remain on top of other
	 * windows.
	 * 
	 * @return {@code true} if to-do list should always remain on top of other
	 *         windows
	 */
	public static boolean isAlwaysOnTop() {
		return getInstance().isInstanceAlwaysOnTop();
	}

	/**
	 * Specifies whether the to-do list should always remain on top of other
	 * windows.
	 * 
	 * @return {@code true} if to-do list should always remain on top of other
	 *         windows
	 */
	protected boolean isInstanceAlwaysOnTop() {
		return prefs.getBoolean(FLD_ON_TOP, DEF_ON_TOP);
	}

	/**
	 * Specifies whether the to-do list should always remain on top of other
	 * windows.
	 * 
	 * @param alwaysOnTop
	 *            {@code true} if to-do list should always remain on top of
	 *            other windows
	 */
	public static void setAlwaysOnTop(boolean alwaysOnTop) {
		getInstance().setInstanceAlwaysOnTop(alwaysOnTop);
	}

	/**
	 * Specifies whether the to-do list should always remain on top of other
	 * windows.
	 * 
	 * @param alwaysOnTop
	 *            {@code true} if to-do list should always remain on top of
	 *            other windows
	 */
	protected void setInstanceAlwaysOnTop(boolean alwaysOnTop) {
		prefs.putBoolean(FLD_ON_TOP, alwaysOnTop);
	}
}