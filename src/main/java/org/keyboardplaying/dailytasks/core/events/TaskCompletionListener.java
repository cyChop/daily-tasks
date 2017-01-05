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
import org.keyboardplaying.dailytasks.ui.util.WindowUtils;

/**
 * An implementation of the {@link TaskStateListener} which closes the application once all the tasks have been
 * completed.
 *
 * @author Cyrille Chopelet (https://keyboardplaying.org)
 */
public class TaskCompletionListener extends TaskStateListener {

    /**
     * Closes the application if all tasks have been completed.
     *
     * @param task {@inheritDoc}
     */
    @Override
    protected void processTaskAfterStateSaved(Task task) {
        // Is job done?
        if (TaskManager.getInstance().areAllTasksDone()) {
            // Close the window
            WindowUtils.triggerClosingEvent(getMainWindow());
        }
    }
}
