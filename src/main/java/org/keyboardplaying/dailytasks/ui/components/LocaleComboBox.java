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
package org.keyboardplaying.dailytasks.ui.components;

import org.keyboardplaying.dailytasks.messages.MessageBundle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * A combo box to display all available locales.
 *
 * @author Cyrille Chopelet (https://keyboardplaying.org)
 */
public class LocaleComboBox extends JComboBox<Locale> {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 4050125723828264998L;

    /**
     * Creates a new instance displaying all available locales.
     */
    public LocaleComboBox() {
        this(MessageBundle.getAvailableLocales());
    }

    /**
     * Creates a new instance.
     *
     * @param locales the locales to display
     */
    protected LocaleComboBox(List<Locale> locales) {
        super(makeStoreFromList(locales));
        setRenderer(new LocaleComboBoxRenderer());
    }

    /**
     * Converts a list of locales into a sorted array for display.
     *
     * @param locales the list of locales to display
     * @return the store of the combo box
     */
    private static Locale[] makeStoreFromList(List<Locale> locales) {
        List<Locale> clone = new ArrayList<>(locales);
        Collections.sort(locales, (o1, o2) -> getLocaleDisplayName(o1).compareTo(getLocaleDisplayName(o2)));

        return clone.toArray(new Locale[clone.size()]);
    }

    /**
     * Returns the display name of the locale in that same locale, with an upper-case first character.
     *
     * @param locale the locale to display
     * @return the localized display name of the locale
     * @see Locale#getDisplayName()
     */
    private static String getLocaleDisplayName(Locale locale) {
        String localeName = locale.getDisplayName(locale);
        // Make sure it begins with an upper-case char
        localeName = Character.toUpperCase(localeName.charAt(0)) + localeName.substring(1);
        return localeName;
    }

    /**
     * A renderer to display Locales using {@link LocaleComboBox#getLocaleDisplayName(Locale)}.
     *
     * @author Cyrille Chopelet (https://keyboardplaying.org)
     */
    private class LocaleComboBoxRenderer extends PreferencesComboBoxRenderer<Locale> {

        /**
         * Generated serial version UID.
         */
        private static final long serialVersionUID = 5895576360090476049L;

        /*
         * (non-Javadoc)
         *
         * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int,
         * boolean, boolean)
         */
        @Override
        public Component getListCellRendererComponent(JList<? extends Locale> list, Locale locale, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            /* Set the colors */
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            /* Set the panel renderer. */
            setText(getLocaleDisplayName(locale));

            return this;
        }
    }
}
