package org.keyboardplaying.dailytasks.ui;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.keyboardplaying.dailytasks.preferences.AppPreferences;
import org.keyboardplaying.dailytasks.ui.util.FontUtils;

/**
 * The application toolbar.
 * <p/>
 * It comes with a set of buttons that blend on the background and display as
 * simple glyphs from FontAwesome.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class Toolbar extends JPanel {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 9190817897980345216L;

	/** The width of the empty border to apply around the toolbar. */
	private static final int BORDER_WIDTH = 2;

	/**
	 * Creates a new instance and initializes layout and content.
	 * 
	 * @see #initPanel()
	 */
	public Toolbar() {
		super();
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
		Color btnTxtColor = AppPreferences.getTheme().getTxtColor();

		// \uf0ad=icon-wrench
		// -> settings
		addButtonToPanel('\uf0ad', btnTxtColor, null, btnMargins);

		// \uf059=icon-question-sign
		// -> about
		addButtonToPanel('\uf059', btnTxtColor, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AboutWindow aboutWindow = new AboutWindow();
				aboutWindow.setVisible(true);
			}
		}, btnMargins);
	}

	/**
	 * Adds a button to the toolbar.
	 * <p/>
	 * The button uses a FontAwesome glyph as text. The button is transparent in
	 * order to blend on the background.
	 * 
	 * @param btnText
	 *            the text to display on this button; this should be a Unicode
	 *            character recognized in FontAwesome
	 * @param fontColor
	 *            the color the glyph should be
	 * @param listener
	 *            the action to be activated on button click; {@code null} if
	 *            none
	 * @param btnMargins
	 *            the margins to use when adding the buttons
	 */
	private void addButtonToPanel(char btnText, Color fontColor,
			ActionListener listener, Insets btnMargins) {
		JButton btn = new JButton(String.valueOf(btnText));
		btn.setMargin(btnMargins);

		/* Apply theme. */
		btn.setForeground(fontColor);

		/* Makes the button blend in the background. */
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);

		if (listener != null) {
			btn.addActionListener(listener);
		}

		/* Use FontAwesome. */
		btn.setFont(FontUtils.getFontAwesome());
		this.add(btn);
	}
}