/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.keyboardplaying.dailytasks.messages;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.keyboardplaying.dailytasks.ui.theme.Theme;

/**
 * Utility class for the management of localized messages.
 * <p/>
 * This classes uses the system locale as a default, but another locale can be
 * specified at any moment.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class MessageBundle {

	/** The base name of the message bundle. */
	private static final String BUNDLE_BASE_NAME = "org.keyboardplaying.dailytasks.ui.messages.MessageBundle";
	/** The locale of the default message bundle. */
	private static final String DEFAULT_LANG = "en";

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
		setInstanceLocale(Locale.getDefault());
	}

	/**
	 * Reloads the bundle to match the specified locale.
	 * 
	 * @param locale
	 *            the locale to use
	 */
	public static void setLocale(Locale locale) {
		getInstance().setInstanceLocale(locale);
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
	 * Returns the {@link String} for the specified key and includes the
	 * supplied arguments into the pattern.
	 * 
	 * @param key
	 *            the key for the message
	 * @param args
	 *            the arguments to include inside the message pattern retrieved
	 *            from the bundle
	 * @return the message for the supplied key and arguments
	 */
	public static String get(String key, Object... args) {
		return getInstance().getString(key, args);
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
	 * Returns the localized name of a {@link Theme}.
	 * 
	 * @param theme
	 *            the theme
	 * @return the name of the theme
	 */
	public static String get(Theme theme) {
		return getInstance().getString("theme." + theme.name());
	}

	/**
	 * Returns the {@link String} for the specified key and includes the
	 * supplied arguments into the pattern.
	 * 
	 * @param key
	 *            the key for the message
	 * @param args
	 *            the arguments to include inside the message pattern retrieved
	 *            from the bundle
	 * @return the message for the supplied key and arguments
	 */
	protected String getString(String key, Object... args) {
		if (args == null || args.length == 0) {
			// MessageFormat.format is time-consuming
			// Skip it if no use
			return getString(key);
		}
		return MessageFormat.format(getString(key), args);
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

	/**
	 * Returns a list of all locales a bundle has been written for.
	 * 
	 * @return a list of all locales availables within the application
	 */
	public static List<Locale> getAvailableLocales() {

		/* Convert base name to a path. */
		String bundlePath = BUNDLE_BASE_NAME.replaceAll("\\.", "/")
				+ "_%s.properties";

		List<Locale> available = new ArrayList<Locale>();

		/* Loop over all locales to find those we do support. */
		// Get a list of all locales the JVM supports
		Locale[] locales = Locale.getAvailableLocales();
		// Loop over those to find available locales
		for (Locale locale : locales) {
			URL bundle = ClassLoader.getSystemResource(String.format(
					bundlePath, locale.toString()));
			if (bundle != null) {
				available.add(locale);
			}
		}

		/* Return the list. */
		return available;
	}

	/**
	 * Returns the closest applicable locale.
	 * <p/>
	 * If the supplied locale cannot be found in available locales, the closer
	 * variation should be returned. If no variation corresponds to that
	 * language, the locale of the default bundle should be returned.
	 * 
	 * @param locale
	 *            the locale being searched for
	 * @return the closest applicable locale
	 */
	public static Locale getClosestApplicableLocale(Locale locale) {
		// List available locales
		List<Locale> available = getAvailableLocales();

		// Currently loaded locale
		Locale current = locale;
		if (!available.contains(current)) {
			// Current locale is not available
			// Keep only language
			current = new Locale(current.getLanguage());
			if (!available.contains(current)) {
				// Not available
				// Return default
				current = new Locale(DEFAULT_LANG);
			}
		}

		return current;
	}
}
