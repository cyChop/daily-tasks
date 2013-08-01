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
package org.keyboardplaying.dailytasks;

import javax.swing.JFrame;

import org.keyboardplaying.dailytasks.core.PreferencesManager;
import org.keyboardplaying.dailytasks.core.events.ApplicationClosingListener;
import org.keyboardplaying.dailytasks.core.events.ApplicationWindowGetter;
import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.model.UIPreferences;
import org.keyboardplaying.dailytasks.ui.theme.ThemeManager;
import org.keyboardplaying.dailytasks.ui.util.WindowUtils;
import org.keyboardplaying.dailytasks.ui.window.WindowGetter;

/**
 * Main class for the application.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class Launcher {

	/** The object in charge of creating the windows on demand. */
	private static WindowGetter getter = new ApplicationWindowGetter();

	/**
	 * Main method for the application.
	 * 
	 * @param args
	 *            optional arguments; first argument can be used to pass the
	 *            path to the properties file
	 */
	public static void main(String... args) {
		start();
	}

	/** Applies the UI preferences and starts the application. */
	private static void start() {
		/* Load the application settings. */
		UIPreferences prefs = PreferencesManager.getUIPreferences();
		ThemeManager.applyTheme(prefs.getTheme());
		MessageBundle.setLocale(prefs.getLocale());

		/* Run application */
		JFrame window = getter.getMainWindow();

		ApplicationClosingListener.register(window);
		window.setVisible(true);
	}

	/** Closes all windows and restarts the application. */
	public static void restart() {
		WindowUtils.disposeAllWindows();
		start();
	}
}
