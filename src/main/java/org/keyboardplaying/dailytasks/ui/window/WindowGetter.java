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
package org.keyboardplaying.dailytasks.ui.window;

import javax.swing.JFrame;

/**
 * Gets the request windows.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public interface WindowGetter {

	/**
	 * Gets the main window, which displays tasks and the application toolbar.
	 * 
	 * @return the main window
	 */
	JFrame getMainWindow();

	/**
	 * Gets the preferences window, to update the application settings.
	 * 
	 * @return the preferences window
	 */
	JFrame getPreferencesWindow();

	/**
	 * Gets the about window, which gives some intel about the application.
	 * 
	 * @return the about window
	 */
	JFrame getAboutWindow();
}
