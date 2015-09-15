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

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JList;

import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.ui.theme.Theme;

/**
 * A combo box to display available themes.
 *
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 */
public class ThemeComboBox extends JComboBox {

    /** Generated serial version UID. */
    private static final long serialVersionUID = 540078357947006457L;

    /** Creates a new instance. */
    public ThemeComboBox() {
        super(Theme.values());
        setRenderer(new ThemeComboBoxRenderer());
    }

    /**
     * A renderer to display themes by their localized name, applying the theme settings to each single value, thus
     * easing the choice.
     *
     * @author Cyrille Chopelet (http://keyboardplaying.org)
     */
    private class ThemeComboBoxRenderer extends PreferencesComboBoxRenderer {

        /** Generated serial version UID. */
        private static final long serialVersionUID = 8668298417035583036L;

        /*
         * (non-Javadoc)
         *
         * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing .JList, java.lang.Object, int,
         * boolean, boolean)
         */
        @Override
        public Component getListCellRendererComponent(JList list, Object theme, int index, boolean isSelected,
                boolean cellHasFocus) {
            /* Set the colors. */
            setBackground(((Theme) theme).getBgColor());
            setForeground(((Theme) theme).getTxtColor());

            /* Set the panel renderer. */
            setText(MessageBundle.get((Theme) theme));

            return this;
        }
    }
}
