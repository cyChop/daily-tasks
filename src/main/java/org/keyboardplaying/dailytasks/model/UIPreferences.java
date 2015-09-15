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
package org.keyboardplaying.dailytasks.model;

import java.util.Locale;

import org.keyboardplaying.dailytasks.ui.theme.Theme;

/**
 * This model represents the UI preferences of the running application.
 *
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 */
public class UIPreferences {

    /** The locale used for message display. */
    private Locale locale;
    /** The UI theme. */
    private Theme theme;
    /** {@code true} to keep application on top of other windows. */
    private boolean onTop;

    /**
     * Creates a new instance.
     *
     * @param locale
     *            the locale used for message display
     * @param theme
     *            the UI theme
     * @param onTop
     *            {@code true} to keep application on top of other windows
     */
    public UIPreferences(Locale locale, Theme theme, boolean onTop) {
        super();
        this.locale = locale;
        this.theme = theme;
        this.onTop = onTop;
    }

    /**
     * Returns the locale to use for message display
     *
     * @return the locale to use for message display
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Sets the locale to use for message display.
     *
     * @param locale
     *            the locale to use for message display
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Returns the UI theme.
     *
     * @return the UI theme
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * Sets the UI theme.
     *
     * @param theme
     *            the UI theme
     */
    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    /**
     * Specifies whether application should be on top of other windows.
     *
     * @return {@code true} if application is to remain on top of other windows
     */
    public boolean isAlwaysOnTop() {
        return onTop;
    }

    /**
     * Specifies whether application should be on top of other windows.
     *
     * @param onTop
     *            {@code true} if application is to remain on top of other windows
     */
    public void setAlwaysOnTop(boolean onTop) {
        this.onTop = onTop;
    }
}
