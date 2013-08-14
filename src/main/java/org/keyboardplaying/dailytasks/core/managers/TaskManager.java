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
package org.keyboardplaying.dailytasks.core.managers;

import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.model.TaskSet;

/**
 * Provides utilities to manage a {@link TaskSet}.
 * <p/>
 * This class implements the Singleton design pattern and will handle only one
 * task set.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class TaskManager {

	/** The single instance of the {@link TaskManager} class. */
	private static TaskManager instance = null;

	/** The tasks the unique instance of this class will manage. */
	private TaskSet tasks;

	/**
	 * Returns the single instance of the {@link TaskManager} class.
	 * <p/>
	 * This class implements the Singleton design-pattern.
	 * 
	 * @return the single instance of this class
	 */
	public static synchronized TaskManager getInstance() {
		if (instance == null) {
			instance = new TaskManager();
			instance.tasks = PreferencesManager.getTasks();
		}
		return instance;
	}

	/**
	 * Returns the set of tasks.
	 * 
	 * @return the set of tasks
	 */
	public TaskSet getTasks() {
		return this.tasks;
	}

	/**
	 * Updates the state of a task in the set and persists changes in
	 * preferences for retrieval in the next session.
	 * 
	 * @param taskId
	 *            the ID of the task to update
	 * @param done
	 *            the completion state of the task
	 * @return the updated task
	 */
	public Task updateTask(int taskId, boolean done) {
		// Retrieve the task in the list and update it
		Task task = updateTaskState(taskId, done);

		// Persist data
		saveTasks();

		return task;
	}

	/**
	 * Updates the state of a task in the set.
	 * 
	 * @param taskId
	 *            the ID of the task to update
	 * @param done
	 *            the completion state of the task
	 * @return the updated task
	 */
	private Task updateTaskState(int taskId, boolean done) {
		return tasks.updateTaskState(taskId, done);
	}

	/** Updates the tasks in the preferences (between-sessions persistence). */
	private void saveTasks() {
		PreferencesManager.setTasks(tasks);
	}

	/**
	 * Tests whether all tasks are complete.
	 * 
	 * @return {@code true} if all tasks are in a complete state
	 */
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

	/**
	 * Creates a default {@link TaskSet} containing only one {@link Task}
	 * proposing to create more.
	 * 
	 * @return the default {@link TaskSet}
	 */
	public static TaskSet getDefaultTaskSet() {
		TaskSet set = new TaskSet();
		set.addTask(new Task(MessageBundle.get("task.default")));
		return set;
	}
}
