package org.keyboardplaying.dailytasks;

import org.keyboardplaying.dailytasks.preferences.AppPreferences;
import org.keyboardplaying.dailytasks.ui.Theme;
import org.keyboardplaying.dailytasks.ui.todos.MainWindow;

/**
 * Main class for the application.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class App {

	/**
	 * Main method for the application.
	 * 
	 * @param args
	 *            optional arguments; first argument can be used to pass the
	 *            path to the properties file
	 */
	public static void main(String... args) {

		/* Apply the system L&F. */
		Theme.applyTheme(AppPreferences.getTheme());

		displayTaskWindow();
	}

	/**
	 * Loads the tasks and displays them in a dedicated window.
	 */
	private static void displayTaskWindow() {
		// Prepare the window and show
		MainWindow window = new MainWindow(AppPreferences.getTasks());
		window.setVisible(true);
	}
}
