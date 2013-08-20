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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * A basic representation of a task and its state.
 * <p/>
 * The task contains an integer identifier generated upon instantiation. This ID
 * will be used to ensure equality. <strong>Warning:</strong> The IDs will not
 * be serialised and will be valid only for current session.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class Task implements Serializable {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 6770842266859911969L;

	/** A sequence used to generate a unique ID for each task. */
	private static int sequence = 0;

	/** The task's identifier. */
	private transient int id = ++sequence;
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
	 * Sets the task's label.
	 * 
	 * @param todo
	 *            the task's label
	 */
	protected void setTodo(String todo) {
		this.todo = todo;
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
	 * Returns the task's ID.
	 * 
	 * @return the task's ID
	 */
	public int getId() {
		return id;
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
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof Task && ((Task) o).getId() == this.getId();
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

	/**
	 * Invoked when deserializing an instance.
	 * 
	 * @param in
	 *            the deserializer
	 * @throws ClassNotFoundException
	 *             when the class is not found
	 * @throws IOException
	 *             when an exception occurs
	 */
	private void readObject(ObjectInputStream in)
			throws ClassNotFoundException, IOException {
		// default deserialization
		in.defaultReadObject();
		// initialize ID
		id = ++sequence;
	}
}
