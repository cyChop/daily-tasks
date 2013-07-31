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

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * Provides utilities to handle windows.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public final class WindowUtils {

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

	/**
	 * Searches for a visible window with the supplied name.
	 * 
	 * @param name
	 *            the name of the window to look for
	 * @return the first visible window with the supplied name, or {@code null}
	 *         if none
	 */
	public static JFrame getVisibleWindowByName(String name) {
		JFrame result = null;

		if (name != null) {
			Frame[] windows = JFrame.getFrames();
			for (Frame window : windows) {
				if (window instanceof JFrame && window.isVisible()
						&& name.equals(window.getName())) {
					result = (JFrame) window;
					break;
				}
			}
		}

		return result;
	}
}
