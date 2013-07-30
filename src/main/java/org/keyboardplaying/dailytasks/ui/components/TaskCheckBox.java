/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.keyboardplaying.dailytasks.ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.ui.events.TaskStateChangeListener;

/**
 * A check box to represent {@link Task} instances.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class TaskCheckBox extends JCheckBox {

	/** Generated serial version UID. */
	private static final long serialVersionUID = -2493794753510466163L;

	/** The ID of the task this check box represents. */
	private int taskId;

	/**
	 * Creates new instance.
	 * 
	 * @param task
	 *            the task this check box represents
	 * @param stateListener
	 *            the listener which should be warned on task's state change
	 */
	public TaskCheckBox(Task task, TaskStateChangeListener stateListener) {
		// save fields
		super(task.getTodo(), task.isDone());
		this.taskId = task.getId();

		// action listener
		this.addActionListener(new TaskCheckBoxListener(stateListener));
	}

	/**
	 * An action listener to monitor the changes of the box's check state.
	 * <p/>
	 * Any instance of this class will consider the change of check state as a
	 * change of the task's state and will relay the information to the attached
	 * {@link TaskStateChangeListener}.
	 * <p/>
	 * This class is private to avoid any other class instantiating it.
	 * 
	 * @author cyChop (http://keyboardplaying.org/)
	 */
	private static class TaskCheckBoxListener implements ActionListener {

		/** The related {@link TaskStateChangeListener}. */
		private TaskStateChangeListener stateListener;

		/**
		 * Creates a new instance.
		 * 
		 * @param stateListener
		 *            the listener which should be warned on task's state change
		 */
		private TaskCheckBoxListener(TaskStateChangeListener stateListener) {
			super();
			this.stateListener = stateListener;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (stateListener != null) {
				TaskCheckBox cb = (TaskCheckBox) e.getSource();
				stateListener.updateTaskState(cb.taskId, cb.isSelected());
			}
		}
	}
}
