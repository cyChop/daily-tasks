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
package org.keyboardplaying.dailytasks.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * A set of tasks.
 * <p/>
 * This class is designed around an ordered set for two purposes:
 * <ul>
 * <li>Using a set ensures the unicity of each task.</li>
 * <li>Being ordered, it preserves the order of tasks.</li>
 * <li>Composition was retained rather than inheritance to allow for future
 * reordering of tasks.</li>
 * </ul>
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class TaskSet implements Serializable, Iterable<Task> {

	/** Generated serial version UID. */
	private static final long serialVersionUID = -1091318559849096033L;

	/** The tasks of this instance. */
	private Set<Task> tasks = new LinkedHashSet<Task>();

	/** Creates a new instance. */
	public TaskSet() {
	}

	/**
	 * Adds a task to the collection.
	 * <p/>
	 * The call to this method will be ignored if the supplied argument is
	 * {@code null}.
	 * 
	 * @param task
	 *            the task to add to the set
	 */
	public void addTask(Task task) {
		if (task != null) {
			tasks.add(task);
		}
	}

	/**
	 * Updates the {@link Task#isDone()} value of a collection in the set.
	 * 
	 * @param task
	 *            the task to update
	 * @param done
	 *            the state to apply
	 */
	public void updateTaskState(Task task, boolean done) {
		updateTaskState(task.getId(), done);
	}

	/**
	 * Updates the {@link Task#isDone()} value of a collection in the set.
	 * 
	 * @param taskId
	 *            the id of the task to update
	 * @param done
	 *            the state to apply
	 * 
	 * @return the updated {@link Task}
	 */
	public Task updateTaskState(int taskId, boolean done) {
		Task result = null;

		/* Search for the task. */
		for (Task task : tasks) {
			if (task.getId() == taskId) {
				/* Update the task. */
				task.setDone(done);
				result = task;

				break;
			}
		}

		/* The task does not exist in the collection. */
		if (result == null) {
			// TODO throw a meaningful exception
			throw new RuntimeException();
		}

		return result;
	}

	/**
	 * Updates the {@link Task#isDone()} value of a collection in the set.
	 * <p/>
	 * The new value for the state will be the one of the passed {@link Task}.
	 * 
	 * @param task
	 *            the task to update
	 */
	public void updateTaskState(Task task) {
		updateTaskState(task.getId(), task.isDone());
	}

	/**
	 * Removes a {@link Task} from the set.
	 * 
	 * @param task
	 *            the task to remove
	 */
	public void removeTask(Task task) {
		tasks.remove(task);
	}

	/**
	 * Returns the number of elements in this set.
	 * 
	 * @return the cardinality of the set
	 */
	public int size() {
		return tasks.size();
	}

	/**
	 * Returns {@code true} if the set contains no task
	 * 
	 * @return {@code true} if the set is empty, {@code false} otherwise
	 */
	public boolean isEmpty() {
		return tasks.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Task> iterator() {
		return tasks.iterator();
	}
}