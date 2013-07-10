package org.keyboardplaying.dailytasks.messages;

/**
 * The possible error messages.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public enum Message {

	/** Message for when the look and feel could not be loaded. */
	LAF_LOADING_PROBLEM(MessageLevel.WARNING, "warning.lookandfeel"),

	/**
	 * Message for when no theme has been specified and the default one had to
	 * be applied.
	 */
	UNSPECIFIED_THEME(MessageLevel.INFO, "info.theme.unspecified"),

	/**
	 * Message for when it has not been specified whether the window should be
	 * stuck on top and the default value had to be applied.
	 */
	UNSPECIFIED_ONTOP(MessageLevel.INFO, "info.ontop.unspecified"),

	/**
	 * Message for when the specified message level threshold is incorrect and
	 * the default one had to be used instead.
	 */
	INCORRECT_MSG_LVL(MessageLevel.WARNING, "warning.messagelevel.incorrect"),

	/**
	 * Message for when the specified locale is incorrect and the default one
	 * had to be used instead.
	 */
	INCORRECT_LOCALE(MessageLevel.WARNING, "warning.locale.incorrect"),

	/**
	 * Message for when the specified theme is incorrect and the default one had
	 * to be applied.
	 */
	INCORRECT_THEME(MessageLevel.WARNING, "warning.theme.incorrect"),

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
