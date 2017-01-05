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

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class to retrieve icons.
 *
 * @author Cyrille Chopelet (https://keyboardplaying.org)
 */
public final class IconUtils {

    /**
     * The extension for window icons files.
     */
    private static final String WINDOW_ICON_EXTENSION = ".png";
    /**
     * The relative path to the directory containing all icons.
     */
    private static final String ICONS_RELATIVE_PATH = "../icons/";

    /**
     * Private constructor for utility class.
     */
    private IconUtils() {
    }

    /**
     * Returns a list of all icons with this name and extension.
     * <p/>
     * The icon is selected according to what has been registered in the field {@code ApplicationWindow.icon.prefix} of
     * the UI Manager.
     *
     * @return a list of the requested icon in all available sizes
     */
    // @see org.keyboardplaying.dailytasks.ui.util.IconUtils.IconSize
    public static List<Image> getWindowIconImages() {
        List<Image> icons = new ArrayList<>();

        for (IconSize size : IconSize.values()) {
            // build path to icon
            String iconPath = ICONS_RELATIVE_PATH
                    + UIManager.get("ApplicationWindow.icon.prefix")
                    + '-'
                    + size.getIdentifier()
                    + WINDOW_ICON_EXTENSION;

            // create Image
            URL url = IconUtils.class.getResource(iconPath);
            Image icon = Toolkit.getDefaultToolkit().getImage(url);

            // store Image
            icons.add(icon);
        }

        return icons;
    }

    /**
     * The available icon sizes.
     *
     * @author Cyrille Chopelet (https://keyboardplaying.org)
     */
    protected enum IconSize {

        W_16("16"), W_32("32"), W_48("48"), W_128("128"), W_256("256");

        /**
         * The identifier suffix that characterize a size.
         */
        private String identifier;

        /**
         * Creates a new instance.
         *
         * @param identifier the identifier suffix that characterize a size
         */
        IconSize(String identifier) {
            this.identifier = identifier;
        }

        /**
         * Returns the identifier suffix that characterize a size.
         *
         * @return the identifier suffix that characterize a size
         */
        public String getIdentifier() {
            return identifier;
        }
    }
}
