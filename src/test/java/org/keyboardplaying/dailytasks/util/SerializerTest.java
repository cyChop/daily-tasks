/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.keyboardplaying.dailytasks.util;

import org.junit.Test;
import org.keyboardplaying.dailytasks.exception.DeserializationException;
import org.keyboardplaying.dailytasks.exception.SerializationException;
import org.keyboardplaying.dailytasks.model.DailyTask;
import org.keyboardplaying.dailytasks.model.Task;
import org.keyboardplaying.dailytasks.model.TaskSet;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the {@link Serializer} class on the objects which will be serialized when running the application.
 *
 * @author Cyrille Chopelet (https://keyboardplaying.org)
 */
@SuppressWarnings("javadoc")
public class SerializerTest {

    /**
     * Tests serialization and deserialization of {@link Task}.
     */
    @Test
    public void testTaskSerialization() throws SerializationException, DeserializationException {
        Task original = new Task("Some label", true);
        byte[] serialized = Serializer.serialize(original);
        Task deserialized = Serializer.deserialize(serialized);

        // Do not test for object equality: original and deserialized will not
        // have the same ID.
        // Make sure label and state are fine, though.
        assertEquals(original.getTodo(), deserialized.getTodo());
        assertEquals(original.isDone(), deserialized.isDone());
    }

    /**
     * Tests serialization and deserialization of dailies.
     */
    @Test
    public void testDailySerialization() throws SerializationException, DeserializationException {
        DailyTask original = new DailyTask("Some label", false);
        byte[] serialized = Serializer.serialize(original);
        DailyTask deserialized = Serializer.deserialize(serialized);

        // Do not test for object equality: original and deserialized will not
        // have the same ID.
        // Make sure label and state are fine, though.
        assertEquals(original.getTodo(), deserialized.getTodo());
        // deserialized's done should always be false.
        assertFalse(original.isDone());
        assertFalse(deserialized.isDone());

        original.setDone(true);
        serialized = Serializer.serialize(original);
        deserialized = Serializer.deserialize(serialized);

        // Do not test for object equality: original and deserialized will not
        // have the same ID.
        // Make sure label and state are fine, though.
        assertEquals(original.getTodo(), deserialized.getTodo());
        // deserialized's done should always be false.
        assertTrue(original.isDone());
        assertFalse(deserialized.isDone());
    }

    /**
     * Tests serialization and deserialization of {@link Task}.
     */
    @Test
    public void testTaskSetSerialization() throws SerializationException, DeserializationException {
        // Some randomization to make things even better...
        TaskSet original = new TaskSet();
        int nbTasks = (int) (20 * Math.random()) + 1;
        for (int i = 0; i < nbTasks; i++) {
            Task randomTask = new Task(UUID.randomUUID().toString(), Math.random() >= 0.5);
            original.addTask(randomTask);
        }

        byte[] serialized = Serializer.serialize(original);

        TaskSet result = Serializer.deserialize(serialized);

        assertEquals(original.size(), result.size());
        // Ensure all tasks have been serialized.
        Iterator<Task> iterL = original.iterator();
        Iterator<Task> iterR = result.iterator();
        while (iterL.hasNext()) {
            Task left = iterL.next();
            Task right = iterR.next();
            assertEquals(left.getTodo(), right.getTodo());
            assertEquals(left.isDone(), right.isDone());
        }
    }
}
