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

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.keyboardplaying.dailytasks.messages.MessageBundle;
import org.keyboardplaying.dailytasks.model.UIPreferences;
import org.keyboardplaying.dailytasks.ui.components.LocaleComboBox;
import org.keyboardplaying.dailytasks.ui.components.ThemeComboBox;
import org.keyboardplaying.dailytasks.ui.events.UIPreferencesChangeListener;
import org.keyboardplaying.dailytasks.ui.theme.Theme;
import org.keyboardplaying.dailytasks.ui.util.WindowUtils;

/**
 * A window to update the UI settings of the application.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class PreferencesPanel extends JPanel {

	/** Generated serial version UID. */
	private static final long serialVersionUID = -4100240338628399119L;

	/** The value selector for the locale to use. */
	private LocaleComboBox localeCB;
	/** The value selector for the theme to use. */
	private ThemeComboBox themeCB;
	/**
	 * A checkbox to indicate whether the application should remain on top of
	 * other windows.
	 */
	private JCheckBox onTopCheckBox;

	/**
	 * Creates a new instance and loads the current value of the UI preferences.
	 * 
	 * @param preferences
	 *            the current value for the UI preferences
	 * @param listener
	 *            the object listening for changes to the current preferences
	 */
	public PreferencesPanel(UIPreferences preferences,
			UIPreferencesChangeListener listener) {
		super();
		initSettingComponents(preferences);
		initWindow(listener);
	}

	/**
	 * Initializes the components used to select the current value of the UI
	 * preferences.
	 * 
	 * @param preferences
	 *            the current value for the UI preferences
	 */
	private final void initSettingComponents(UIPreferences preferences) {
		localeCB = new LocaleComboBox();
		localeCB.setSelectedItem(MessageBundle
				.getClosestApplicableLocale(preferences.getLocale()));

		themeCB = new ThemeComboBox();
		themeCB.setSelectedItem(preferences.getTheme());

		onTopCheckBox = new JCheckBox(MessageBundle.get("pref.onTop"));
		onTopCheckBox.setSelected(preferences.isAlwaysOnTop());
	}

	/**
	 * Creates non-setting related components and composes the window's layout.
	 * 
	 * @param listener
	 *            the object listening for changes to the preferences
	 */
	private final void initWindow(UIPreferencesChangeListener listener) {
		/* Create minor components. */
		JLabel themeLabel = new JLabel(MessageBundle.get("pref.theme"));
		themeLabel.setLabelFor(themeCB);

		JLabel localeLabel = new JLabel(MessageBundle.get("pref.lang"));
		localeLabel.setLabelFor(localeCB);

		JButton saveBtn = new JButton(MessageBundle.get("action.save"));
		saveBtn.addActionListener(new SaveButtonListener(listener));
		JButton cancelBtn = new JButton(MessageBundle.get("action.cancel"));
		cancelBtn.addActionListener(new CancelButtonListener());

		/* Create the layout. */
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		SequentialGroup comboBoxesHorizontalGroup = layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup().addComponent(localeLabel)
								.addComponent(themeLabel))
				.addGroup(
						layout.createParallelGroup().addComponent(localeCB)
								.addComponent(themeCB));
		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addGroup(comboBoxesHorizontalGroup)
								.addComponent(onTopCheckBox))
				.addGap(30)
				.addGroup(
						layout.createParallelGroup().addComponent(saveBtn)
								.addComponent(cancelBtn)));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(localeLabel)
								.addComponent(localeCB).addComponent(saveBtn))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(themeLabel).addComponent(themeCB)
								.addComponent(cancelBtn))
				.addComponent(onTopCheckBox));

		layout.linkSize(saveBtn, cancelBtn);
	}

	/**
	 * The {@link ActionListener} which is called when the cancel button is
	 * clicked. This object saves the preferences then closes the current
	 * preferences window.
	 * 
	 * @author cyChop (http://keyboardplaying.org/)
	 */
	private class SaveButtonListener implements ActionListener {

		/** The object listening for changes of the preferences. */
		private UIPreferencesChangeListener listener;

		/**
		 * Creates a new instance.
		 * 
		 * @param listener
		 *            the object listening for changes of the preferences
		 */
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
			UIPreferences newPrefs = new UIPreferences(
					(Locale) localeCB.getSelectedItem(),
					(Theme) themeCB.getSelectedItem(),
					onTopCheckBox.isSelected());
			listener.saveUIPreferences(newPrefs);
			// TODO propose auto-restart
			WindowUtils.triggerClosingEvent((Window) PreferencesPanel.this
					.getTopLevelAncestor());
		}
	}

	/**
	 * The {@link ActionListener} which is called when the cancel button is
	 * clicked. This object only closes the current preferences window.
	 * 
	 * @author cyChop (http://keyboardplaying.org/)
	 */
	private class CancelButtonListener implements ActionListener {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			WindowUtils.triggerClosingEvent((Window) PreferencesPanel.this
					.getTopLevelAncestor());
		}
	}
}
