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
package org.keyboardplaying.dailytasks.core.events;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import org.keyboardplaying.dailytasks.core.TaskManager;
import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.ui.panels.MainPanel;

/**
 * This {@link WindowAdapter} applies to the application's {@link MainPanel}.
 * <p/>
 * It ensures two things:
 * <ul>
 * <li>When the main window's close button is clicked, it ensures all tasks are
 * complete. If some are still unchecked, it will ask for confirmation before
 * closing the application.</li>
 * <li>When the main window is closed, the program is terminated and any other
 * window open is closed along with it.</li>
 * </ul>
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class ApplicationClosingListener extends WindowAdapter {

	/** The application's main window. */
	private Window mainWindow;

	/**
	 * Creates a new instance.
	 * 
	 * @param mainWindow
	 *            the application's main window
	 */
	private ApplicationClosingListener(Window mainWindow) {
		this.mainWindow = mainWindow;
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * When the window is closed, this implementation asks for confirmation if
	 * some tasks are not complete. If all tasks are complete, it just closes
	 * the application.
	 * <p/>
	 * If other windows were left open, they will be closed.
	 * 
	 * @param e
	 *            {@inheritDoc}
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		if (TaskManager.getInstance().areAllTasksDone()) {

			closeWindowAndTermineProgram(e);

		} else {

			/*
			 * For window closing, if some tasks are not yet completed, ask for
			 * confirmation.
			 */
			int exit = JOptionPane.showConfirmDialog(mainWindow,
					MessageBundle.get("confirm.skip.unfinished.body"),
					MessageBundle.get("confirm.skip.unfinished.title"),
					JOptionPane.YES_NO_OPTION);

			if (exit == JOptionPane.YES_OPTION) {
				closeWindowAndTermineProgram(e);
			}
		}
	}

	/**
	 * Closes this window and any other open by terminating the program.
	 * 
	 * @param e
	 *            the closing window event
	 */
	private void closeWindowAndTermineProgram(WindowEvent e) {
		super.windowClosing(e);
		// Terminate program (make sure all other windows are closed)
		System.exit(0);
	}

	/**
	 * Creates and attaches an instance to the {@link MainPanel}.
	 * 
	 * @param window
	 *            the window to control via an
	 *            {@link ApplicationClosingListener}
	 */
	public static void register(JFrame window) {
		ApplicationClosingListener instance = new ApplicationClosingListener(
				window);
		window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(instance);
	}
}