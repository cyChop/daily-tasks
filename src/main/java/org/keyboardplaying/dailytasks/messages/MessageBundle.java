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

import java.text.MessageFormat;
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
}
