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
package org.keyboardplaying.dailytasks.ui.events;

import org.keyboardplaying.dailytasks.model.UIPreferences;

/**
 * This interface defines a listener which will be called upon when UI
 * preferences change.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public interface UIPreferencesChangeListener {

	/**
	 * Called when UI preferences change. Implementations of this method should
	 * persist the UI preferences.
	 * 
	 * @param prefs
	 *            the new values for the UI preferences
	 */
	void saveUIPreferences(UIPreferences prefs);
}