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
package org.keyboardplaying.dailytasks.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.keyboardplaying.dailytasks.exception.UnexpectedException;

// XXX Javadoc
/**
 * @author cyChop (http://keyboardplaying.org/)
 */
public final class ExceptionUtils {

	/** Private constructor for utility class. */
	private ExceptionUtils() {
	}

	/**
	 * Handles an exception which was not supposed to occur by throwing an
	 * {@link UnexpectedException}.
	 * <p/>
	 * The stack trace is logged and preserved.
	 * 
	 * @param source
	 *            the object receiving the unexpected error
	 * @param throwable
	 *            the unexpected error
	 * @throws UnexpectedException
	 *             the exception, wrapping {@code throwable}
	 */
	public static void handleUnexpectedException(Object source,
			Throwable throwable) throws UnexpectedException {
		UnexpectedException e = new UnexpectedException(throwable);
		Logger.getLogger(source.getClass().getName()).log(Level.SEVERE,
				"An unexpected exception occurred.", e);
		throw e;
	}
}
