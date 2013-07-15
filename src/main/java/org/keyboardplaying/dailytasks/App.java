package org.keyboardplaying.dailytasks;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.keyboardplaying.dailytasks.messages.Message;
import org.keyboardplaying.dailytasks.messages.MessageLevel;
import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.properties.TaskProperties;
import org.keyboardplaying.dailytasks.ui.DialogUtils;
import org.keyboardplaying.dailytasks.ui.todos.MainWindow;

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
	 *            optional arguments; first argument can be used to pass the
	 *            path to the properties file
	 */
	public static void main(String... args) {
		// load the properties first and set the locale so that any error
		// message displays with the right locale

		/* Load the properties. */
		String propertiesPath = args.length > 0 ? args[0]
				: PROPERTIES_FILE_NAME;
		TaskProperties prop = new TaskProperties(propertiesPath);

		/* Apply the system L&F. */
		applySystemLookAndFeel();

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
	 * Loads and applies the system look and feel for better integration within
	 * the environment.
	 */
	private static void applySystemLookAndFeel() {
		try {
			// Use system look & feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			DialogUtils.displayDialog(Message.LAF_LOADING_PROBLEM);
		} catch (ClassNotFoundException e) {
			DialogUtils.displayDialog(Message.LAF_LOADING_PROBLEM);
		} catch (InstantiationException e) {
			DialogUtils.displayDialog(Message.LAF_LOADING_PROBLEM);
		} catch (IllegalAccessException e) {
			DialogUtils.displayDialog(Message.LAF_LOADING_PROBLEM);
		}
	}

	/**
	 * Displays each of the supplied message in a separate dialog.
	 * 
	 * @param messages
	 *            the messages to display
	 * @return {@code true} if at least one of the messages was at
	 *         {@link org.keyboardplaying.dailytasks.messages.MessageLevel#ERROR}
	 *         level
	 */
	private static boolean displayEachMessageInADialog(
			Collection<Message> messages) {
		boolean noFatalError = true;

		// Wanna get rid of it? Configure your app properly.
		for (Message msg : messages) {
			DialogUtils.displayDialog(msg);
			if (msg.getLevel() == MessageLevel.ERROR) {
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
		MainWindow window = new MainWindow(tasks);
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
}
