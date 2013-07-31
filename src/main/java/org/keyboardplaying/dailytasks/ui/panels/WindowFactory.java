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
package org.keyboardplaying.dailytasks.ui.panels;

import java.awt.Window;

import org.keyboardplaying.dailytasks.model.TaskSet;
import org.keyboardplaying.dailytasks.model.UIPreferences;
import org.keyboardplaying.dailytasks.ui.ApplicationWindow;
import org.keyboardplaying.dailytasks.ui.events.TaskStateChangeListener;

/**
 * Provides methods to create the required application windows.
 * <p/>
 * If an instance of the required window is already visible, the factory method
 * will return the existing window.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public final class WindowFactory {

	/** The name for the main window. */
	private static final String NAME_MAIN = "windowMain";
	/** The name for the preferences window. */
	private static final String NAME_PREFS = "windowPreferences";
	/** The name for the about window. */
	private static final String NAME_ABOUT = "windowAbout";

	/**
	 * Creates the main window of the application.
	 * 
	 * @param prefs
	 *            the UI preferences
	 * @param tasks
	 *            the tasks to display
	 * @param taskStateListener
	 *            the listener for tasks' state changes
	 * @return the window
	 */
	public static Window makeMainWindow(UIPreferences prefs, TaskSet tasks,
			TaskStateChangeListener taskStateListener) {
		Window window = getVisibleWindowByName(NAME_PREFS);
		if (window == null) {
			MainPanel panel = new MainPanel(prefs, tasks, taskStateListener);
			window = new ApplicationWindow(prefs, "app.title", NAME_MAIN, panel);
		}
		return window;
	}

	/**
	 * Creates the window used to update the UI preferences.
	 * 
	 * @param prefs
	 *            the UI preferences
	 * @return the window
	 */
	public static Window makePreferencesWindow(UIPreferences prefs) {
		Window window = getVisibleWindowByName(NAME_PREFS);
		if (window == null) {
			window = new ApplicationWindow(prefs, "app.settings", NAME_PREFS,
					new PreferencesPanel(prefs, null));
		}
		return window;
	}

	/**
	 * Creates an "About" window.
	 * 
	 * @param prefs
	 *            the UI preferences
	 * @return the window
	 */
	public static Window makeAboutWindow(UIPreferences prefs) {
		Window window = getVisibleWindowByName(NAME_ABOUT);
		if (window == null) {
			window = new ApplicationWindow(prefs, "app.about", NAME_ABOUT,
					new AboutPanel(prefs));
		}
		return window;
	}

	/**
	 * Searches for a visible window with the supplied name.
	 * 
	 * @param name
	 *            the name of the window to look for
	 * @return the first visible window with the supplied name, or {@code null}
	 *         if none
	 */
	public static Window getVisibleWindowByName(String name) {
		Window result = null;

		if (name != null) {
			Window[] windows = Window.getWindows();
			for (Window window : windows) {
				if (window.isVisible() && name.equals(window.getName())) {
					result = window;
					break;
				}
			}
		}

		return result;
	}
}
