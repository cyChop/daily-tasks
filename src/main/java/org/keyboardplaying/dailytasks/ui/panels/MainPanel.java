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
package org.keyboardplaying.dailytasks.ui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.keyboardplaying.dailytasks.model.TaskSet;
import org.keyboardplaying.dailytasks.ui.components.ApplicationToolbar;
import org.keyboardplaying.dailytasks.ui.events.TaskStateChangeListener;
import org.keyboardplaying.dailytasks.ui.window.WindowGetter;

/**
 * The panel displaying both the tasks and application toolbar.
 *
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 */
public class MainPanel extends JPanel {

    /** Generated serial version UID. */
    private static final long serialVersionUID = -8114051125473167781L;

    /**
     * Creates a new instance.
     *
     * @param getter
     *            the object in charge of getting the windows on demand
     * @param tasks
     *            the tasks to display
     * @param taskStateListener
     *            the object which listens to the tasks' state
     */
    public MainPanel(WindowGetter getter, TaskSet tasks, TaskStateChangeListener taskStateListener) {
        super(new BorderLayout());

        // Build UI
        TaskPanel taskPanel = new TaskPanel(tasks, taskStateListener);
        add(taskPanel, BorderLayout.CENTER);
        add(new ApplicationToolbar(getter), BorderLayout.EAST);
    }
}
