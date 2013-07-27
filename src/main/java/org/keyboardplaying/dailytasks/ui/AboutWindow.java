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
package org.keyboardplaying.dailytasks.ui;

import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.model.UIPreferences;
import org.keyboardplaying.dailytasks.ui.util.FontUtils;

/**
 * A window to display informations about this application.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class AboutWindow extends AbstractWindow {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 497622675922185629L;

	/** The margin surrounding the text in the about window. */
	private static final int WINDOW_MARGIN = 20;

	/**
	 * Creates a new instance.
	 * 
	 * @param prefs
	 *            the UI preferences for this window
	 */
	public AboutWindow(UIPreferences prefs) {
		super(prefs, "app.about");

		build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.keyboardplaying.dailytasks.ui.AppWindow#buildContentPane()
	 */
	@Override
	protected Container buildContentPane() {
		/* Set the container. */
		JPanel panel = new JPanel();
		// Use a box layout
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// Looks better with an empty border
		panel.setBorder(BorderFactory.createEmptyBorder(WINDOW_MARGIN,
				WINDOW_MARGIN, WINDOW_MARGIN, WINDOW_MARGIN));

		/* Add the text to the window. */
		buildTextContent(panel);

		// Return the result
		return panel;
	}

	/**
	 * Adds the about text to the window.
	 * 
	 * @param panel
	 *            the panel that contains the text
	 */
	private void buildTextContent(JPanel panel) {
		/* A bold name and version. */
		JLabel appNameAndVersion = addLabel(panel, "app.pom.name.full");
		appNameAndVersion.setFont(appNameAndVersion.getFont().deriveFont(
				Font.BOLD));

		/* Some general information about the application. */
		addLabel(panel, "app.pom.url");
		addLabel(panel, "app.pom.description");

		addEmptyLine(panel);

		/* About the author. */
		addLabel(panel, "about.author");
		addLabel(panel, "about.keyboardplaying.url");

		addEmptyLine(panel);

		/* FontAwesome. */
		addLabel(panel, "about.fontawesome", FontUtils.FONT_AWESOME_VERSION);
		addLabel(panel, "about.fontawesome.url");
	}

	/**
	 * Adds a label from the message bundle to the panel.
	 * 
	 * @param panel
	 *            the panel
	 * @param messageKey
	 *            the key for the message to add
	 * @return the label which was added
	 */
	private JLabel addLabel(Container panel, String messageKey) {
		JLabel label = new JLabel(MessageBundle.get(messageKey));
		return addLabel(panel, label);
	}

	/**
	 * Adds a label from the message bundle to the panel and formats it with the
	 * supplied additional arguments.
	 * 
	 * @param panel
	 *            the panel
	 * @param messageKey
	 *            the key for the pattern for the message to add
	 * @param args
	 *            the additional arguments
	 * @return the label which was added
	 * 
	 * @see MessageBundle#get(String, Object...)
	 */
	private JLabel addLabel(Container panel, String messageKey, Object... args) {
		JLabel label = new JLabel(MessageBundle.get(messageKey, args));
		return addLabel(panel, label);
	}

	/**
	 * Adds an empty line.
	 * 
	 * @param panel
	 *            the panel
	 */
	private void addEmptyLine(Container panel) {
		JLabel label = new JLabel(" ");
		addLabel(panel, label);
	}

	/**
	 * Adds a label to a panel.
	 * 
	 * @param panel
	 *            the label
	 * @param label
	 *            the panel
	 * @return the label which was added
	 */
	private JLabel addLabel(Container panel, JLabel label) {
		label.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(label);
		return label;
	}
}
