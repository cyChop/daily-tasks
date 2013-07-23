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

import java.awt.Container;

import javax.swing.JFrame;

import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.preferences.AppPreferences;
import org.keyboardplaying.dailytasks.preferences.Theme;
import org.keyboardplaying.dailytasks.ui.util.IconUtils;

/**
 * Abstract parent for all windows in the application.
 * <p/>
 * This class defines some behavior common to all windows.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public abstract class AbstractWindow extends JFrame {

	/** Generated serial version UID. */
	private static final long serialVersionUID = -1535058140157572739L;

	/** The theme used for this window. */
	private Theme theme;

	/** Creates a new instance. */
	protected AbstractWindow() {
		super();
		initWindow();
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param titleKey
	 *            the key to the title of the current window in the
	 *            {@link MessageBundle}
	 */
	protected AbstractWindow(String titleKey) {
		super(MessageBundle.get(titleKey));
		initWindow();
	}

	/**
	 * Returns the theme used for this window.
	 * 
	 * @return the theme used for this window
	 */
	protected Theme getTheme() {
		return theme;
	}

	/**
	 * Initializes some behaviors which should be common to all windows in the
	 * application.
	 * <p/>
	 * The initialization consists in two steps:
	 * <ul>
	 * <li>Set the icon according to the current theme for the application.</li>
	 * <li>Make sure the thread associated to this window will be terminated on
	 * close.</li>
	 * </ul>
	 */
	private void initWindow() {
		// Load theme from preferences
		// Store the theme to avoid parsing from prefs each time (caching)
		theme = AppPreferences.getTheme();

		// Set icon from theme
		setIconImages(IconUtils.getWindowIconImages(theme.getIcon(), ".png"));

		// Make sure the associated thread is terminated when window is closed
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/** Builds the window. */
	protected void build() {
		/* General styling. */
		setAlwaysOnTop(AppPreferences.isAlwaysOnTop());
		setResizable(false);
		// Center on screen
		setLocationRelativeTo(null);

		/* Now, the content. */
		Container contentPane = buildContentPane();
		contentPane.setBackground(theme.getBgColor());
		contentPane.setForeground(theme.getTxtColor());
		setContentPane(contentPane);

		/* Adapt size to the fittest. */
		pack();
	}

	/**
	 * Builds the content of the window.
	 * 
	 * @return the generated {@link Container}
	 */
	protected abstract Container buildContentPane();
}
