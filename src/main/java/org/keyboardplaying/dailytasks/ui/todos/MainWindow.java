package org.keyboardplaying.dailytasks.ui.todos;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.ui.AppWindow;
import org.keyboardplaying.dailytasks.ui.MessageBundle;
import org.keyboardplaying.dailytasks.ui.toolbar.AppToolbar;

/**
 * The main window of the application. It displays the tasks.
 * <p/>
 * This window will only close once all tasks have been checked.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class MainWindow extends AppWindow implements ActionListener {

	/** Generated serial version UID. */
	private static final long serialVersionUID = -3510718848122843486L;

	/** The tasks displayed in this window. */
	private Collection<Task> tasks;

	/**
	 * Creates a new instance.
	 * 
	 * @param tasks
	 *            the tasks this window should display
	 */
	public MainWindow(Collection<Task> tasks) {
		super("app.title");

		// Save parameters
		this.tasks = tasks;

		build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.keyboardplaying.dailytasks.ui.AppWindow#buildContentPane()
	 */
	@Override
	protected Container buildContentPane() {
		/* Set the container. */
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		/* Prepare panel constraints. */
		JPanel taskPanel = new JPanel();
		taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));

		/* Add buttons. */

		/* Add tasks. */
		for (Task task : tasks) {
			addTaskToPanel(taskPanel, task);
		}

		panel.add(taskPanel, BorderLayout.CENTER);
		panel.add(new AppToolbar(), BorderLayout.EAST);
		// Return the result
		return panel;
	}

	/**
	 * Adds a check box representing the supplied task to the panel.
	 * 
	 * @param panel
	 *            the panel to add the checkbox to
	 * @param task
	 *            the task to display as a check box
	 */
	private void addTaskToPanel(Container panel, Task task) {
		/* Create the checkbox. */
		JCheckBox cb = new JCheckBox(task.getTodo(), task.isDone());
		// blend checkbox into the pane
		cb.setOpaque(false);
		cb.addActionListener(this);

		/* Add the line to the container. */
		panel.add(cb);
	}

	/*
	 * (Non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/* Update task. */
		updateTaskState((JCheckBox) e.getSource());

		/* Close if everything is done. */
		if (areAllTasksDone()) {
			triggerWindowClosingSignal();
		}
	}

	/**
	 * Updates the state of the task associated with the supplied check box.
	 * 
	 * @param cb
	 *            the check box associated with the task to update
	 */
	private void updateTaskState(JCheckBox cb) {
		String todo = cb.getText();
		boolean done = cb.isSelected();

		// Retrieve the task in the list and update it
		for (Task task : tasks) {
			if (task.equals(todo)) {
				task.setDone(done);
				break;
			}
		}
	}

	/**
	 * Checks if all tasks are completed.
	 * 
	 * @return {@code true} if all tasks were completed, {@code false} otherwise
	 */
	private boolean areAllTasksDone() {
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

	/** Triggers a signal to close the window. */
	private void triggerWindowClosingSignal() {
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JFrame#processWindowEvent(java.awt.event.WindowEvent)
	 */
	@Override
	protected void processWindowEvent(WindowEvent e) {

		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			if (!areAllTasksDone()) {

				/*
				 * For window closing, if some tasks are not yet completed, ask
				 * for confirmation.
				 */
				int exit = JOptionPane.showConfirmDialog(this,
						MessageBundle.get("confirm.skip.unfinished.body"),
						MessageBundle.get("confirm.skip.unfinished.title"),
						JOptionPane.YES_NO_OPTION);
				if (exit == JOptionPane.YES_OPTION) {
					closeWindowAndTermineProgram(e);
				}
			} else {
				closeWindowAndTermineProgram(e);
			}

		} else {

			/*
			 * All other events, including closing when all tasks are completed,
			 * are processed the usual way.
			 */
			super.processWindowEvent(e);
		}
	}

	/**
	 * Closes this window and any other open by terminating the program.
	 * 
	 * @param e
	 *            the closing window event
	 */
	private void closeWindowAndTermineProgram(WindowEvent e) {
		super.processWindowEvent(e);
		// Terminate program
		System.exit(0);
	}
}
