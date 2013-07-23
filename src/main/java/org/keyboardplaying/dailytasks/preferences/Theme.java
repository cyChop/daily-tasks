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
package org.keyboardplaying.dailytasks.preferences;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * A set of themes describing the general appearance of the task window.
 * 
 * @author cyChop (http://keyboardplaying.org)
 */
public enum Theme {

	/** A theme with a white background. */
	LIGHT(Color.WHITE, Color.DARK_GRAY),
	/** A theme with a gray background. */
	GRAY(Color.LIGHT_GRAY, Color.BLACK),
	/** A theme with a dark background. */
	DARK(Color.DARK_GRAY, Color.LIGHT_GRAY),
	/** A theme with a black background. */
	BLACK(Color.BLACK, Color.GRAY),

	/** A theme with a blue background. */
	BLUE(Color.BLUE, Color.WHITE, "checkicon-blue"),
	/** A theme with a light blue background. */
	CYAN(Color.CYAN, Color.BLACK, "checkicon-blue"),
	/** A theme with a light green background. */
	GREEN(Color.GREEN, Color.BLACK),
	/** A theme with a yellow background. */
	YELLOW(Color.YELLOW, Color.BLACK),
	/** A theme with an orange background. */
	ORANGE(Color.ORANGE, Color.BLACK),
	/** A theme with a red background. */
	RED(Color.RED, Color.WHITE, "checkicon-red"),
	/** A theme with a pink background. */
	PINK(Color.PINK, Color.BLACK, "checkicon-magenta"),
	/** A theme with a purple background. */
	MAGENTA(Color.MAGENTA, Color.WHITE, "checkicon-magenta");

	/** The icon which matches this theme. */
	private String iconIdentifier;
	/** The window's background color for this theme. */
	private Color background;
	/** The tasks' font color for this theme. */
	private Color font;

	/**
	 * Creates a new instance.
	 * 
	 * @param bgColor
	 *            the background color for this theme
	 * @param txtColor
	 *            the task font color for this theme
	 */
	private Theme(Color bgColor, Color txtColor) {
		this(bgColor, txtColor, "checkicon");
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param bgColor
	 *            the background color for this theme
	 * @param txtColor
	 *            the task font color for this theme
	 * @param icon
	 *            the icon which matches this theme
	 */
	private Theme(Color bgColor, Color txtColor, String icon) {
		this.background = bgColor;
		this.font = txtColor;
		this.iconIdentifier = icon;
	}

	/**
	 * Returns the window's background color for this theme.
	 * 
	 * @return the background color
	 */
	public Color getBgColor() {
		return background;
	}

	/**
	 * Returns the tasks' text color for this theme.
	 * 
	 * @return the font color
	 */
	public Color getTxtColor() {
		return font;
	}

	/**
	 * Returns the icon which matches this theme.
	 * 
	 * @return the icon
	 */
	public String getIcon() {
		return iconIdentifier;
	}

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
