package org.keyboardplaying.dailytasks;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.ui.TaskWindow;
import org.keyboardplaying.dailytasks.util.Utils;

/**
 * Main class for the application.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class App {

	/** The name of the expected properties file. */
	private static final String PROPERTIES_FILE_NAME = "tasks.properties";

	/**
	 * Main method for the application.
	 * 
	 * @param args
	 *            optional arguments (unused)
	 */
	public static void main(String... args) {
		TaskProperties prop = new TaskProperties(PROPERTIES_FILE_NAME);

		if (prop.getErrorMessage() == null) {
			displayWarningsIfAny(prop.getWarningMessages());
			displayTaskWindow(prop);
		} else {
			displayErrorDialog(prop.getErrorMessage());
		}
	}

	/**
	 * Loads the tasks and displays them in a dedicated window.
	 * 
	 * @param prop
	 *            the properties
	 */
	private static void displayTaskWindow(TaskProperties prop) {
		Set<Task> tasks = parseTasks(prop.getTasks());

		// Prepare the window and show
		TaskWindow window = new TaskWindow(tasks, prop.isAlwaysOnTop(),
				prop.getTheme());
		window.setVisible(true);
	}

	/**
	 * Parses an array of strings to a collection of {@link Task} instances.
	 * 
	 * @param stringTasks
	 *            the string version of the tasks
	 * @return an ordered {@link Set} of {@link Task} instances
	 */
	private static Set<Task> parseTasks(String[] stringTasks) {
		// Use an ordered Set:
		// - ordered to preserve tasks' order
		// - set to avoid duplicates
		Set<Task> tasks = new LinkedHashSet<Task>();
		for (String todo : stringTasks) {
			tasks.add(new Task(todo));
		}
		return tasks;
	}

	/**
	 * Displays dialogs for all warning messages if any.
	 * 
	 * @param messages
	 *            the message list
	 */
	private static void displayWarningsIfAny(List<String> messages) {
		if (messages != null && !messages.isEmpty()) {
			displayWarningDialog(Utils.implode(messages, "\n"));
		}
	}

	/**
	 * Displays a warning dialog.
	 * 
	 * @param message
	 *            the warning message
	 */
	private static void displayWarningDialog(final String message) {
		displayDialog(message, JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Displays an alert dialog.
	 * 
	 * @param message
	 *            the alert message
	 */
	private static void displayErrorDialog(final String message) {
		displayDialog(message, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Displays a dialog.
	 * 
	 * @param message
	 *            the message
	 * @param type
	 *            the dialog type
	 */
	private static void displayDialog(final String message, final int type) {
		JOptionPane.showMessageDialog(null, message, "Error", type);
	}
}
