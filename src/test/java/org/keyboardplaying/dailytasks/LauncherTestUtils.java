/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.keyboardplaying.dailytasks;

import java.util.prefs.BackingStoreException;

import org.keyboardplaying.dailytasks.core.managers.PreferencesManager;
import org.keyboardplaying.dailytasks.ui.theme.Theme;

/**
 * This class is not properly speaking a unit test class. It is a utility to run the {@link Launcher} program with a
 * local properties file from within your IDE, without having to perform a full package.
 *
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 */
public class LauncherTestUtils {

    /**
     * Runs the application.
     * 
     * @param args
     *            optional arguments, unused
     * @throws BackingStoreException
     */
    public static void main(String[] args) throws BackingStoreException {
        PreferencesManager.clear();
        PreferencesManager.setTheme(Theme.DARK);
        PreferencesManager.setLocale("fr");
        Launcher.main();
    }
}
