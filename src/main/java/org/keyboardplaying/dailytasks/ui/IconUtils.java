package org.keyboardplaying.dailytasks.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class to retrieve icons.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public final class IconUtils {

	/** The relative path to the directory containing all icons. */
	private static final String ICONS_RELATIVE_PATH = "icons/";

	/**
	 * Returns a list of all icons with this name and extension.
	 * 
	 * @param iconName
	 *            the name of the icon
	 * @param iconExt
	 *            the extension of the icon (should begin with {@code .})
	 * @return a list of the requested icon in all available sizes
	 * @see IconSize
	 */
	// WARNING TO THE DEVELOPER
	// Any icon requested via this method should be available in all sizes.
	public static List<Image> getIconImages(String iconName, String iconExt) {
		List<Image> icons = new ArrayList<Image>();

		for (IconSize size : IconSize.values()) {
			// build path to icon
			StringBuilder sb = new StringBuilder();
			sb.append(ICONS_RELATIVE_PATH).append(iconName);
			sb.append('-').append(size.getIdentifier()).append(iconExt);

			// create Image
			URL url = IconUtils.class.getResource(sb.toString());
			Image icon = Toolkit.getDefaultToolkit().getImage(url);

			// store Image
			icons.add(icon);
		}

		return icons;
	}

	/**
	 * The available icon sizes.
	 * 
	 * @author cyChop (http://keyboardplaying.org/)
	 */
	protected enum IconSize {

		_16("16"), _32("32"), _48("48"), _128("128"), _256("256");

		/** The identifier suffix that characterize a size. */
		private String identifier;

		/**
		 * Creates a new instance.
		 * 
		 * @param identifier
		 *            the identifier suffix that characterize a size
		 */
		private IconSize(String identifier) {
			this.identifier = identifier;
		}

		/**
		 * Returns the identifier suffix that characterize a size.
		 * 
		 * @return the identifier suffix that characterize a size
		 */
		public String getIdentifier() {
			return identifier;
		}
	}
}
