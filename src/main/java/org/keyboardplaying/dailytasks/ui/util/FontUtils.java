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

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

/**
 * A utility class to retrieve fonts.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public final class FontUtils {

	/** The version of FontAwesome we use. */
	public static final String FONT_AWESOME_VERSION = "3.2.1";

	/** The relative path to the directory containing all fonts. */
	private static final String FONTS_RELATIVE_PATH = "../fonts/";

	/** The name of the FontAwesome file. */
	// Using the OTF (CCF internally) file should ensure maximal compatibility
	// across machines running Java 6
	private static final String FONT_AWESOME = "FontAwesome-"
			+ FONT_AWESOME_VERSION + ".otf";

	/** The iconic font used for glyphs. */
	private static Font fontAwesome = null;

	/**
	 * Returns the iconic font used for glyphs.
	 * 
	 * @return the iconic font to use for glyphs
	 */
	public static Font getFontAwesome() {
		if (fontAwesome == null) {
			// the font has not been loaded yet
			createFontAwesome();
		}
		return fontAwesome;
	}

	/** Creates the iconic font for glyphs. */
	private synchronized static void createFontAwesome() {
		if (fontAwesome == null) {
			try {
				InputStream fontStream = FontUtils.class
						.getResourceAsStream(FONTS_RELATIVE_PATH + FONT_AWESOME);
				System.out.println(fontStream);
				// Using TYPE1 for OTF, CCF internally
				Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
				System.out.println(font);
				// the standard font is too small, make it bigger
				fontAwesome = font.deriveFont(8F);
				System.out.println(fontAwesome);
			} catch (FontFormatException e) {
				// font is inside the jar, this should not happen
				// ugly logging just in case
				e.printStackTrace();
			} catch (IOException e) {
				// font is inside the jar, this should not happen
				// ugly logging just in case
				e.printStackTrace();
			}
		}
	}
}
