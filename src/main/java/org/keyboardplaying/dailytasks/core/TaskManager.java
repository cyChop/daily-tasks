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
package org.keyboardplaying.dailytasks.core;

import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.model.TaskSet;

// XXX Javadoc
/**
 * @author cyChop (http://keyboardplaying.org/)
 */
public class TaskManager {

	private static TaskManager instance = null;

	private TaskSet tasks;

	public static synchronized TaskManager getInstance() {
		if (instance == null) {
			instance = new TaskManager();
			instance.tasks = PreferencesManager.getTasks();
		}
		return instance;
	}

	public TaskSet getTasks() {
		return this.tasks;
	}

	public Task updateTask(int taskId, boolean done) {
		// Retrieve the task in the list and update it
		Task task = updateTaskState(taskId, done);

		// Persist data
		saveTasks();

		return task;
	}

	private Task updateTaskState(int taskId, boolean done) {
		return tasks.updateTaskState(taskId, done);
	}

	private void saveTasks() {
		PreferencesManager.setTasks(tasks);
	}

	public boolean areAllTasksDone() {
		/* Checks the state of all tasks. */
		boolean allTasksDone = true;
		for (Task task : tasks) {
			if (!task.isDone()) {
				allTasksDone = false;
				break;
			}
		}
		return allTasksDone;
	}
}
