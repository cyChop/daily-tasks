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
package org.keyboardplaying.dailytasks.ui.panels;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.ui.util.FontUtils;

/**
 * A window to display informations about this application.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class AboutPanel extends JPanel {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 6838253013008799433L;

	/** The margin surrounding the text in the about window. */
	private static final int WINDOW_MARGIN = 20;

	/** Creates a new instance. */
	public AboutPanel() {

		/* Set the container. */
		super();
		// Use a box layout to stack items vertically
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		/* Styling */
		// Looks better with an empty border
		setBorder(BorderFactory.createEmptyBorder(WINDOW_MARGIN, WINDOW_MARGIN,
				WINDOW_MARGIN, WINDOW_MARGIN));

		/* Add the text to the window. */
		buildTextContent();
	}

	/** Adds the about text to the panel. */
	private void buildTextContent() {
		/* A bold name and version. */
		JLabel appNameAndVersion = addLabel("app.pom.name.full");
		appNameAndVersion.setFont(appNameAndVersion.getFont().deriveFont(
				Font.BOLD));

		/* Some general information about the application. */
		addLabel("app.pom.url");
		addLabel("app.pom.description");

		addEmptyLine();

		/* About the author. */
		addLabel("about.author");
		addLabel("about.keyboardplaying.url");

		addEmptyLine();

		/* FontAwesome. */
		addLabel("about.fontawesome", FontUtils.FONT_AWESOME_VERSION);
		addLabel("about.fontawesome.url");
	}

	/**
	 * Adds a label from the message bundle to the panel.
	 * 
	 * @param messageKey
	 *            the key for the message to add
	 * @return the label which was added
	 */
	private JLabel addLabel(String messageKey) {
		JLabel label = new JLabel(MessageBundle.get(messageKey));
		return addLabel(label);
	}

	/**
	 * Adds a label from the message bundle to the panel and formats it with the
	 * supplied additional arguments.
	 * 
	 * @param messageKey
	 *            the key for the pattern for the message to add
	 * @param args
	 *            the additional arguments
	 * @return the label which was added
	 * 
	 * @see MessageBundle#get(String, Object...)
	 */
	private JLabel addLabel(String messageKey, Object... args) {
		JLabel label = new JLabel(MessageBundle.get(messageKey, args));
		return addLabel(label);
	}

	/** Adds an empty line. */
	private void addEmptyLine() {
		JLabel label = new JLabel(" ");
		addLabel(label);
	}

	/**
	 * Adds a label to a panel.
	 * 
	 * @param label
	 *            the panel
	 * @return the label which was added
	 */
	private JLabel addLabel(JLabel label) {
		label.setAlignmentX(CENTER_ALIGNMENT);
		add(label);
		return label;
	}
}
