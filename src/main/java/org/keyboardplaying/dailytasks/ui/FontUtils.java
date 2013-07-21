package org.keyboardplaying.dailytasks.ui;

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
	private static final String FONTS_RELATIVE_PATH = "fonts/";

	/** The name of the FontAwesome file. */
	// Using the OTF file should ensure maximal compatibility across machines
	// running Java 6
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
				Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
				// the standard font is too small, make it bigger
				fontAwesome = font.deriveFont(8F);
			} catch (FontFormatException e) {
				// font is inside the jar, this should not happen
			} catch (IOException e) {
				// font is inside the jar, this should not happen
			}
		}
	}
}
