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
package org.keyboardplaying.dailytasks.core;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.keyboardplaying.dailytasks.core.events.ApplicationClosingListener;
import org.keyboardplaying.dailytasks.core.managers.PreferencesManager;
import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.model.UIPreferences;
import org.keyboardplaying.dailytasks.ui.events.ApplicationController;
import org.keyboardplaying.dailytasks.ui.theme.ThemeManager;
import org.keyboardplaying.dailytasks.ui.util.WindowUtils;
import org.keyboardplaying.dailytasks.ui.window.WindowGetter;

/**
 * The application class.
 * <p/>
 * This class is the main controller for the windows. It is not logic-rich though.
 *
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 */
public class Application implements ApplicationController {

    /** The object in charge of creating the windows on demand. */
    private WindowGetter getter;

    /** Creates a new instance. */
    public Application() {
        super();
    }

    /**
     * Sets the window getter.
     *
     * @param getter
     *            the object in charge of retrieving the window
     */
    public void setWindowGetter(WindowGetter getter) {
        this.getter = getter;
    }

    /** Applies the UI preferences and starts the application. */
    public void start() {
        /* Load the application settings. */
        UIPreferences prefs = PreferencesManager.getUIPreferences();
        ThemeManager.applyTheme(prefs.getTheme());
        MessageBundle.setLocale(prefs.getLocale());

        /* Run application */
        // Make main window.
        JFrame window = getter.getMainWindow();
        // Add control for window closing.
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new ApplicationClosingListener(this));
        // Window is ready, show it.
        window.setVisible(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.keyboardplaying.dailytasks.ui.events.ApplicationController#restart()
     */
    @Override
    public void restart() {
        disposeAllWindows();
        // Launch anew
        start();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.keyboardplaying.dailytasks.ui.events.ApplicationController#terminate ()
     */
    @Override
    public void terminate() {
        disposeAllWindows();
    }

    /**
     * Disposes all windows to release memory so that they can be launched anew if need be.
     */
    private void disposeAllWindows() {
        WindowUtils.disposeAllWindows();
    }
}
