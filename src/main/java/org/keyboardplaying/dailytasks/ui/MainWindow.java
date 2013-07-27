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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

import org.keyboardplaying.dailytasks.model.TaskSet;
import org.keyboardplaying.dailytasks.model.UIPreferences;
import org.keyboardplaying.dailytasks.ui.components.todos.TaskPanel;
import org.keyboardplaying.dailytasks.ui.components.todos.TaskStateChangeListener;

/**
 * The main window of the application. It displays the tasks and the main tool
 * bar.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class MainWindow extends AbstractWindow {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 7355206010448309664L;

	/** The panel displaying the tasks. */
	private TaskPanel taskPanel;

	/**
	 * Creates a new instance.
	 * 
	 * @param prefs
	 *            the UI preferences for this window
	 * @param tasks
	 *            the tasks to display
	 * @param taskStateListener
	 *            the listener which should process the task's state change
	 */
	public MainWindow(UIPreferences prefs, TaskSet tasks,
			TaskStateChangeListener taskStateListener) {
		super(prefs, "app.title");

		// Build UI
		taskPanel = new TaskPanel(tasks, taskStateListener);
		build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.keyboardplaying.dailytasks.ui.AppWindow#buildContentPane()
	 */
	@Override
	protected Container buildContentPane() {
		/* Set the container. */
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		/* Add the content. */
		panel.add(taskPanel, BorderLayout.CENTER);
		panel.add(new Toolbar(getUIPreferences()), BorderLayout.EAST);

		/* Return the result. */
		return panel;
	}

	/** Emulates the click on the 'close' button of this window. */
	public void triggerClosingEvent() {
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}
