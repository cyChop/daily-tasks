package org.keyboardplaying.dailytasks.properties;

/**
 * The possible error messages for the task properties.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public enum Message {

	/**
	 * Message for when no theme has been specified and the default one had to
	 * be applied.
	 */
	UNSPECIFIED_THEME(MessageLevel.INFO,
			"Theme was not specified in the properties file, using default instead."),
	/**
	 * Message for when it has not been specified whether the window should be
	 * stuck on top and the default value had to be applied.
	 */
	UNSPECIFIED_ONTOP(
			MessageLevel.INFO,
			"OnTop property was not specified in the properties file, using default instead."),
	/**
	 * Message for when the specified message level threshold is incorect and
	 * the default one had to be used instead.
	 */
	INCORRECT_MSG_LVL(
			MessageLevel.WARNING,
			"The specified level threshold for messages is incorrect, using default instead."),
	/**
	 * Message for when the specified theme is incorrect and the default one had
	 * to be applied.
	 */
	INCORRECT_THEME(MessageLevel.WARNING,
			"Theme specified in properties file is incorrect, using default instead."),
	/** Message for when the file could not be read. */
	ERROR_READING_FILE(MessageLevel.ERROR, "Properties file could not be read."
			+ "\nPlease make sure it is on the classpath."),
	/** Message for when the tasks could not be parsed from file. */
	ERROR_READING_TASKS(MessageLevel.ERROR,
			"Properties tasks data seem incorrect."
					+ "\nPlease make sure the properties are correct.");

	/** The message level. */
	private MessageLevel level;
	/** A human-readable message. */
	private String message;

	/**
	 * Creates a new instance.
	 * 
	 * @param level
	 *            the message level
	 * @param message
	 *            a human-readable message
	 */
	private Message(MessageLevel level, String message) {
		this.level = level;
		this.message = message;
	}

	/**
	 * Returns the message level.
	 * 
	 * @return the message level
	 */
	public MessageLevel getLevel() {
		return level;
	}

	/**
	 * Returns a human-readable message.
	 * 
	 * @return a human-readable message
	 */
	public String getMessage() {
		return message;
	}
}
