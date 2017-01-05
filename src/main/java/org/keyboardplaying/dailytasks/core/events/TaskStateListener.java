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
package org.keyboardplaying.dailytasks.core.events;

import org.keyboardplaying.dailytasks.core.managers.TaskManager;
import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.ui.events.TaskStateChangeListener;

import java.awt.*;

/**
 * A class to persist the tasks' states and provide appropriate processing on their state changes.
 *
 * @author Cyrille Chopelet (https://keyboardplaying.org)
 */
public abstract class TaskStateListener implements TaskStateChangeListener {

    /**
     * The window this listener controls.
     */
    private Window mainWindow;

    /**
     * Sets the window this listener controls.
     *
     * @param mainWindow the window this listener controls
     */
    public void setMainWindow(Window mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Returns the window this listener controls.
     *
     * @return the window this listener controls
     */
    protected Window getMainWindow() {
        return this.mainWindow;
    }

    /**
     * Detects a check box's check state change and updates the corresponding task accordingly.
     * <p/>
     * After saving the task's state, an additional processing can be performed.
     *
     * @param taskId {@inheritDoc}
     * @param done   {@inheritDoc}
     */
    @Override
    public void updateTaskState(int taskId, boolean done) {
        // Update task
        Task updTask = TaskManager.getInstance().updateTask(taskId, done);

        // Additional processing
        processTaskAfterStateSaved(updTask);
    }

    /**
     * Implementations should provide additional processing on the task which was just updated after it has been saved.
     *
     * @param task the task which were just updated
     */
    protected abstract void processTaskAfterStateSaved(Task task);
}
