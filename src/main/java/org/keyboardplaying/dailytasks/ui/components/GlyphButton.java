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
package org.keyboardplaying.dailytasks.ui.components;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.UIManager;

import org.keyboardplaying.dailytasks.ui.util.FontUtils;
import org.keyboardplaying.dailytasks.ui.util.FontUtils.FontAwesomeGlyph;

/**
 * A button displaying only a glyph from the Font Awesome iconic font. This
 * button is designed to display this icon the same way as standard text and
 * blend into its background.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class GlyphButton extends JButton {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 6819853527671593836L;

	/**
	 * Creates a new instance.
	 * 
	 * @param glyph
	 *            the glyph to display
	 */
	public GlyphButton(FontAwesomeGlyph glyph) {
		super(glyph.toString());
		/* Load Font Awesome to display a glyph. */
		setFont(FontUtils.getFontAwesome());
		/* Apply theme color to glyph. */
		setForeground((Color) UIManager.get("GlyphButton.foreground"));

		/*
		 * Make it transparent so that it blends into the background and show
		 * only the icon as text.
		 */
		setContentAreaFilled(false);
		setBorderPainted(false);
	}
}
