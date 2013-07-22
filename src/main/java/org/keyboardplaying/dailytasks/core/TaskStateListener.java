package org.keyboardplaying.dailytasks.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.ui.MainWindow;

/**
 * A class to persist the tasks' states and provide appropriate processing on
 * their state changes.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public abstract class TaskStateListener implements ActionListener {

	/** The window this listener controls. */
	private MainWindow mainWindow;

	/**
	 * Creates a new instance.
	 * 
	 * @param mainWindow
	 *            the window this listener controls
	 */
	public TaskStateListener() {
	}

	/**
	 * Sets the window this listener controls.
	 * 
	 * @param mainWindow
	 *            the window this listener controls
	 */
	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	/**
	 * Returns the window this listener controls.
	 * 
	 * @return the window this listener controls
	 */
	protected MainWindow getMainWindow() {
		return this.mainWindow;
	}

	/**
	 * Detects a check box's check state change and updates the corresponding
	 * task accordingly.
	 * <p/>
	 * After saving the task's state, an additional processing can be performed.
	 * 
	 * @param e
	 *            {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JCheckBox cb = (JCheckBox) e.getSource();

		// Update task
		Task task = TaskManager.getInstance().updateTask(cb.getText(),
				cb.isSelected());

		// Additional processing
		processTaskAfterStateSaved(task);
	}

	/**
	 * Implementations should provide additional processing on the task which
	 * was just updated after it has been saved.
	 * 
	 * @param task
	 *            the task which were just updated
	 */
	protected abstract void processTaskAfterStateSaved(Task task);
}
