package org.keyboardplaying.dailytasks;

import static org.junit.Assert.fail;

import java.io.IOException;

import jdepend.framework.JDepend;
import jdepend.framework.JavaPackage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Ensures that there is no package cycle.
 * 
 * @see http://blog.mafr.de/2010/10/02/java-finding-package-cycles/
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class DependenciesTest {

	private static Log log = LogFactory.getLog(DependenciesTest.class);

	/** Ensure there is no package cycle. */
	@Test
	public void testCycles() throws IOException {
		JDepend jdepend = new JDepend();
		jdepend.addDirectory("target/classes");

		jdepend.analyze();

		if (jdepend.containsCycles()) {
			StringBuilder sb = new StringBuilder(
					"The following packages contain cycles. Please remove them to avoid future test refusal.");

			for (Object element : jdepend.getPackages()) {
				JavaPackage pack = (JavaPackage) element;
				if (pack.containsCycle()) {
					sb.append('\n').append('\t').append(pack.getName());
				}
			}
			log.warn(sb.toString());

			fail();
		} else {
			log.info("No package cycle detected.");
		}
	}
}