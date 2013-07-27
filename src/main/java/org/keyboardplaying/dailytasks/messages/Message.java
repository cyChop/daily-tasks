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
package org.keyboardplaying.dailytasks.messages;

/**
 * The possible error messages.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public enum Message {

	/** Message for when the look and feel could not be loaded. */
	LAF_LOADING_PROBLEM(MessageLevel.WARNING, "warning.lookandfeel"),

	/** Message for when the file could not be read. */
	ERROR_READING_FILE(MessageLevel.ERROR, "error.properties.incorrect"),

	/** Message for when the tasks could not be parsed from file. */
	ERROR_READING_TASKS(MessageLevel.ERROR, "error.tasks.incorrect");

	/** The message level. */
	private MessageLevel level;
	/** The key for the message in the bundle. */
	private String messageKey;

	/**
	 * Creates a new instance.
	 * 
	 * @param level
	 *            the message level
	 * @param message
	 *            a human-readable message
	 */
	private Message(MessageLevel level, String message) {
		this.level = level;
		this.messageKey = message;
	}

	/**
	 * Returns the message level.
	 * 
	 * @return the message level
	 */
	public MessageLevel getLevel() {
		return level;
	}

	/**
	 * Returns the key for the message in the bundle.
	 * 
	 * @return the key for the message in the bundle
	 */
	public String getKey() {
		return messageKey;
	}
}
