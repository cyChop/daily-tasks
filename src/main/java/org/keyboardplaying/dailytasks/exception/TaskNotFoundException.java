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
package org.keyboardplaying.dailytasks.exception;

/**
 * This exception is to be thrown when a
 * {@link org.keyboardplaying.dailytasks.model.Task} instance could not be found
 * in a {@link org.keyboardplaying.dailytasks.model.TaskSet}.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class TaskNotFoundException extends Exception {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 602886821247815365L;

	/**
	 * Constructs a new exception with {@code null} as its detail message. The
	 * cause is not initialized, and may subsequently be initialized by a call
	 * to {@link #initCause()}.
	 */
	public TaskNotFoundException() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message. The cause
	 * is not initialized, and may subsequently be initialized by a call to
	 * {@link #initCause()}.
	 * 
	 * @param message
	 *            the detail message (which is saved for later retrieval by the
	 *            {@link #getMessage()} method)
	 */
	public TaskNotFoundException(String message) {
		super(message);
	}
}
