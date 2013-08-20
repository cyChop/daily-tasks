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

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * An implementation of {@link Task} whose state is not serialized.
 * <p/>
 * Thus, at each session restoration, the state is reset to its initial state.
 * This means that, if you restart the application daily, these tasks will have
 * to be completed daily.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class DailyTask extends Task implements Externalizable {

	/**
	 * This constructor is for serialization only. Please It should not be used.
	 * 
	 * @deprecated Please use either {@link #DailyTask(String)} or
	 *             {@link #DailyTask(String, boolean)}.
	 */
	@Deprecated
	public DailyTask() {
		this(null);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param todo
	 *            the task's label
	 * @param done
	 *            {@code true} if the task is finished, {@code false} otherwise
	 */
	public DailyTask(String todo, boolean done) {
		super(todo, done);
	}

	/**
	 * Creates a new unfinished task.
	 * 
	 * @param todo
	 *            the task's label
	 */
	public DailyTask(String todo) {
		super(todo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// write only label, do not save state
		out.writeObject(getTodo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		// read the fields which were written, in the same order
		setTodo((String) in.readObject());
	}
}
