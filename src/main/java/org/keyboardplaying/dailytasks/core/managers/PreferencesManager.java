/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.keyboardplaying.dailytasks.core.managers;

import java.util.Locale;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import org.keyboardplaying.dailytasks.exception.DeserializationException;
import org.keyboardplaying.dailytasks.exception.SerializationException;
import org.keyboardplaying.dailytasks.model.TaskSet;
import org.keyboardplaying.dailytasks.model.UIPreferences;
import org.keyboardplaying.dailytasks.ui.theme.Theme;
import org.keyboardplaying.dailytasks.util.ExceptionUtils;
import org.keyboardplaying.dailytasks.util.Serializer;

/**
 * Utility class for the management of preferences.
 * <p/>
 * This class is based on Java's implementation of {@link Preferences}.
 * <p/>
 * Only static methods are exposed, but the class actually uses a Singleton design-pattern, and all static methods refer
 * to {@code protected} instance methods which can be overridden or mocked for the purpose of unit-testing.
 *
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 */
public class PreferencesManager {

    /**
     * A prefix to prepend to all fields, to avoid confusion with the preferences of another application.
     */
    private static final String FLD_PREFIX = "daily-tasks.";
    /** The field storing the language to use. */
    private static final String FLD_LOCALE_LANG = FLD_PREFIX + "locale.lang";
    /** The field storing the country for the locale to use. */
    private static final String FLD_LOCALE_COUNTRY = FLD_PREFIX + "locale.country";
    /**
     * The field storing the preference specifying if the todo-list should remain on top of other windows.
     */
    private static final String FLD_ON_TOP = FLD_PREFIX + "ontop";
    /** The field storing the selected theme. */
    private static final String FLD_THEME = FLD_PREFIX + "theme";
    /** The field storing the tasks. */
    private static final String FLD_TASKS = FLD_PREFIX + "tasks";

    /**
     * The default preference specifying if the todo-list should remain on top of other windows.
     */
    private static final boolean DEF_ON_TOP = true;
    /** The default theme if none is specified. */
    private static final Theme DEF_THEME = Theme.ORANGE;

    /** The unique instance of this object, using the Singleton design-pattern. */
    private static PreferencesManager instance;

    /** The actual preferences for the application. */
    private Preferences prefs;

    /** Creates a new instance. */
    protected PreferencesManager() {
        prefs = Preferences.userRoot().node(getClass().getName());
    }

    /**
     * Returns the single instance of this object, after creating if not initialized yet.
     *
     * @return the single instance of this object
     */
    protected static synchronized PreferencesManager getInstance() {
        if (instance == null) {
            instance = new PreferencesManager();
        }
        return instance;
    }

    /**
     * Clears all the preferences which were stored on the running system.
     *
     * @throws BackingStoreException
     *             if this operation cannot be completed due to a failure in the backing store, or inability to
     *             communicate with it
     */
    public static void clear() throws BackingStoreException {
        getInstance().clearInstance();
    }

    /**
     * Clears all the preferences which were stored on the running system.
     *
     * @throws BackingStoreException
     *             if this operation cannot be completed due to a failure in the backing store, or inability to
     *             communicate with it
     */
    public void clearInstance() throws BackingStoreException {
        prefs.clear();
    }

    /**
     * Returns the locale stored in the preferences, or the system's default locale if none.
     *
     * @return the locale to use
     */
    public static Locale getLocale() {
        return getInstance().fetchLocale();
    }

    /**
     * Returns the locale stored in the preferences, or the system's default locale if none.
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
            locale = country == null ? new Locale(lang) : new Locale(lang, country);
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
     * Specifies whether the to-do list should always remain on top of other windows.
     *
     * @return {@code true} if to-do list should always remain on top of other windows
     */
    public static boolean isAlwaysOnTop() {
        return getInstance().isInstanceAlwaysOnTop();
    }

    /**
     * Specifies whether the to-do list should always remain on top of other windows.
     *
     * @return {@code true} if to-do list should always remain on top of other windows
     */
    protected boolean isInstanceAlwaysOnTop() {
        return prefs.getBoolean(FLD_ON_TOP, DEF_ON_TOP);
    }

    /**
     * Specifies whether the to-do list should always remain on top of other windows.
     *
     * @param alwaysOnTop
     *            {@code true} if to-do list should always remain on top of other windows
     */
    public static void setAlwaysOnTop(boolean alwaysOnTop) {
        getInstance().setInstanceAlwaysOnTop(alwaysOnTop);
    }

    /**
     * Specifies whether the to-do list should always remain on top of other windows.
     *
     * @param alwaysOnTop
     *            {@code true} if to-do list should always remain on top of other windows
     */
    protected void setInstanceAlwaysOnTop(boolean alwaysOnTop) {
        prefs.putBoolean(FLD_ON_TOP, alwaysOnTop);
    }

    /**
     * Returns the UI preferences packed as a single object.
     *
     * @return the UI preferences packed as a single object
     */
    public static UIPreferences getUIPreferences() {
        return new UIPreferences(getLocale(), getTheme(), isAlwaysOnTop());
    }

    /**
     * Saves the UI preferences from the supplied argument.
     *
     * @param prefs
     *            the UI preferences to save
     */
    public static void setUIPreferences(UIPreferences prefs) {
        setLocale(prefs.getLocale().getLanguage(), prefs.getLocale().getCountry());
        setTheme(prefs.getTheme());
        setAlwaysOnTop(prefs.isAlwaysOnTop());
    }

    /**
     * Returns the tasks stored as a preference, or the default set if none.
     *
     * @return the tasks
     *
     * @see TaskManager#getDefaultTaskSet()
     */
    public static TaskSet getTasks() {
        return getInstance().getInstanceTasks();
    }

    /**
     * Returns the tasks stored as a preference, or the default set if none.
     *
     * @return the tasks
     *
     * @see TaskManager#getDefaultTaskSet()
     */
    protected TaskSet getInstanceTasks() {
        TaskSet result;

        byte[] byteArray = prefs.getByteArray(FLD_TASKS, null);
        if (byteArray == null) {
            result = TaskManager.getDefaultTaskSet();
        } else {
            try {
                result = Serializer.deserialize(byteArray);
                if (result == null || result.isEmpty()) {
                    result = TaskManager.getDefaultTaskSet();
                }
            } catch (DeserializationException e) {
                result = null;
                // this should never happen, as the Serializer is used in a
                // controlled environment
                ExceptionUtils.handleUnexpectedException(this, e);
            }
        }

        return result;
    }

    /**
     * Stores the specified {@link TaskSet} as a preference.
     *
     * @param tasks
     *            the tasks to save
     */
    public static void setTasks(TaskSet tasks) {
        getInstance().setInstanceTasks(tasks);
    }

    /**
     * Stores the specified {@link TaskSet} as a preference.
     *
     * @param tasks
     *            the tasks to save
     */
    protected void setInstanceTasks(TaskSet tasks) {
        try {
            prefs.putByteArray(FLD_TASKS, Serializer.serialize(tasks));
        } catch (SerializationException e) {
            // this should never happen, as the Serializer is used in a
            // controlled environment
            ExceptionUtils.handleUnexpectedException(this, e);
        }
    }
}
