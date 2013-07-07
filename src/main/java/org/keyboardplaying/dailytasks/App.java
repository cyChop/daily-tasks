package org.keyboardplaying.dailytasks;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.properties.Message;
import org.keyboardplaying.dailytasks.properties.TaskProperties;
import org.keyboardplaying.dailytasks.ui.TaskWindow;

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

		/* Load the properties. */
		TaskProperties prop = new TaskProperties(PROPERTIES_FILE_NAME);

		/* Display each message in a pop-up. */
		boolean noFatalError = displayEachMessageInADialog(prop.getMessages());

		/*
		 * Display the tasks only if no error occurred while loading the
		 * properties.
		 */
		if (noFatalError) {
			displayTaskWindow(prop);
		}
	}

	/**
	 * Displays each of the supplied message in a separate dialog.
	 * 
	 * @param messages
	 *            the messages to display
	 * @return {@code true} if at least one of the messages was at
	 *         {@link org.keyboardplaying.dailytasks.properties.MessageLevel#ERROR}
	 *         level
	 */
	private static boolean displayEachMessageInADialog(
			Collection<Message> messages) {
		boolean noFatalError = true;

		// Wanna get rid of it? Configure your app properly.
		for (Message msg : messages) {
			int msgLevel;

			switch (msg.getLevel()) {
			case ERROR:
				msgLevel = JOptionPane.ERROR_MESSAGE;
				noFatalError = false;
				break;
			case WARNING:
				msgLevel = JOptionPane.WARNING_MESSAGE;
				break;
			case INFO:
			default:
				msgLevel = JOptionPane.INFORMATION_MESSAGE;
			}

			displayDialog(msg.getMessage(), msgLevel);
			if (msgLevel == JOptionPane.ERROR_MESSAGE) {
				noFatalError = false;
			}
		}
		return noFatalError;
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
