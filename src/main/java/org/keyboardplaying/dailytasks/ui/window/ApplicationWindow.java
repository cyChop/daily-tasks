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
package org.keyboardplaying.dailytasks.ui.window;

import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.ui.util.IconUtils;

import javax.swing.*;
import java.awt.*;

/**
 * A standard window for all the application's screen.
 * <p/>
 * The constructor requires a key for the title in the message bundle, a unique name for this window and the content
 * pane.
 *
 * @author Cyrille Chopelet (https://keyboardplaying.org)
 */
public class ApplicationWindow extends JFrame {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -2522196280489071421L;

    /**
     * Creates a new instance.
     *
     * @param titleKey    the key for this window's title in the {@link MessageBundle}
     * @param windowName  the name of the window
     * @param contentPane the window's content
     * @param alwaysOnTop {@code true} if window should always remain on top of other windows
     */
    public ApplicationWindow(String titleKey, String windowName, Container contentPane, boolean alwaysOnTop) {
        /* Title and icon. */
        super(MessageBundle.get(titleKey));
        setIconImages(IconUtils.getWindowIconImages());

        /* Save window's name */
        setName(windowName);

        /* Make sure thread is ended on close. */
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /* General styling */
        setAlwaysOnTop(alwaysOnTop);
        setResizable(false);
        // center on screen
        setLocationRelativeTo(null);

        /* Now the content. */
        setContentPane(contentPane);

        /* Adapt size to fit the content. */
        pack();
    }
}
