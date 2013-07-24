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

	/** The name of the FontAwesome OTF file. */
	// Using the OTF (CCF internally) file should ensure maximal compatibility
	// across machines running Java 7
	private static final String FONT_AWESOME_OTF = "FontAwesome-"
			+ FONT_AWESOME_VERSION + ".otf";
	/** The name of the FontAwesome TTF file. */
	// TTF fallback for Sun Java 6
	private static final String FONT_AWESOME_TTF = "FontAwesome-"
			+ FONT_AWESOME_VERSION + ".ttf";

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

	/**
	 * Creates the iconic font for glyphs.
	 * <p/>
	 * Default file should be an OTF file (CCF internally), which should work
	 * fine for all systems using Java 7. In case OTF is not supported, a fall
	 * back to a TTF file is performed.
	 */
	private synchronized static void createFontAwesome() {
		if (fontAwesome == null) {
			try {
				createFontAwesome(FONT_AWESOME_OTF, 8F);
			} catch (FontFormatException e) {

				// Sun Java 6 cannot read OTF, fall back to TTF
				createFontAwesomeTTF();

			} catch (IOException e) {
				// font is inside the jar, this should not happen
				// ugly logging just in case
				e.printStackTrace();
			}
		}
	}

	/**
	 * Fail-safe method: creates the iconic font for glyphs using a TTF version
	 * for systems not supporting OTF.
	 */
	private static void createFontAwesomeTTF() {
		try {
			createFontAwesome(FONT_AWESOME_TTF, 14F);
		} catch (FontFormatException e1) {
			// TODO TTF on Linux's Sun Java 6?
			// If yes, should provide a post script version, or a
			// fallback to graphical icons
			e1.printStackTrace();
		} catch (IOException e1) {
			// font is inside the jar, this should not happen
			// ugly logging just in case
			e1.printStackTrace();
		}
	}

	/**
	 * Creates the iconic font for glyphs.
	 * 
	 * @param fontFile
	 *            the font file to use
	 * @param size
	 *            the size magnification to use (depends on the file)
	 * @throws FontFormatException
	 *             if the fontStream data does not contain the required font
	 *             tables for the specified format
	 * @throws IOException
	 *             if the font file cannot be read
	 */
	private static void createFontAwesome(String fontFile, float size)
			throws FontFormatException, IOException {
		InputStream fontStream = FontUtils.class
				.getResourceAsStream(FONTS_RELATIVE_PATH + fontFile);
		fontAwesome = Font.createFont(Font.TRUETYPE_FONT, fontStream)
				.deriveFont(size);
	}
}
