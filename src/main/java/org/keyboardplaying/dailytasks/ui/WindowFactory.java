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
package org.keyboardplaying.dailytasks.ui;

import javax.swing.JFrame;

import org.keyboardplaying.dailytasks.model.TaskSet;
import org.keyboardplaying.dailytasks.model.UIPreferences;
import org.keyboardplaying.dailytasks.ui.events.ApplicationController;
import org.keyboardplaying.dailytasks.ui.events.TaskStateChangeListener;
import org.keyboardplaying.dailytasks.ui.events.UIPreferencesChangeListener;
import org.keyboardplaying.dailytasks.ui.panels.AboutPanel;
import org.keyboardplaying.dailytasks.ui.panels.MainPanel;
import org.keyboardplaying.dailytasks.ui.panels.PreferencesPanel;
import org.keyboardplaying.dailytasks.ui.util.WindowUtils;
import org.keyboardplaying.dailytasks.ui.window.ApplicationWindow;
import org.keyboardplaying.dailytasks.ui.window.WindowGetter;

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
	 * @param alwaysOnTop
	 *            {@code true} if window should always remain on top of other
	 *            windows
	 * @param getter
	 *            the object in charge of getting the windows on demand
	 * @param tasks
	 *            the tasks to display
	 * @param taskStateListener
	 *            the listener for tasks' state changes
	 * @return the window
	 */
	public static JFrame makeMainWindow(boolean alwaysOnTop,
			WindowGetter getter, TaskSet tasks,
			TaskStateChangeListener taskStateListener) {
		JFrame window = WindowUtils.getVisibleWindowByName(NAME_PREFS);
		if (window == null) {
			MainPanel panel = new MainPanel(getter, tasks, taskStateListener);
			window = new ApplicationWindow("app.title", NAME_MAIN, panel,
					alwaysOnTop);
		}
		return window;
	}

	/**
	 * Creates the window used to update the UI preferences.
	 * 
	 * @param alwaysOnTop
	 *            {@code true} if window should always remain on top of other
	 *            windows
	 * @param prefs
	 *            the UI preferences
	 * @param listener
	 *            the object listening to changes of the tasks' states
	 * @param controller
	 *            the controller in charge of restarting the application if need
	 *            be
	 * 
	 * @return the window
	 */
	public static JFrame makePreferencesWindow(boolean alwaysOnTop,
			UIPreferences prefs, UIPreferencesChangeListener listener,
			ApplicationController controller) {
		JFrame window = WindowUtils.getVisibleWindowByName(NAME_PREFS);
		if (window == null) {
			window = new ApplicationWindow("app.settings", NAME_PREFS,
					new PreferencesPanel(prefs, listener, controller),
					alwaysOnTop);
		}
		return window;
	}

	/**
	 * Creates an "About" window.
	 * 
	 * @param alwaysOnTop
	 *            {@code true} if window should always remain on top of other
	 *            windows
	 * @return the window
	 */
	public static JFrame makeAboutWindow(boolean alwaysOnTop) {
		JFrame window = WindowUtils.getVisibleWindowByName(NAME_ABOUT);
		if (window == null) {
			window = new ApplicationWindow("app.about", NAME_ABOUT,
					new AboutPanel(), alwaysOnTop);
		}
		return window;
	}
}
