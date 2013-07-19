package org.keyboardplaying.dailytasks.ui.todos;

import java.awt.GridBagConstraints;

/**
 * A class which will handle the providing of {@link GridBagConstraints} for
 * inserting lines on the {@link MainWindow}.
 * <p/>
 * This class will provide constraints so that each check box inserted in the
 * {@link MainWindow} appears on a separate line. To perform this, the
 * {@link #getTaskConstraints()} method will always return the same object with
 * an incremented {@link GridBagConstraints#gridy}.
 * 
 * @author cyChop (http://keyboardplaying.org)
 */
/* TODO Buttons would look nicer as a column beside the tasks. */
// No visibility modifier: this class is visible only within this package.
class MainWindowConstraintsHandler {

	/** The grid bag constraints for the tasks. */
	private GridBagConstraints tasksGbc;
	/** The grid bag constraints for the toolbar. */
	private GridBagConstraints toolbarGbc;

	/** Creates a new instance. */
	public MainWindowConstraintsHandler() {
		initTasksConstraints();
		initToolbarConstraints();
	}

	/**
	 * Initializes the {@link GridBagConstraints} which will be used for
	 * displaying the task list.
	 */
	private void initTasksConstraints() {
		tasksGbc = new GridBagConstraints();

		// Prepare first line.
		tasksGbc.gridx = 0;
		tasksGbc.gridy = 0;

		// Take all available space.
		tasksGbc.weightx = 1.0;
		tasksGbc.weighty = 1.0;
		tasksGbc.fill = GridBagConstraints.BOTH;

		// Anchor at line beginning.
		tasksGbc.anchor = GridBagConstraints.LINE_START;

		toolbarGbc = new GridBagConstraints();
	}

	/**
	 * Initializes the {@link GridBagConstraints} which will be used for
	 * displaying the toolbar.
	 */
	private void initToolbarConstraints() {
		// Prepare first column
		toolbarGbc.gridx = 0;
		toolbarGbc.gridy = 0;

		// Adapt size
		toolbarGbc.weighty = 1.0;
		toolbarGbc.fill = GridBagConstraints.VERTICAL;

		// Button should be aligned right
		toolbarGbc.anchor = GridBagConstraints.LINE_END;
	}

	/**
	 * Returns the {@link GridBagConstraints} for inserting a new button at the
	 * end of the toolbar.
	 * 
	 * @return the constraints for inserting a new button at the end of the
	 *         toolbar
	 */
	public GridBagConstraints getToolbarConstraints() {
		toolbarGbc.gridx++;
		return toolbarGbc;
	}

	/**
	 * Returns the {@link GridBagConstraints} for inserting a new task onto the
	 * next line.
	 * 
	 * @return the constraints for inserting a new task onto the next line
	 */
	public GridBagConstraints getTaskConstraints() {
		// Add 1 to Y in order to get a new line.
		tasksGbc.gridy++;
		// Return the update constraints.
		return tasksGbc;
	}
}
