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
	private static final long serialVersionUID = 325943211601291141L;

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
		boolean found = false;

		/* Search for the task. */
		for (Task taskInSet : tasks) {
			if (taskInSet.equals(task)) {
				/* Update the task. */
				taskInSet.setDone(done);
				found = true;
				break;
			}
		}

		/* The task does not exist in the collection. */
		if (!found) {
			// TODO throw a meaningful exception
			throw new RuntimeException();
		}
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
		updateTaskState(task, task.isDone());
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