package org.keyboardplaying.dailytasks.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.keyboardplaying.dailytasks.core.TaskManager;
import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.ui.components.todos.TaskPanel;

/**
 * The main window of the application. It displays the tasks.
 * <p/>
 * This window will only close once all tasks have been checked.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class ApplicationWindow extends AbstractWindow implements ActionListener {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 891615403231436155L;

	/** The panel displaying the tasks. */
	private TaskPanel taskPanel;

	/** Creates a new instance. */
	public ApplicationWindow() {
		super("app.title");

		// Build UI
		taskPanel = new TaskPanel(TaskManager.getInstance().getTasks(), this);
		build();
	}

	public void run() {
		this.setVisible(true);
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

		/* Add the content. */
		panel.add(taskPanel, BorderLayout.CENTER);
		panel.add(new Toolbar(), BorderLayout.EAST);

		/* Return the result. */
		return panel;
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * This implementation is supposed to be called only when the check state of
	 * the check boxes representing the tasks.
	 * 
	 * @param e
	 *            {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JCheckBox cb = (JCheckBox) e.getSource();

		// Update task
		TaskManager taskManager = TaskManager.getInstance();
		taskManager.updateTask(cb.getText(), cb.isSelected());

		// Is job done?
		if (taskManager.areAllTasksDone()) {
			// Triggers a window closing signal
			WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JFrame#processWindowEvent(java.awt.event.WindowEvent)
	 */
	@Override
	protected void processWindowEvent(WindowEvent e) {

		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			if (TaskManager.getInstance().areAllTasksDone()) {

				closeWindowAndTermineProgram(e);

			} else {

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
		// Terminate program (make sure all other windows are closed)
		System.exit(0);
	}
}
