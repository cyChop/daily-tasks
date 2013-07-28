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
package org.keyboardplaying.dailytasks.ui;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.model.UIPreferences;
import org.keyboardplaying.dailytasks.ui.util.IconUtils;

/**
 * A standard window for all the application's screen.
 * <p/>
 * The constructor requires the {@link UIPreferences}, a key for the title in
 * the message bundle and the content pane.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class ApplicationWindow extends JFrame {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 3031951572624643029L;

	/**
	 * Creates a new instance.
	 * 
	 * @param prefs
	 *            the application UI preferences
	 * @param titleKey
	 *            the key for this window's title in the {@link MessageBundle}
	 * @param contentPane
	 *            the window's content
	 */
	public ApplicationWindow(UIPreferences prefs, String titleKey,
			Container contentPane) {
		/* Title and icon. */
		super(MessageBundle.get(titleKey));
		setIconImages(IconUtils.getWindowIconImages(prefs.getTheme()));

		/* Make sure thread is ended on close. */
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		/* General styling */
		setAlwaysOnTop(prefs.isAlwaysOnTop());
		setResizable(false);
		// center on screen
		// XXX save and restore last position?
		setLocationRelativeTo(null);

		/* Now the content. */
		setContentPane(contentPane);

		/* Adapt size to fit the content. */
		pack();
	}

	/** Emulates the click on the 'close' button of this window. */
	public void triggerClosingEvent() {
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}
