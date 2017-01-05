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
package org.keyboardplaying.dailytasks.ui.util;

import org.keyboardplaying.dailytasks.util.ExceptionUtils;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * A utility class to retrieve fonts.
 *
 * @author Cyrille Chopelet (https://keyboardplaying.org)
 */
public final class FontUtils {

    /**
     * The version of FontAwesome we use.
     */
    public static final String FONT_AWESOME_VERSION = "3.2.1";

    /**
     * The relative path to the directory containing all fonts.
     */
    private static final String FONTS_RELATIVE_PATH = "../fonts/";

    /**
     * The name of the FontAwesome OTF file.
     */
    // Using the OTF (CCF internally) file should ensure maximal compatibility across machines running Java 7
    private static final String FONT_AWESOME_OTF = "FontAwesome-" + FONT_AWESOME_VERSION + ".otf";
    /**
     * The name of the FontAwesome TTF file.
     */
    // TODO obsolete! TTF fallback for Sun Java 6
    private static final String FONT_AWESOME_TTF = "FontAwesome-" + FONT_AWESOME_VERSION + ".ttf";
    /**
     * The font size to apply when using the OTF file.
     */
    private static final float FONT_SIZE_OTF = 8F;
    /**
     * The font size to apply when using the TTF file.
     */
    private static final float FONT_SIZE_TTF = 14F;

    /**
     * The iconic font used for glyphs.
     */
    private static Font fontAwesome = null;

    /**
     * A list of FontAwesome glyphs which can be used in the application.
     *
     * @author Cyrille Chopelet (https://keyboardplaying.org)
     */
    public enum FontAwesomeGlyph {

        /**
         * A pushpin icon.
         */
        PUSHPIN('\uf08d'), /**
         * A wrench icon.
         */
        WRENCH('\uf0ad'), /**
         * A question mark in a round sign.
         */
        QUESTION_SIGN('\uf059'), /**
         * A plus icon.
         */
        PLUS('\uf067');

        /**
         * The character corresponding to this glyph in Font Awesome's font.
         */
        private char character;

        /**
         * Creates a new instance.
         *
         * @param character the character corresponding to this glyph
         */
        FontAwesomeGlyph(char character) {
            this.character = character;
        }

        /**
         * Returns the character corresponding to this glyph.
         *
         * @return the character corresponding to this glyph
         */
        public char getChar() {
            return character;
        }

        /**
         * Returns the character from the Font Awesome font to be used when this glyph is wished
         *
         * @return the character corresponding to this glyph
         */
        @Override
        public String toString() {
            return String.valueOf(character);
        }
    }

    /**
     * Private constructor for utility class.
     */
    private FontUtils() {
    }

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
     * Default file should be an OTF file (CCF internally), which should work fine for all systems using Java 7. In case
     * OTF is not supported, a fall back to a TTF file is performed.
     */
    private static synchronized void createFontAwesome() {
        if (fontAwesome == null) {
            try {
                createFontAwesome(FONT_AWESOME_OTF, FONT_SIZE_OTF);
            } catch (FontFormatException e) {

                // Sun Java 6 cannot read OTF, fall back to TTF
                createFontAwesomeTTF();

            } catch (IOException e) {
                // font is inside the jar, this should not happen
                // ugly logging just in case
                ExceptionUtils.handleUnexpectedException(FontUtils.class, e);
            }
        }
    }

    /**
     * Fail-safe method: creates the iconic font for glyphs using a TTF version for systems not supporting OTF.
     */
    private static void createFontAwesomeTTF() {
        try {
            createFontAwesome(FONT_AWESOME_TTF, FONT_SIZE_TTF);
        } catch (FontFormatException | IOException e) {
            // If yes, should provide a post script version, or a
            // fallback to graphical icons
            ExceptionUtils.handleUnexpectedException(FontUtils.class, e);
        }
    }

    /**
     * Creates the iconic font for glyphs.
     *
     * @param fontFile the font file to use
     * @param size     the size magnification to use (depends on the file)
     * @throws FontFormatException if the fontStream data does not contain the required font tables for the specified format
     * @throws IOException         if the font file cannot be read
     */
    private static void createFontAwesome(String fontFile, float size) throws FontFormatException, IOException {
        InputStream fontStream = FontUtils.class.getResourceAsStream(FONTS_RELATIVE_PATH + fontFile);
        fontAwesome = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(size);
    }
}
