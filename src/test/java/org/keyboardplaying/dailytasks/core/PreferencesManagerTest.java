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
package org.keyboardplaying.dailytasks.core;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.keyboardplaying.dailytasks.core.managers.PreferencesManager;
import org.keyboardplaying.dailytasks.core.managers.TaskManager;
import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.model.TaskSet;
import org.keyboardplaying.dailytasks.model.UIPreferences;
import org.keyboardplaying.dailytasks.ui.theme.Theme;

import java.util.Iterator;
import java.util.Locale;
import java.util.prefs.BackingStoreException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Tests the {@link PreferencesManager} class.
 *
 * @author Cyrille Chopelet (https://keyboardplaying.org)
 */
@SuppressWarnings("javadoc")
@FixMethodOrder(MethodSorters.DEFAULT)
public class PreferencesManagerTest {

    /**
     * Clears the whole preferences once testing is done.
     */
    @AfterClass
    public static void staticClean() throws BackingStoreException {
        PreferencesManager.clear();
    }

    /**
     * Clears the whole preferences set before each test.
     */
    @Before
    public void clean() throws BackingStoreException {
        staticClean();
    }

    /**
     * Tests the locale setting.
     */
    @Test
    public void testLocale() {
        // Test default value
        assertEquals(Locale.getDefault(), PreferencesManager.getLocale());

        // Test saving and retrieving
        PreferencesManager.setLocale("fr");
        assertEquals(new Locale("fr"), PreferencesManager.getLocale());
        PreferencesManager.setLocale("en");
        assertEquals(new Locale("en"), PreferencesManager.getLocale());
        PreferencesManager.setLocale("fr", "FR");
        assertEquals(new Locale("fr", "FR"), PreferencesManager.getLocale());
        PreferencesManager.setLocale("en", "GB");
        assertEquals(new Locale("en", "GB"), PreferencesManager.getLocale());
        // try removing the country
        PreferencesManager.setLocale("en");
        assertEquals(new Locale("en"), PreferencesManager.getLocale());
    }

    /**
     * Tests the theme setting.
     */
    @Test
    public void testTheme() {
        // Test default value
        assertSame(Theme.ORANGE, PreferencesManager.getTheme());

        // Test saving and retrieving
        PreferencesManager.setTheme(Theme.DARK);
        assertSame(Theme.DARK, PreferencesManager.getTheme());
    }

    /**
     * Tests the on top setting.
     */
    @Test
    public void testOnTop() {
        // Test default value
        assertTrue(PreferencesManager.isAlwaysOnTop());

        // Test saving an retrieving
        PreferencesManager.setAlwaysOnTop(false);
        assertFalse(PreferencesManager.isAlwaysOnTop());
        PreferencesManager.setAlwaysOnTop(true);
        assertTrue(PreferencesManager.isAlwaysOnTop());
    }

    /**
     * Tests the preferences manipulation using the {@link UIPreferences} object.
     */
    @Test
    public void testUIPreferences() {
        UIPreferences prefs = PreferencesManager.getUIPreferences();

        /* Update preferences. */
        prefs.setLocale(new Locale("fr", "CH"));
        prefs.setTheme(Theme.LIGHT);
        prefs.setAlwaysOnTop(false);
        PreferencesManager.setUIPreferences(prefs);

        /* Ensure properties are saved properly. */
        assertEquals(new Locale("fr", "CH"), PreferencesManager.getLocale());
        assertSame(Theme.LIGHT, PreferencesManager.getTheme());
        assertFalse(PreferencesManager.isAlwaysOnTop());
    }

    /**
     * Tests the tasks
     */
    @Test
    public void testTasks() {
        // Test default
        assertDefaultTaskSet(PreferencesManager.getTasks());

        // Test saving and retrieving
        // - An empty task set shoudl return default task set
        PreferencesManager.setTasks(new TaskSet());
        assertDefaultTaskSet(PreferencesManager.getTasks());
        // - A non-empty task set
        TaskSet tasks = new TaskSet();
        tasks.addTask(new Task("Task"));
        PreferencesManager.setTasks(tasks);
        tasks = PreferencesManager.getTasks();
        assertEquals(1, tasks.size());
        Iterator<Task> iter = tasks.iterator();
        assertEquals("Task", iter.next().getTodo());
        assertFalse(iter.hasNext());
        // - Ensure order and state are preserved
        tasks = new TaskSet();
        tasks.addTask(new Task("Task 1", true));
        tasks.addTask(new Task("Task 2", false));
        PreferencesManager.setTasks(tasks);
        tasks = PreferencesManager.getTasks();
        assertEquals(2, tasks.size());
        iter = tasks.iterator();
        Task task = iter.next();
        assertEquals("Task 1", task.getTodo());
        assertTrue(task.isDone());
        task = iter.next();
        assertEquals("Task 2", task.getTodo());
        assertFalse(task.isDone());
        assertFalse(iter.hasNext());
    }

    /**
     * Asserts a {@link TaskSet} is the default task set.
     *
     * @param tasks the task set to test
     * @see TaskManager#getDefaultTaskSet()
     */
    private static void assertDefaultTaskSet(TaskSet tasks) {
        assertEquals(1, tasks.size());
        Iterator<Task> iter = tasks.iterator();
        assertEquals(MessageBundle.get("task.default"), iter.next().getTodo());
    }
}
