package org.keyboardplaying.dailytasks.ui.todos;

import java.awt.GridBagConstraints;

/**
 * A class which will handle the providing of {@link GridBagConstraints} for
 * inserting lines on the {@link MainWindow}.
 * <p/>
 * This class will provide constraints so that each check box inserted in the
 * {@link MainWindow} appears on a separate line. To perform this, the
 * {@link #getConstraints()} method will always return the same object with an
 * incremented {@link GridBagConstraints#gridy}.
 * 
 * @author cyChop (http://keyboardplaying.org)
 */
// No visibility modifier: this class is visible only within this package.
class MainWindowConstraintsHandler {

	/** The grid bag constraints. */
	private GridBagConstraints gbc;

	/** Creates a new instance. */
	public MainWindowConstraintsHandler() {
		gbc = new GridBagConstraints();

		// Prepare first line.
		gbc.gridx = 0;
		gbc.gridy = 0;

		// Take all available space.
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;

		// Anchor at line beginning.
		gbc.anchor = GridBagConstraints.LINE_START;
	}

	/**
	 * Returns the {@link GridBagConstraints} for inserting a new task onto the
	 * next line.
	 */
	public GridBagConstraints getConstraints() {
		// Add 1 to Y in order to get a new line.
		gbc.gridy++;
		// Return the update constraints.
		return gbc;
	}
}
