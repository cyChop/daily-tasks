package org.keyboardplaying.dailytasks;

import java.util.prefs.BackingStoreException;

import org.keyboardplaying.dailytasks.preferences.AppPreferences;
import org.keyboardplaying.dailytasks.preferences.Theme;

/**
 * This class is not properly speaking a unit test class. It is a utility to run
 * the {@link Launcher} program with a local properties file from within your IDE,
 * without having to perform a full package.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class LauncherTestUtils {

	/**
	 * Runs the application.
	 * 
	 * @param args
	 *            optional arguments, unused
	 * @throws BackingStoreException
	 */
	public static void main(String[] args) throws BackingStoreException {
		AppPreferences.clear();
		AppPreferences.setTheme(Theme.DARK);
		AppPreferences.setLocale("fr");
		Launcher.main();
	}
}
