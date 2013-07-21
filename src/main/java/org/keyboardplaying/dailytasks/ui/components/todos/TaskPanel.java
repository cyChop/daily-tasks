package org.keyboardplaying.dailytasks.ui.components.todos;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.model.TaskSet;

/**
 * A panel to display the tasks.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class TaskPanel extends JPanel {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 6314379694565949226L;

	/** The width of the empty border to apply around the toolbar. */
	private static final int BORDER_WIDTH = 2;

	/**
	 * Creates a new instance and initializes layout and content.
	 * 
	 * @param tasks
	 *            the tasks to be displayed
	 * @param listener
	 *            the object which listens on task state change
	 * 
	 * @see #initPanel()
	 */
	public TaskPanel(TaskSet tasks, ActionListener listener) {
		super();
		initPanel(tasks, listener);
	}

	/**
	 * Initializes the toolbar layout and components.
	 * 
	 * @param tasks
	 *            the tasks to be displayed
	 * @param listener
	 *            the object which listens on task state change
	 */
	private void initPanel(TaskSet tasks, ActionListener listener) {
		/* Initialize layout. */
		this.setBorder(BorderFactory.createEmptyBorder(BORDER_WIDTH,
				BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		/* Now add tasks. */
		for (Task task : tasks) {
			addTaskToPanel(task, listener);
		}
	}

	/**
	 * Adds a check box representing the supplied task to the panel.
	 * 
	 * @param task
	 *            the task to display as a check box
	 * @param listener
	 *            the object which listens on task state change
	 */
	private void addTaskToPanel(Task task, ActionListener listener) {
		/* Create the checkbox. */
		JCheckBox cb = new JCheckBox(task.getTodo(), task.isDone());
		// blend checkbox into the pane
		cb.setOpaque(false);
		cb.addActionListener(listener);

		/* Add the line to the container. */
		this.add(cb);
	}
}
