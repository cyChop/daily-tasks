package org.keyboardplaying.dailytasks;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.keyboardplaying.dailytasks.ui.Theme;

/**
 * Object representation of the tasks.properties file.
 * <p/>
 * Below is a sample of a commented tasks.properties file, formatted as
 * expected.
 * 
 * <pre>
 * # === GENERAL BEHAVIOUR === #
 * 
 * # true to display tasks on top of other windows
 * # false otherwise
 * #always.on.top=false
 * always.on.top=true
 * 
 * # Choose a theme from LIGHT, GRAY and DARK
 * theme=GRAY
 * 
 * # === TASKS === #
 * 
 * # change the separator if you need to
 * tasks.separator=;
 * tasks.todos=Task 1;Task 2;Task 3
 * </pre>
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class TaskProperties {

	/* === PROPERTIES === */
	/** The field storing the "on top" property in the properties file. */
	private static final String PROPERTY_ON_TOP = "always.on.top";
	/** The field storing the theme in the properties file. */
	private static final String PROPERTY_THEME = "theme";
	/** The field storing the task separator in the properties file. */
	private static final String PROPERTY_SEPARATOR = "tasks.separator";
	/** The field storing the tasks in the properties file. */
	private static final String PROPERTY_TASKS = "tasks.todos";

	/* === DEFAULT VALUES === */
	/** The default theme when none or an incorrect one is provided. */
	private static final Theme DEFAULT_THEME = Theme.GRAY;
	/** The default "on top" property when none or an incorrect one is provided. */
	private static final boolean DEFAULT_ON_TOP = true;

	/* === PARSED PARAMETERS === */
	/**
	 * The "on top" properties.
	 * <p/>
	 * Specifies whether the task window should remain on top of others.
	 */
	private boolean onTop;
	/** The theme which should be applied to the task window. */
	private Theme theme;
	/** The tasks to display. */
	private String[] tasks;

	/* === ERROR MESSAGES === */
	/**
	 * Warning messages for errors which do not prevent the application from
	 * working.
	 */
	private List<String> warningMessages = new ArrayList<String>();
	/** Error message if properties parsing failed. */
	private String errorMessage;

	/* === CONSTRUCTOR === */
	/**
	 * Loads a properties file from the supplied file name and parses it.
	 * 
	 * @param fileName
	 *            the name of the file to load
	 */
	public TaskProperties(String fileName) {
		try {
			Properties prop = loadProperties(fileName);
			parseTheme(prop);
			parseOnTop(prop);
			parseTasks(prop);
		} catch (IOException e) {
			errorMessage = "Properties file " + fileName
					+ " could not be read."
					+ "\nPlease make sure it is located on the classpath.";
		}
	}

	/* === GETTERS === */
	/**
	 * Returns the "on top" properties.
	 * 
	 * @return {@code true} if the task window should always stay on top of
	 *         other windows, {@code false} otherwise
	 */
	public boolean isAlwaysOnTop() {
		return onTop;
	}

	/**
	 * Returns the {@link Theme} to apply to the task window.
	 * 
	 * @return the window's theme
	 */
	public Theme getTheme() {
		return theme;
	}

	/**
	 * Returns the tasks to display.
	 * 
	 * @return the tasks to display
	 */
	public String[] getTasks() {
		return tasks;
	}

	/**
	 * Returns the warning messages for errors which do not prevent the
	 * application from working.
	 * 
	 * @return the warning messages for errors which do not prevent the
	 *         application from working
	 */
	public List<String> getWarningMessages() {
		return warningMessages;
	}

	/**
	 * Returns the error message if properties parsing failed.
	 * 
	 * @return the error message if properties parsing failed
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/* === BUSINESS METHODS === */
	/**
	 * Loads the properties file.
	 * 
	 * @param fileName
	 *            the name of the properties file
	 * @return a {@link Properties} file
	 * @throws IOException
	 *             when the properties file could not be read
	 */
	private static Properties loadProperties(String fileName)
			throws IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(fileName));
		return properties;
	}

	/**
	 * Extracts and stores the theme information from the supplied properties,
	 * or applies the default one if parsing is impossible.
	 * 
	 * @param prop
	 *            the properties
	 */
	private void parseTheme(Properties prop) {
		String themeProp = (String) prop.get(PROPERTY_THEME);
		try {
			if (themeProp == null) {
				theme = DEFAULT_THEME;
				warningMessages
						.add("Theme could not be read from properties file, using default instead.");
			} else {
				theme = Theme.valueOf(themeProp);
			}
		} catch (IllegalArgumentException e) {
			theme = DEFAULT_THEME;
			warningMessages
					.add("Theme could not be read from properties file, using default instead.");
		}
	}

	/**
	 * Extracts and stores the "on top" properties from the supplied properties,
	 * or applies the default one if parsing is impossible.
	 * 
	 * @param prop
	 *            the properties
	 */
	private void parseOnTop(Properties prop) {
		String onTopProp = (String) prop.get(PROPERTY_ON_TOP);
		try {
			if (onTopProp == null) {
				onTop = DEFAULT_ON_TOP;
				warningMessages
						.add("OnTop property could not be read from properties file, using default instead.");
			} else {
				onTop = Boolean.valueOf(onTopProp);
			}
		} catch (IllegalArgumentException e) {
			onTop = DEFAULT_ON_TOP;
			warningMessages
					.add("OnTop property could not be read from properties file, using default instead.");
		}
	}

	/**
	 * Extracts and stores the tasks from the properties.
	 * 
	 * @param prop
	 *            the properties
	 */
	private void parseTasks(Properties prop) {
		String separator = (String) prop.get(PROPERTY_SEPARATOR);
		String tasksProp = (String) prop.get(PROPERTY_TASKS);
		if (tasksProp == null || separator == null) {
			errorMessage = "Properties tasks data seem incorrect."
					+ "\nPlease make sure the properties are correct.";
		} else {
			tasks = tasksProp.split(separator);
		}
	}
}
