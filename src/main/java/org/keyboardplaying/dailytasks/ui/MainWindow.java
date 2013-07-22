package org.keyboardplaying.dailytasks.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

import org.keyboardplaying.dailytasks.model.TaskSet;
import org.keyboardplaying.dailytasks.ui.components.todos.TaskPanel;

/**
 * The main window of the application. It displays the tasks and the main tool
 * bar.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class MainWindow extends AbstractWindow {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 5931987982513289624L;

	/** The panel displaying the tasks. */
	private TaskPanel taskPanel;

	/**
	 * Creates a new instance.
	 * 
	 * @param tasks
	 *            the tasks to display
	 * @param taskStateListener
	 *            the listener which should process the task's state change
	 */
	public MainWindow(TaskSet tasks, ActionListener taskStateListener) {
		super("app.title");

		// Build UI
		taskPanel = new TaskPanel(tasks, taskStateListener);
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

		/* Add the content. */
		panel.add(taskPanel, BorderLayout.CENTER);
		panel.add(new Toolbar(), BorderLayout.EAST);

		/* Return the result. */
		return panel;
	}

	/** Emulates the click on the 'close' button of this window. */
	public void triggerClosingEvent() {
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}
