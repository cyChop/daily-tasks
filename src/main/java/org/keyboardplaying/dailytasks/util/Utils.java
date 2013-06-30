package org.keyboardplaying.dailytasks.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * Class providing utility methods.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class Utils {

	/**
	 * Returns a {@link String} from the elements of a {@link Collection}, using
	 * a specific {@link Printer}.
	 * 
	 * @param collection
	 *            the elements to join to a {@link String}
	 * @param separator
	 *            specifies what to put between the elements
	 * @return a {@link String} from the elements of an array
	 */
	// retrieved and simplified from another project, not null-safe
	public static <T> String implode(Collection<T> collection, String separator) {
		if (collection == null || collection.isEmpty()) {
			return "";
		}

		Iterator<T> iter = collection.iterator();

		// init the builder with the first element
		StringBuilder sb = new StringBuilder();
		sb.append(iter.next());

		// concat each element
		while (iter.hasNext()) {
			sb.append(separator).append(iter.next());
		}

		return sb.toString();
	}
}
