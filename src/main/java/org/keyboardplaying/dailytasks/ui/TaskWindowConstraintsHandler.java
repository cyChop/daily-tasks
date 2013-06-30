package org.keyboardplaying.dailytasks.ui;

import java.awt.GridBagConstraints;

/**
 * A class which will handle the providing of {@link GridBagConstraints} for
 * inserting lines on the {@link TaskWindow}.
 * <p/>
 * This class will provide constraints so that each check box inserted in the
 * {@link TaskWindow} appears on a separate line. To perform this, the
 * {@link #getConstraints()} method will always return the same object with an
 * incremented {@link GridBagConstraints#gridy}.
 * 
 * @author cyChop (http://keyboardplaying.org)
 */
class TaskWindowConstraintsHandler {

	/** The grid bag constraints. */
	private GridBagConstraints gbc;

	/** Creates a new instance. */
	public TaskWindowConstraintsHandler() {
		gbc = new GridBagConstraints();
		// add to next line
		gbc.gridx = 0;
		gbc.gridy = 0;
		// take all available space
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		// anchor at line beginning
		gbc.anchor = GridBagConstraints.LINE_START;
	}

	/**
	 * Returns the {@link GridBagConstraints} for inserting a new task onto the
	 * next line.
	 */
	public GridBagConstraints getConstraints() {
		gbc.gridy++;
		return gbc;
	}
}
