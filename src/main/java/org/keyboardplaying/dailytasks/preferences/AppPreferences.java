package org.keyboardplaying.dailytasks.preferences;

import java.util.Locale;
import java.util.prefs.Preferences;

import org.keyboardplaying.dailytasks.App;
import org.keyboardplaying.dailytasks.ui.Theme;

// XXX JAVADOC
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

	private static final String FLD_PREFIX = "daily-tasks.";
	private static final String FLD_LOCALE_LANG = FLD_PREFIX + "locale.lang";
	private static final String FLD_LOCALE_COUNTRY = FLD_PREFIX
			+ "locale.country";
	private static final String FLD_ON_TOP = FLD_PREFIX + "ontop";
	private static final String FLD_THEME = FLD_PREFIX + "theme";

	private static final boolean DEF_ON_TOP = true;
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

	public static Locale getLocale() {
		return getInstance().fetchLocale();
	}

	public static void setLocale(String language, String country) {
		getInstance().setInstanceLocale(language, country);
	}

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

	public static void setLocale(String language) {
		setLocale(language, null);
	}

	protected void setInstanceLocale(String language, String country) {
		prefs.put(FLD_LOCALE_LANG, language);
		if (country == null) {
			prefs.remove(FLD_LOCALE_COUNTRY);
		} else {
			prefs.put(FLD_LOCALE_COUNTRY, country);
		}
	}

	public static Theme getTheme() {
		return getInstance().getInstanceTheme();
	}

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

	public static void setTheme(Theme theme) {
		getInstance().setInstanceTheme(theme);
	}

	protected void setInstanceTheme(Theme theme) {
		prefs.put(FLD_THEME, theme.name());
	}

	public static boolean isAlwaysOnTop() {
		return getInstance().isInstanceAlwaysOnTop();
	}

	protected boolean isInstanceAlwaysOnTop() {
		return prefs.getBoolean(FLD_ON_TOP, DEF_ON_TOP);
	}

	public static void setAlwaysOnTop(boolean alwaysOnTop) {
		getInstance().setInstanceAlwaysOnTop(alwaysOnTop);
	}

	protected void setInstanceAlwaysOnTop(boolean alwaysOnTop) {
		prefs.putBoolean(FLD_ON_TOP, alwaysOnTop);
	}
}
