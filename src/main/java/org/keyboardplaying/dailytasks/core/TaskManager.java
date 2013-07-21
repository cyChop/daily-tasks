package org.keyboardplaying.dailytasks.core;

import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.model.TaskSet;
import org.keyboardplaying.dailytasks.preferences.AppPreferences;

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
			instance.tasks = AppPreferences.getTasks();
		}
		return instance;
	}

	public TaskSet getTasks() {
		return this.tasks;
	}

	public void updateTask(String todo, boolean done) {
		// Retrieve the task in the list and update it
		updateTaskState(todo, done);

		// Persist data
		saveTasks();
	}

	private void updateTaskState(String todo, boolean done) {
		for (Task task : tasks) {
			if (task.getTodo().equals(todo)) {
				task.setDone(done);
				break;
			}
		}
	}

	private void saveTasks() {
		AppPreferences.setTasks(tasks);
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
