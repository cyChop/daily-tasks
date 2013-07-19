package org.keyboardplaying.dailytasks.ui;

import javax.swing.JFrame;

import org.keyboardplaying.dailytasks.preferences.AppPreferences;

/**
 * Abstract parent for all windows in the application.
 * <p/>
 * This class defines some behavior common to all windows.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public abstract class AppWindow extends JFrame {

	/** Generated serial version UID. */
	private static final long serialVersionUID = -4975217954201488541L;

	/** The theme used for this window. */
	private Theme theme;

	/** Creates a new instance. */
	protected AppWindow() {
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
	protected AppWindow(String titleKey) {
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
		setIconImages(IconUtils.getIconImages(theme.getIcon(), ".png"));

		// Make sure the associated thread is terminated when window is closed
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
