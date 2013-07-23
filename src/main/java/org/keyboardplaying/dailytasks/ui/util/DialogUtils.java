/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
