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
import org.keyboardplaying.dailytasks.model.UIPreferences;
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
	private static final long serialVersionUID = 2595887968484253180L;

	/** The UI preferences. */
	private UIPreferences prefs;

	/**
	 * Creates a new instance.
	 * 
	 * @param prefs
	 *            the UI preferences for this window
	 * @param titleKey
	 *            the key to the title of the current window in the
	 *            {@link MessageBundle}
	 */
	protected AbstractWindow(UIPreferences prefs, String titleKey) {
		super(MessageBundle.get(titleKey));
		this.prefs = prefs;
		initWindow();
	}

	/**
	 * Returns the UI preferences for this window.
	 * 
	 * @return the UI preferences for this window
	 */
	protected UIPreferences getUIPreferences() {
		return prefs;
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
		// Set icon from theme
		setIconImages(IconUtils.getWindowIconImages(prefs.getTheme().getIcon(),
				".png"));

		// Make sure the associated thread is terminated when window is closed
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/** Builds the window. */
	protected void build() {
		/* General styling. */
		setAlwaysOnTop(prefs.isAlwaysOnTop());
		setResizable(false);
		// Center on screen
		setLocationRelativeTo(null);

		/* Now, the content. */
		setContentPane(buildContentPane());

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
