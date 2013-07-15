package org.keyboardplaying.dailytasks.model;

import java.io.Serializable;

/**
 * A basic representation of a task and its state.
 * <p/>
 * The task's label is used as an identifier (two tasks with the same label will
 * be considered to be equal).
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class Task implements Serializable {

	/** Generated serial version UID. */
	private static final long serialVersionUID = -7606638946156111184L;

	/** The task's label and identifier. */
	private String todo;
	/** The task state (finished or not). */
	private boolean done;

	/**
	 * Creates a new instance.
	 * 
	 * @param todo
	 *            the task's label
	 * @param done
	 *            {@code true} if the task is finished, {@code false} otherwise
	 */
	public Task(String todo, boolean done) {
		this.todo = todo;
		this.done = done;
	}

	/**
	 * Creates a new unfinished task.
	 * 
	 * @param todo
	 *            the task's label
	 */
	public Task(String todo) {
		this(todo, false);
	}

	/**
	 * Returns the task's label.
	 * 
	 * @return the task's label
	 */
	public String getTodo() {
		return todo;
	}

	/**
	 * Returns the task's state.
	 * 
	 * @return {@code true} if the task is finished, {@code false} otherwise
	 */
	public boolean isDone() {
		return done;
	}

	/**
	 * Sets the task's state.
	 * 
	 * @param done
	 *            {@code true} if the task is finished, {@code false} otherwise
	 */
	public void setDone(boolean done) {
		this.done = done;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hashcode = 0;
		if (this.getTodo() != null) {
			hashcode = this.getTodo().hashCode();
		}
		return hashcode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		boolean result = false;

		if (o == null) {
			result = this.getTodo() == null;
		} else if (o instanceof Task) {
			result = this.getTodo() == null && ((Task) o).getTodo() == null
					|| this.getTodo().equals(((Task) o).getTodo());
		} else if (o instanceof String) {
			result = o.equals(this.getTodo());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + (isDone() ? "X" : " ") + "] " + getTodo();
	}
}
