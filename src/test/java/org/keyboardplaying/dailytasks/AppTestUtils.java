package org.keyboardplaying.dailytasks;

import java.net.URL;

import org.keyboardplaying.dailytasks.preferences.AppPreferences;
import org.keyboardplaying.dailytasks.ui.Theme;

/**
 * This class is not properly speaking a unit test class. It is a utility to run
 * the {@link App} program with a local properties file from within your IDE,
 * without having to perform a full package.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class AppTestUtils {

	/**
	 * Runs the application.
	 * 
	 * @param args
	 *            optional arguments, unused
	 */
	public static void main(String[] args) {
		URL propertiesUrl = AppTestUtils.class
				.getResource("properties/tasks.properties");
		AppPreferences.setTheme(Theme.DARK);
		AppPreferences.setLocale("en");
		App.main(propertiesUrl.getPath());
	}
}
