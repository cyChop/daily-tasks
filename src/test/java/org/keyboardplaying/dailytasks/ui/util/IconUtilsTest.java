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
package org.keyboardplaying.dailytasks.ui.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.awt.Image;
import java.util.List;

import org.junit.Test;
import org.keyboardplaying.dailytasks.ui.theme.Theme;
import org.keyboardplaying.dailytasks.ui.theme.ThemeManager;

/**
 * Tests for the {@link IconUtils} class.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class IconUtilsTest {

	/** Ensures {@link IconUtils} is still capable of retrieving icons. */
	@Test
	public void testIconFetching() {
		// Set the UI manager
		ThemeManager.applyTheme(Theme.ORANGE);

		// Test
		List<Image> defaultWindowIcon = IconUtils.getWindowIconImages();
		assertNotNull(defaultWindowIcon);
		assertFalse(defaultWindowIcon.isEmpty());
	}
}
