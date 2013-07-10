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
}
