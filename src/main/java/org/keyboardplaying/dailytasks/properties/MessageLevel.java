package org.keyboardplaying.dailytasks.properties;

/**
 * A list of possible levels for messages.
 * <p/>
 * The message level can be used to determine how to display the message (log
 * level, dialog type, ...).
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public enum MessageLevel {
	/** For information messages, with no impact on the application behavior. */
	INFO,
	/**
	 * For messages stating an error for which a contingency behavior has been
	 * implemented.
	 */
	WARNING,
	/**
	 * For messages stating an error which prevents the application from working
	 * properly.
	 */
	ERROR;
}
