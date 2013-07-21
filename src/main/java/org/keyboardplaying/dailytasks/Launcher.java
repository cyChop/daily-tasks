package org.keyboardplaying.dailytasks;

import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.preferences.AppPreferences;
import org.keyboardplaying.dailytasks.preferences.Theme;
import org.keyboardplaying.dailytasks.ui.ApplicationWindow;

/**
 * Main class for the application.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class Launcher {

	/**
	 * Main method for the application.
	 * 
	 * @param args
	 *            optional arguments; first argument can be used to pass the
	 *            path to the properties file
	 */
	public static void main(String... args) {

		/* Load the application settings. */
		Theme.applyTheme(AppPreferences.getTheme());
		MessageBundle.setLocale(AppPreferences.getLocale());

		/* Run application */
		ApplicationWindow window = new ApplicationWindow();
		window.run();
	}
}
