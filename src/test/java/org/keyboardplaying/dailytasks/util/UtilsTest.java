package org.keyboardplaying.dailytasks.util;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

/** @author cyChop (http://keyboardplaying.org/) */
public class UtilsTest {

	@Test
	public void testImplode() {
		// null collection
		assertEquals("", Utils.implode(null, ", "));

		// empty collection
		Set<String> set = new LinkedHashSet<String>();
		assertEquals("", Utils.implode(set, ", "));

		// one-sized collection
		set.add("one");
		assertEquals("one", Utils.implode(set, ", "));

		// different separators
		set.add("2");
		set.add("thr33");
		assertEquals("one, 2, thr33", Utils.implode(set, ", "));
		assertEquals("one;2;thr33", Utils.implode(set, ";"));
	}
}
