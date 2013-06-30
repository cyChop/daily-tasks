package org.keyboardplaying.dailytasks.ui;

import java.awt.Color;

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
	/** A theme with a black background. */
	DARK(Color.DARK_GRAY, Color.LIGHT_GRAY);
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
		this.background = bgColor;
		this.font = txtColor;
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
}
