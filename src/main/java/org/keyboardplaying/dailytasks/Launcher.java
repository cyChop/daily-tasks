package org.keyboardplaying.dailytasks;

import org.keyboardplaying.dailytasks.core.ApplicationClosingListener;
import org.keyboardplaying.dailytasks.core.TaskCompletionListener;
import org.keyboardplaying.dailytasks.core.TaskManager;
import org.keyboardplaying.dailytasks.core.TaskStateListener;
import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.model.TaskSet;
import org.keyboardplaying.dailytasks.preferences.AppPreferences;
import org.keyboardplaying.dailytasks.preferences.Theme;
import org.keyboardplaying.dailytasks.ui.MainWindow;

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
		TaskSet tasks = TaskManager.getInstance().getTasks();

		TaskStateListener taskStateListener = new TaskCompletionListener();

		MainWindow window = new MainWindow(tasks, taskStateListener);
		taskStateListener.setMainWindow(window);

		ApplicationClosingListener.register(window);
		window.setVisible(true);
	}
}
