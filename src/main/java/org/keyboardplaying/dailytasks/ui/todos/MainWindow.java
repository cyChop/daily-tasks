package org.keyboardplaying.dailytasks.ui.todos;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.ui.AppWindow;
import org.keyboardplaying.dailytasks.ui.FontUtils;
import org.keyboardplaying.dailytasks.ui.MessageBundle;
import org.keyboardplaying.dailytasks.ui.about.AboutWindow;

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
		panel.setLayout(new GridBagLayout());

		/* Prepare panel constraints. */
		MainWindowConstraintsHandler ch = new MainWindowConstraintsHandler();

		/* Add buttons. */
		Insets btnMargins = new Insets(7, 0, 3, 1);
		// wrench -> settings
		addButtonToPanel(panel, "\uf0ad", null, ch, btnMargins);
		// question-sign -> about
		addButtonToPanel(panel, "\uf059", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final AboutWindow aboutWindow = new AboutWindow();
				aboutWindow.setVisible(true);
			}
		}, ch, btnMargins);

		/* Add tasks. */
		for (Task task : tasks) {
			addTaskToPanel(panel, task, ch);
		}

		// Return the result
		return panel;
	}

	/**
	 * Adds a button to the toolbar.
	 * <p/>
	 * The button uses a FontAwesome glyph, and is transparent, blended on the
	 * panel.
	 * 
	 * @param panel
	 *            the {@link Container} for the panel
	 * @param btnText
	 *            the text to display on this button; this should be one unicode
	 *            character recognized in FontAwesome
	 * @param listener
	 *            the action to be activated on button click; {@code null} if
	 *            none
	 * @param ch
	 *            the handler accountable for the handling of constraints when
	 *            adding a new component to the {@link GridBagLayout}
	 * @param btnMargins
	 *            the margins to use when adding the buttons
	 */
	private void addButtonToPanel(JPanel panel, String btnText,
			ActionListener listener, MainWindowConstraintsHandler ch,
			Insets btnMargins) {
		JButton btn = new JButton(btnText);
		btn.setMargin(btnMargins);

		/* Apply theme. */
		btn.setForeground(getTheme().getTxtColor());

		/* Makes the button blend in the background. */
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);

		if (listener != null) {
			btn.addActionListener(listener);
		}

		/* Use FontAwesome. */
		btn.setFont(FontUtils.getFontAwesome());
		panel.add(btn, ch.getToolbarConstraints());
	}

	/**
	 * Adds a check box representing the supplied task to the panel.
	 * 
	 * @param panel
	 *            the {@link Container} for the panel
	 * @param task
	 *            the task to display as a check box
	 * @param ch
	 *            the handler accountable for the handling of constraints when
	 *            adding a new component to the {@link GridBagLayout}
	 */
	private void addTaskToPanel(Container panel, Task task,
			MainWindowConstraintsHandler ch) {
		/* Create the checkbox. */
		JCheckBox cb = new JCheckBox(task.getTodo(), task.isDone());
		// blend checkbox into the pane
		cb.setOpaque(false);
		cb.addActionListener(this);

		/* Add the line to the container. */
		panel.add(cb, ch.getTaskConstraints());
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
