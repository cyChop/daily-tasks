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

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.keyboardplaying.dailytasks.ui.util.FontUtils.FontAwesomeGlyph;
import org.keyboardplaying.dailytasks.ui.window.WindowGetter;

/**
 * The application toolbar.
 * <p/>
 * It comes with a set of buttons that blend on the background and display as
 * simple glyphs from FontAwesome.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class ApplicationToolbar extends JPanel {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 2257174746245010479L;

	/** The object in charge of getting the required windows on demand. */
	private WindowGetter getter;

	/** The width of the empty border to apply around the toolbar. */
	private static final int BORDER_WIDTH = 2;

	/**
	 * Creates a new instance and initializes layout and content.
	 * 
	 * @param getter
	 *            the object in charge of getting the windows on demand
	 * 
	 * @see #initPanel()
	 */
	public ApplicationToolbar(WindowGetter getter) {
		super();
		this.getter = getter;
		initPanel();
	}

	/** Initializes the toolbar layout and components. */
	private void initPanel() {
		/* Initialize layout. */
		this.setBorder(BorderFactory.createEmptyBorder(BORDER_WIDTH,
				BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		/* Now add buttons. */
		// Prepare styling options
		Insets btnMargins = new Insets(5, 0, 0, 0);

		// Settings
		addButtonToPanel(FontAwesomeGlyph.WRENCH, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getter.getPreferencesWindow().setVisible(true);
			}
		}, btnMargins);

		// About
		addButtonToPanel(FontAwesomeGlyph.QUESTION_SIGN, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getter.getAboutWindow().setVisible(true);
			}
		}, btnMargins);
	}

	/**
	 * Adds a button to the toolbar.
	 * <p/>
	 * The button uses a FontAwesome glyph as text. The button is transparent in
	 * order to blend on the background.
	 * 
	 * @param glyph
	 *            the glyph to display on this button
	 * @param listener
	 *            the action to be activated on button click; {@code null} if
	 *            none
	 * @param btnMargins
	 *            the margins to use when adding the buttons
	 */
	private void addButtonToPanel(FontAwesomeGlyph glyph,
			ActionListener listener, Insets btnMargins) {
		JButton btn = new GlyphButton(glyph);

		btn.addActionListener(listener);

		btn.setMargin(btnMargins);
		this.add(btn);
	}
}