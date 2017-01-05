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

import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.model.TaskSet;
import org.keyboardplaying.dailytasks.ui.components.TaskCheckBox;
import org.keyboardplaying.dailytasks.ui.components.TaskTitleBar;
import org.keyboardplaying.dailytasks.ui.events.TaskStateChangeListener;
import org.keyboardplaying.dailytasks.ui.util.FontUtils.FontAwesomeGlyph;

import javax.swing.*;

/**
 * A panel to display the tasks.
 *
 * @author Cyrille Chopelet (https://keyboardplaying.org)
 */
public class TaskPanel extends JPanel {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 4237334615830374030L;

    /**
     * The width of the empty border to apply around the toolbar.
     */
    private static final int BORDER_WIDTH = 2;

    /**
     * Creates a new instance and initializes layout and content.
     *
     * @param tasks    the tasks to be displayed
     * @param listener the object which listens on task state change
     */
    // @see #initPanel(TaskSet, TaskStateChangeListener)
    public TaskPanel(TaskSet tasks, TaskStateChangeListener listener) {
        super();
        initPanel(tasks, listener);
    }

    /**
     * Initializes the toolbar layout and components.
     *
     * @param tasks    the tasks to be displayed
     * @param listener the object which listens on task state change
     */
    private void initPanel(TaskSet tasks, TaskStateChangeListener listener) {
        /* Initialize layout. */
        this.setBorder(BorderFactory.createEmptyBorder(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        TaskTitleBar title = new TaskTitleBar(FontAwesomeGlyph.PUSHPIN, "tasks.tasks");
        title.setAlignmentX(LEFT_ALIGNMENT);
        this.add(title);

        /* Now add tasks. */
        for (Task task : tasks) {
            // Create the checkbox and add it to the container.
            this.add(new TaskCheckBox(task, listener));
        }
    }
}
