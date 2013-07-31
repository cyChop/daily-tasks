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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.model.UIPreferences;
import org.keyboardplaying.dailytasks.ui.components.LocaleComboBox;
import org.keyboardplaying.dailytasks.ui.components.ThemeComboBox;
import org.keyboardplaying.dailytasks.ui.events.UIPreferencesChangeListener;

// XXX Javadoc
/**
 * @author cyChop (http://keyboardplaying.org/)
 */
public class PreferencesPanel extends JPanel {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 5000324938317224037L;

	private ThemeComboBox themeCB;
	private LocaleComboBox localeCB;
	private JCheckBox onTopCheckBox;

	public PreferencesPanel(UIPreferences preferences,
			UIPreferencesChangeListener listener) {
		super();
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);

		themeCB = new ThemeComboBox();
		themeCB.setSelectedItem(preferences.getTheme());
		themeCB.setBackground(preferences.getTheme().getBgColor());
		JLabel themeLabel = new JLabel(MessageBundle.get("pref.theme"));
		themeLabel.setLabelFor(themeCB);

		localeCB = new LocaleComboBox();
		localeCB.setSelectedItem(MessageBundle
				.getClosestApplicableLocale(preferences.getLocale()));
		JLabel localeLabel = new JLabel(MessageBundle.get("pref.lang"));
		localeLabel.setLabelFor(localeCB);

		onTopCheckBox = new JCheckBox(MessageBundle.get("pref.onTop"));
		onTopCheckBox.setSelected(preferences.isAlwaysOnTop());

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup().addComponent(themeLabel)
								.addComponent(localeLabel)
								.addComponent(onTopCheckBox))
				.addGroup(
						layout.createParallelGroup().addComponent(themeCB)
								.addComponent(localeCB)
								.addComponent(onTopCheckBox)));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(themeLabel).addComponent(themeCB))
				.addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(localeLabel)
								.addComponent(localeCB))
				.addComponent(onTopCheckBox));
	}

	private class SaveButtonListener implements ActionListener {

		private UIPreferencesChangeListener listener;

		public SaveButtonListener(UIPreferencesChangeListener listener) {
			this.listener = listener;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
}
