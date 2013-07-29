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

import org.keyboardplaying.dailytasks.model.TaskSet;
import org.keyboardplaying.dailytasks.model.UIPreferences;
import org.keyboardplaying.dailytasks.ui.ApplicationWindow;
import org.keyboardplaying.dailytasks.ui.events.TaskStateChangeListener;

/**
 * Provides methods to create the required application windows.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public final class WindowFactory {

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
	public static ApplicationWindow makeMainWindow(UIPreferences prefs,
			TaskSet tasks, TaskStateChangeListener taskStateListener) {
		MainPanel panel = new MainPanel(prefs, tasks, taskStateListener);
		return new ApplicationWindow(prefs, "app.title", panel);
	}

	/**
	 * Creates the window used to update the UI preferences.
	 * 
	 * @param prefs
	 *            the UI preferences
	 * @return the window
	 */
	public static ApplicationWindow makePreferencesWindow(UIPreferences prefs) {
		return new ApplicationWindow(prefs, "app.settings",
				new PreferencesPanel(prefs, null));
	}

	/**
	 * Creates an "About" window.
	 * 
	 * @param prefs
	 *            the UI preferences
	 * @return the window
	 */
	public static ApplicationWindow makeAboutWindow(UIPreferences prefs) {
		return new ApplicationWindow(prefs, "app.about", new AboutPanel(prefs));
	}
}
