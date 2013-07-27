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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;

/**
 * Test class for {@link MessageBundle}.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class MessageBundleTest {

	/** The title field, used for locale testing. */
	private static final String TITLE = "app.title";
	/** French value for the title. */
	private static final String TITLE_FR = "Tâches";
	/** English value for the title. */
	private static final String TITLE_EN = "Tasks";

	/** Tests the switching of locale with or without a country specified. */
	@Test
	public void testLocaleSwitching() {
		// Do not assert content on this level, you cannot know which locale the
		// testing system has loaded.
		assertNotNull(MessageBundle.get(TITLE));

		/* Test the various locale switching. */
		// Assume the testing machine has an English locale (this should be more
		// widespread than French-localed testing machines)
		MessageBundle.setLocale(new Locale("fr", "CA"));
		assertEquals(TITLE_FR, MessageBundle.get(TITLE));
		MessageBundle.setLocale(new Locale("en", "US"));
		// Should not work without a refresh.
		assertEquals(TITLE_EN, MessageBundle.get(TITLE));
		MessageBundle.setLocale(new Locale("fr"));
		assertEquals(TITLE_FR, MessageBundle.get(TITLE));
		MessageBundle.setLocale(new Locale("en"));
		assertEquals(TITLE_EN, MessageBundle.get(TITLE));

		/* Test with some extravagant cases, just to make sure. */
		MessageBundle.setLocale(new Locale("fR", "Fr"));
		assertEquals(TITLE_FR, MessageBundle.get(TITLE));
		MessageBundle.setLocale(new Locale("EN", "uK"));
		assertEquals(TITLE_EN, MessageBundle.get(TITLE));
		MessageBundle.setLocale(new Locale("FR"));
		assertEquals(TITLE_FR, MessageBundle.get(TITLE));
		MessageBundle.setLocale(new Locale("En"));
		assertEquals(TITLE_EN, MessageBundle.get(TITLE));
	}
}
