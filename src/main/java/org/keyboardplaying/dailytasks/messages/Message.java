package org.keyboardplaying.dailytasks.messages;

/**
 * The possible error messages.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public enum Message {

	/** Message for when the look and feel could not be loaded. */
	LAF_LOADING_PROBLEM(MessageLevel.WARNING, "warning.lookandfeel"),

	/** Message for when the file could not be read. */
	ERROR_READING_FILE(MessageLevel.ERROR, "error.properties.incorrect"),

	/** Message for when the tasks could not be parsed from file. */
	ERROR_READING_TASKS(MessageLevel.ERROR, "error.tasks.incorrect");

	/** The message level. */
	private MessageLevel level;
	/** The key for the message in the bundle. */
	private String messageKey;

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
		this.messageKey = message;
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
	 * Returns the key for the message in the bundle.
	 * 
	 * @return the key for the message in the bundle
	 */
	public String getKey() {
		return messageKey;
	}
}
