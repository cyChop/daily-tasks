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

import org.keyboardplaying.dailytasks.exception.DeserializationException;
import org.keyboardplaying.dailytasks.exception.SerializationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A utility class to convert {@link Serializable} objects to byte arrays and back.
 * <p/>
 * This class uses the {@link ObjectOutputStream} and {@link ObjectInputStream}.
 *
 * @author Cyrille Chopelet (https://keyboardplaying.org)
 */
public final class Serializer {

    /**
     * Private constructor for utility class.
     */
    private Serializer() {
    }

    /**
     * Marshals a {@link Serializable} object to a byte array.
     *
     * @param object the object to serialize
     * @return the object serialized as a byte array
     * @throws SerializationException if serialization fails
     */
    public static <T extends Serializable> byte[] serialize(T object) throws SerializationException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(output)) {

            oos.writeObject(object);
            oos.flush();

            // done, return the result
            return output.toByteArray();

        } catch (IOException e) {

            throw new SerializationException(
                    String.format("An error occurred while serializing an object of type %s: <%s>",
                            object.getClass().getName(), object.toString()),
                    e); 
        }
    }

    /**
     * Unmarshals a serialized object.
     *
     * @param serialized the serialized object
     * @return the deserialized object
     * @throws DeserializationException if deserialization fails
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deserialize(byte[] serialized) throws DeserializationException {
        try (ByteArrayInputStream input = new ByteArrayInputStream(serialized);
             ObjectInputStream ois = new ObjectInputStream(input)) {

            // done, return the result
            return (T) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {

            throw new DeserializationException(
                    String.format("An error occurred while deserializing data: <%s>", new String(serialized)), e);
        }
    }
}
