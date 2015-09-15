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
 * This exception can be used where checked exceptions may be thrown but are actually not expected to happen.
 * <p/>
 * Sometimes, checked exceptions have to be declared but should never be thrown. In those case within the application,
 * we invite the developer to catch the check exception and throw an {@link UnexpectedException} instance instead.
 * <p/>
 * This object constructors oblige you to provide a {@link Throwable} because it always should be thrown in consequence
 * to another error, and the stack trace should be preserved.
 *
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 */
public class UnexpectedException extends RuntimeException {

    /** Generated serial version UID. */
    private static final long serialVersionUID = 4040061276950220381L;

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
    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new runtime exception with the specified cause and a detail message of
     * {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of cause).
     * This constructor is useful for runtime exceptions that are little more than wrappers for other throwables.
     *
     * @param cause
     *            the cause (which is saved for later retrieval by the {@link #getCause()} method)
     */
    public UnexpectedException(Throwable cause) {
        super(cause);
    }
}
