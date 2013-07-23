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
 * A list of possible levels for messages.
 * <p/>
 * The message level can be used to determine how to display the message (log
 * level, dialog type, ...).
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public enum MessageLevel {
	/** For information messages, with no impact on the application behavior. */
	INFO,
	/**
	 * For messages stating an error for which a contingency behavior has been
	 * implemented.
	 */
	WARNING,
	/**
	 * For messages stating an error which prevents the application from working
	 * properly.
	 */
	ERROR;
}
