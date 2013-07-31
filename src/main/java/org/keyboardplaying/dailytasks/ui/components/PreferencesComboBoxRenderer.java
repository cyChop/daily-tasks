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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;

/**
 * A common base for implementations of {@link ListCellRenderer} to be used.
 * <p/>
 * This class defines some custom styles to be used for the combo boxes on the
 * preferences window.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public abstract class PreferencesComboBoxRenderer extends JLabel implements
		ListCellRenderer {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 3181770228834762042L;

	/** The horizontal margin for combo box values. */
	private static final int MARGIN_HORIZONTAL = 10;
	/** The vertical margin for combo box values. */
	private static final int MARGIN_VERTICAL = 2;

	/**
	 * Creates a new instance.
	 * 
	 * @param hAlign
	 *            the horizontal alignment to be used
	 */
	public PreferencesComboBoxRenderer(int hAlign) {
		setOpaque(true);
		setBorder(BorderFactory.createEmptyBorder(MARGIN_VERTICAL,
				MARGIN_HORIZONTAL, MARGIN_VERTICAL, MARGIN_HORIZONTAL));
		setHorizontalAlignment(hAlign);
		setVerticalAlignment(CENTER);
	}
}