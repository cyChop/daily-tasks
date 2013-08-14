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
package org.keyboardplaying.dailytasks.core.events;

import javax.swing.JFrame;

import org.keyboardplaying.dailytasks.core.managers.PreferencesManager;
import org.keyboardplaying.dailytasks.core.managers.TaskManager;
import org.keyboardplaying.dailytasks.model.TaskSet;
import org.keyboardplaying.dailytasks.model.UIPreferences;
import org.keyboardplaying.dailytasks.ui.WindowFactory;
import org.keyboardplaying.dailytasks.ui.events.ApplicationController;
import org.keyboardplaying.dailytasks.ui.window.WindowGetter;

/**
 * A business-logic-rich implementation of the {@link WindowGetter} interface.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class ApplicationWindowGetter implements WindowGetter {

	/** An {@link ApplicationController}. */
	private ApplicationController controller;

	/**
	 * Creates a new instance.
	 * 
	 * @param controller
	 *            the application controller
	 */
	public ApplicationWindowGetter(ApplicationController controller) {
		this.controller = controller;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.keyboardplaying.dailytasks.ui.WindowGetter#getMainWindow()
	 */
	@Override
	public JFrame getMainWindow() {
		TaskSet tasks = TaskManager.getInstance().getTasks();
		TaskCompletionListener taskStateListener = new TaskCompletionListener();
		JFrame window = WindowFactory.makeMainWindow(getUIPrefs()
				.isAlwaysOnTop(), this, tasks, taskStateListener);
		taskStateListener.setMainWindow(window);
		return window;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.keyboardplaying.dailytasks.ui.WindowGetter#getPreferencesWindow()
	 */
	@Override
	public JFrame getPreferencesWindow() {
		UIPreferences prefs = getUIPrefs();
		return WindowFactory.makePreferencesWindow(prefs.isAlwaysOnTop(),
				prefs, new UIPreferencesSaver(), controller);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.keyboardplaying.dailytasks.ui.WindowGetter#getAboutWindow()
	 */
	@Override
	public JFrame getAboutWindow() {
		return WindowFactory.makeAboutWindow(getUIPrefs().isAlwaysOnTop());
	}

	/**
	 * Loads the UI preferences from the {@link PreferencesManager}.
	 * 
	 * @return the UI preferences
	 */
	private UIPreferences getUIPrefs() {
		return PreferencesManager.getUIPreferences();
	}
}
