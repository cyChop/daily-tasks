package org.keyboardplaying.dailytasks.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Some basic tests to make sure no regression is made due to changes on
 * {@link MessageLevel}.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public class MessageLevelTest {

	@Test
	public void testOrder() {
		assertEquals(0, MessageLevel.INFO.compareTo(MessageLevel.INFO));

		// This should not evolve since it comes from the implementation of
		// Enum.compareTo(Enum)
		assertTrue(MessageLevel.INFO.compareTo(MessageLevel.WARNING) < 0);
		assertTrue(MessageLevel.WARNING.compareTo(MessageLevel.ERROR) < 0);
		assertFalse(MessageLevel.WARNING.compareTo(MessageLevel.INFO) <= 0);
		assertFalse(MessageLevel.ERROR.compareTo(MessageLevel.WARNING) <= 0);
	}
}
