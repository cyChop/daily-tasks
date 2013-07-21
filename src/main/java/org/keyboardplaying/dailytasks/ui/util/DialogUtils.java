package org.keyboardplaying.dailytasks.ui.util;

import javax.swing.JOptionPane;

import org.keyboardplaying.dailytasks.messages.Message;
import org.keyboardplaying.dailytasks.messages.MessageBundle;

/**
 * Utility class for the display of dialogs.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public final class DialogUtils {

	/**
	 * Displays a {@link Message} in a dialog.
	 * 
	 * @param msg
	 *            the message to display
	 */
	public static void displayDialog(Message msg) {
		int msgLevel;

		switch (msg.getLevel()) {
		case ERROR:
			msgLevel = JOptionPane.ERROR_MESSAGE;
			break;
		case WARNING:
			msgLevel = JOptionPane.WARNING_MESSAGE;
			break;
		case INFO:
		default:
			msgLevel = JOptionPane.INFORMATION_MESSAGE;
		}

		DialogUtils.displayDialog(MessageBundle.get(msg.getKey()), msgLevel);
	}

	/**
	 * Displays a dialog.
	 * 
	 * @param message
	 *            the message
	 * @param type
	 *            the dialog type
	 */
	private static void displayDialog(String message, int type) {
		JOptionPane.showMessageDialog(null, message, "Error", type);
	}
}
