package org.keyboardplaying.dailytasks.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.keyboardplaying.dailytasks.messages.Message;
import org.keyboardplaying.dailytasks.messages.MessageLevel;

/**
 * Object representation of the tasks.configuration file.
 * <p/>
 * Below is a sample of a commented tasks.configuration file, formatted as
 * expected.
 * 
 * <pre>
 * # === TASKS === #
 * 
 * # change the separator if you need to
 * tasks.separator=;
 * tasks.todos=Task 1;Task 2;Task 3
 * </pre>
 * 
 * @author cyChop (http://keyboardplaying.org/)
 * @deprecated The properties file is to be dropped soon.
 */
@Deprecated
public class TaskProperties {

	/* === PROPERTIES === */
	/** The field storing the locale in the configuration file. */
	private static final String PROPERTY_SEPARATOR = "tasks.separator";
	/** The field storing the tasks in the configuration file. */
	private static final String PROPERTY_TASKS = "tasks.todos";

	/* === DEFAULT VALUES === */
	/**
	 * The default minimal level for a message to be displayed when none or an
	 * incorrect one is provided.
	 */
	private static final MessageLevel DEFAULT_MSG_LEVEL = MessageLevel.WARNING;

	/* === PARSED PARAMETERS === */
	/**
	 * The minimal level for a message to be saved.
	 * <p/>
	 * This field is not exposed.
	 */
	private MessageLevel msgLvl = DEFAULT_MSG_LEVEL;
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
	 * Loads a configuration file from the supplied file name and parses it.
	 * 
	 * @param stream
	 *            the stream containing the configuration file
	 */
	public TaskProperties(InputStream stream) {
		try {
			parseProperties(stream);
		} catch (IOException e) {
			addMessage(Message.ERROR_READING_FILE);
		}
	}

	/**
	 * Loads a configuration file from the supplied file name and parses it.
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
	 * Returns the info messages for user.
	 * 
	 * @return the info messages
	 */
	public Collection<Message> getMessages() {
		return messages;
	}

	/**
	 * Returns the tasks to display.
	 * 
	 * @return the tasks to display
	 */
	public String[] getTasks() {
		return tasks;
	}

	/* === BUSINESS METHODS === */
	/**
	 * Loads a properties stream and maps its content to the various fields of
	 * this instance.
	 * 
	 * @param stream
	 *            the stream containing the configuration file
	 * @throws IOException
	 *             when the configuration file could not be read
	 */
	private void parseProperties(InputStream stream) throws IOException {
		Properties prop = loadProperties(stream);
		parseTasks(prop);
	}

	/**
	 * Loads the configuration file.
	 * 
	 * @param stream
	 *            the stream containing the configuration file
	 * @return a {@link Properties} file
	 * @throws IOException
	 *             when the configuration file could not be read
	 */
	private Properties loadProperties(InputStream stream) throws IOException {
		Properties properties = new Properties();
		properties.load(stream);
		return properties;
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
