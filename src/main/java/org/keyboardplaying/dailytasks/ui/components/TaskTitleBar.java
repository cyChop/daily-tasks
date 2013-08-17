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
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.ui.util.FontUtils.FontAwesomeGlyph;

/**
 * A title bar for the task panel.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class TaskTitleBar extends JPanel {

	/** Generated serial version UID. */
	private static final long serialVersionUID = -6923076012298351055L;

	/** The thickness of the filler line. */
	private static final int FILLER_HEIGHT = 1;
	/** The space (in pixels) to add on each side of the filler line. */
	private static final int FILLER_MARGIN = 3;

	/**
	 * Creates a new instance.
	 * 
	 * @param icon
	 *            the icon to show on the left of the bar
	 * @param titleKey
	 *            the key to the title to show on the bar, retrieved from the
	 *            {@link MessageBundle}
	 */
	public TaskTitleBar(FontAwesomeGlyph icon, String titleKey) {
		/* The basics. */
		super();

		/* The icon. */
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(new GlyphIcon(icon));

		/* The title. */
		JLabel title = new JLabel(MessageBundle.get(titleKey));
		title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, FILLER_MARGIN));
		this.add(title);

		/* An opaque filler (1px height -> horizontal line). */
		Filler filler = new Filler(new Dimension(0, FILLER_HEIGHT),
				new Dimension(0, FILLER_HEIGHT), new Dimension(Short.MAX_VALUE,
						FILLER_HEIGHT));
		filler.setBackground(Color.BLACK);
		filler.setOpaque(true);
		this.add(filler);

		/* An "add" button. */
		GlyphButton addButton = new GlyphButton(FontAwesomeGlyph.PLUS);
		addButton.setBorder(BorderFactory.createEmptyBorder(0, FILLER_MARGIN,
				0, 0));
		this.add(addButton);
	}
}
