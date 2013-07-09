package org.keyboardplaying.dailytasks.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

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
	/**
	 * The field storing the minimal level for a message to be displayed in the
	 * properties file.
	 */
	private static final String PROPERTY_MSG_LEVEL = "msg.level";
	/** The field storing the "on top" property in the properties file. */
	private static final String PROPERTY_ON_TOP = "always.on.top";
	/** The field storing the theme in the properties file. */
	private static final String PROPERTY_THEME = "theme";
	/** The field storing the task separator in the properties file. */
	private static final String PROPERTY_SEPARATOR = "tasks.separator";
	/** The field storing the tasks in the properties file. */
	private static final String PROPERTY_TASKS = "tasks.todos";

	/* === DEFAULT VALUES === */
	/**
	 * The default minimal level for a message to be displayed when none or an
	 * incorrect one is provided.
	 */
	private static final MessageLevel DEFAULT_MSG_LEVEL = MessageLevel.WARNING;
	/** The default theme when none or an incorrect one is provided. */
	private static final Theme DEFAULT_THEME = Theme.GRAY;
	/** The default "on top" property when none or an incorrect one is provided. */
	private static final boolean DEFAULT_ON_TOP = true;

	/* === PARSED PARAMETERS === */
	/**
	 * The minimal level for a message to be saved.
	 * <p/>
	 * This field is not exposed.
	 */
	private MessageLevel msgLvl = DEFAULT_MSG_LEVEL;
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
	 * Info, warning and error messages, filled while initializing the instance.
	 * <p/>
	 * A {@link Set} is used to guarantee fast access and unicity of each
	 * element.
	 */
	private Set<Message> messages = new HashSet<Message>();

	/* === CONSTRUCTORS === */
	/**
	 * Loads a properties file from the supplied file name and parses it.
	 * 
	 * @param stream
	 *            the stream containing the properties file
	 */
	public TaskProperties(InputStream stream) {
		try {
			parseProperties(stream);
		} catch (IOException e) {
			addMessage(Message.ERROR_READING_FILE);
		}
	}

	/**
	 * Loads a properties file from the supplied file name and parses it.
	 * 
	 * @param fileName
	 *            the name of the file to load
	 */
	public TaskProperties(String fileName) {
		try {
			parseProperties(new FileInputStream(fileName));
		} catch (IOException e) {
			addMessage(Message.ERROR_READING_FILE);
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
	 * Returns the info messages for user.
	 * 
	 * @return the info messages
	 */
	public Collection<Message> getMessages() {
		return messages;
	}

	/* === BUSINESS METHODS === */
	/**
	 * Loads a properties stream and maps its content to the various fields of
	 * this instance.
	 * 
	 * @param stream
	 *            the stream containing the properties file
	 * @throws IOException
	 *             when the properties file could not be read
	 */
	private void parseProperties(InputStream stream) throws IOException {
		Properties prop = loadProperties(stream);
		parseMsgLvl(prop);
		parseTheme(prop);
		parseOnTop(prop);
		parseTasks(prop);
	}

	/**
	 * Loads the properties file.
	 * 
	 * @param stream
	 *            the stream containing the properties file
	 * @return a {@link Properties} file
	 * @throws IOException
	 *             when the properties file could not be read
	 */
	private Properties loadProperties(InputStream stream) throws IOException {
		Properties properties = new Properties();
		properties.load(stream);
		return properties;
	}

	/**
	 * Extracts and stores the minimum level for messages to be displayed, or
	 * applies default if parsing is impossible.
	 * 
	 * @param prop
	 *            the properties
	 */
	private void parseMsgLvl(Properties prop) {
		String themeProp = (String) prop.get(PROPERTY_MSG_LEVEL);
		if (themeProp != null) {
			try {
				msgLvl = MessageLevel.valueOf(themeProp);
			} catch (IllegalArgumentException e) {
				addMessage(Message.INCORRECT_MSG_LVL);
			}
		}
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
		if (themeProp == null) {
			theme = DEFAULT_THEME;
			addMessage(Message.UNSPECIFIED_THEME);
		} else {
			try {
				theme = Theme.valueOf(themeProp);
			} catch (IllegalArgumentException e) {
				theme = DEFAULT_THEME;
				addMessage(Message.INCORRECT_THEME);
			}
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
		if (onTopProp == null) {
			onTop = DEFAULT_ON_TOP;
			addMessage(Message.UNSPECIFIED_ONTOP);
		} else {
			onTop = Boolean.valueOf(onTopProp);
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
			addMessage(Message.ERROR_READING_TASKS);
		} else {
			tasks = tasksProp.split(separator);
		}
	}

	/**
	 * Adds a message to the list of messages for display, provided the level of
	 * this message is at least equal to the specified threshold.
	 * 
	 * @param msg
	 *            the message for user
	 */
	private void addMessage(Message msg) {
		if (msgLvl.compareTo(msg.getLevel()) <= 0) {
			messages.add(msg);
		}
	}
}
