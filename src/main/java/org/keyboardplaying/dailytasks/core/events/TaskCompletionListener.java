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

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowEvent;

import org.keyboardplaying.dailytasks.core.TaskManager;
import org.keyboardplaying.dailytasks.model.Task;

/**
 * An implementation of the {@link TaskStateController} which closes the
 * application once all the tasks have been completed.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class TaskCompletionListener extends TaskStateListener {

	/** Creates a new instance. */
	public TaskCompletionListener() {
	}

	/**
	 * Closes the application if all tasks have been completed.
	 * 
	 * @param task
	 *            {@inheritDoc}
	 */
	@Override
	protected void processTaskAfterStateSaved(Task task) {
		// Is job done?
		if (TaskManager.getInstance().areAllTasksDone()) {
			// Close the window
			triggerClosingEvent(getMainWindow());
		}
	}

	/**
	 * Emulates the click on the 'close' button of the supplied window.
	 * 
	 * @param window
	 *            the window to close
	 */
	public static void triggerClosingEvent(Window window) {
		WindowEvent wev = new WindowEvent(window, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}
