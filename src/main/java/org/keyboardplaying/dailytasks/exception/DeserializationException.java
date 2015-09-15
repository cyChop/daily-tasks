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
package org.keyboardplaying.dailytasks.exception;

/**
 * Exception to be thrown in case an error occurs while deserializing an object.
 *
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 */
public class DeserializationException extends Exception {

    /** Generated serial version UID. */
    private static final long serialVersionUID = -4364410543589685317L;

    /**
     * Constructs a new runtime exception with the specified detail message and cause.
     * <p/>
     * Note that the detail message associated with cause is <em>not</em> automatically incorporated in this runtime
     * exception's detail message.
     *
     * @param message
     *            the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     * @param cause
     *            the cause (which is saved for later retrieval by the {@link #getCause()} method)
     */
    public DeserializationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified detail message. The cause is not initialized, and may subsequently
     * be initialized by a call to {@link #initCause(Throwable)}.
     *
     * @param message
     *            the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    public DeserializationException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified cause and a detail message of
     * {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of cause).
     * This constructor is useful for runtime exceptions that are little more than wrappers for other throwables.
     *
     * @param cause
     *            the cause (which is saved for later retrieval by the {@link #getCause()} method)
     */
    public DeserializationException(Throwable cause) {
        super(cause);
    }
}
