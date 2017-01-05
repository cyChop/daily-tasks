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

import org.keyboardplaying.dailytasks.ui.util.FontUtils;
import org.keyboardplaying.dailytasks.ui.util.FontUtils.FontAwesomeGlyph;

import javax.swing.*;

/**
 * A utility to easily display a single FontAwesome glyph as a {@link JLabel}.
 *
 * @author Cyrille Chopelet (https://keyboardplaying.org)
 */
public class GlyphIcon extends JLabel {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 6199703398032250855L;

    /**
     * The horizontal margins around this icon.
     */
    private static final int H_MARGIN = 4;
    /**
     * The vertical margins around this icon.
     */
    private static final int V_MARGIN = 0;

    /**
     * Creates a new instance.
     *
     * @param glyph the glyph this icon should show
     */
    public GlyphIcon(FontAwesomeGlyph glyph) {
        super(String.valueOf(glyph.getChar()));
        setFont(FontUtils.getFontAwesome());
        setBorder(BorderFactory.createEmptyBorder(V_MARGIN, H_MARGIN, V_MARGIN, H_MARGIN));
    }
}
