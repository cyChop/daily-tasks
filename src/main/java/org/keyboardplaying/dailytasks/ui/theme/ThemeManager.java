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
package org.keyboardplaying.dailytasks.ui.theme;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author cyChop (http://keyboardplaying.org/)
 */
// TODO unit test in a headless environment
public class ThemeManager {

	/**
	 * Loads the system look and feel and registers theme properties to avoid
	 * manual manipulations.
	 * <p/>
	 * The choice of the system look and feel was made to integrate at best with
	 * user's usual UI.
	 * 
	 * @param theme
	 *            the theme which should be applied
	 */
	public static void applyTheme(Theme theme) {

		/* Switch to native look & feel. */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// this is no custom look and feel and should not happen
		} catch (InstantiationException e) {
			// this is no custom look and feel and should not happen
		} catch (IllegalAccessException e) {
			// this is no custom look and feel and should not happen
		} catch (UnsupportedLookAndFeelException e) {
			// this is no custom look and feel and should not happen
		}

		/* Apply custom theme. */
		// TODO extend to make sure everything is fine
		// Some generalities
		UIManager.put("Panel.background", theme.getBgColor());
		UIManager.put("Button.background", theme.getBgColor());

		// Texts
		UIManager.put("CheckBox.foreground", theme.getTxtColor());
		UIManager.put("Label.foreground", theme.getTxtColor());

		// Dialogs
		UIManager.put("OptionPane.background", theme.getBgColor());
		UIManager.put("OptionPane.messageForeground", theme.getTxtColor());
	}
}
