package org.keyboardplaying.dailytasks.core;

import org.keyboardplaying.dailytasks.model.Task;

/**
 * An implementation of the {@link TaskStateController} which closes the
 * application once all the tasks have been completed.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class TaskCompletionListener extends TaskStateListener {

	/** Creates a new instance. */
	public TaskCompletionListener() {
	}

	/**
	 * Closes the application if all tasks have been completed.
	 * 
	 * @param task
	 *            {@inheritDoc}
	 */
	@Override
	protected void processTaskAfterStateSaved(Task task) {
		// Is job done?
		if (TaskManager.getInstance().areAllTasksDone()) {
			// Close the window
			getMainWindow().triggerClosingEvent();
		}
	}
}
