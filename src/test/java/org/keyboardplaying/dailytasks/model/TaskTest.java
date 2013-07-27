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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.keyboardplaying.dailytasks.util.Serializer;

/**
 * Tests the {@link Task} class, along with the {@link Serializer} methods.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class TaskTest {

	@Test
	public void testTaskEquals() {
		Task task1 = new Task("Common label", true);
		Task task2 = new Task("Common label", false);
		Task task3 = new Task("Different label", false);

		assertEquals(task1, task1);
		assertFalse(task1.equals(task2));
		assertFalse(task1.equals(task3));
		assertFalse(task2.equals(task3));
		assertFalse(task2.equals(null));
	}

	@Test
	public void testTaskSerialization() {
		Task original = new Task("Some label", true);
		byte[] serialized = Serializer.serialize(original);
		Task result = Serializer.deserialize(serialized);

		assertEquals(original.getTodo(), result.getTodo());
		assertEquals(original.isDone(), result.isDone());
	}
}
