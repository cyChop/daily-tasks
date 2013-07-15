package org.keyboardplaying.dailytasks.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.io.IOUtils;

// XXX JAVADOC
/**
 * @author cyChop (http://keyboardplaying.org/)
 */
public final class Serializer {

	public static <T extends Serializable> byte[] serialize(T object) {
		ObjectOutputStream oos = null;

		try {

			/* Use the ObjectOutputStream to serialize. */
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(output);
			oos.writeObject(object);
			oos.flush();

			// done, return the result
			return output.toByteArray();

		} catch (IOException e) {

			// TODO throw an exception to display an appropriate message
			throw new RuntimeException();

		} finally {

			/* Make sure the stream is closed. */
			IOUtils.closeQuietly(oos);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deserialize(byte[] serialized) {
		ObjectInputStream ois = null;

		try {

			/* Use the ObjectInputStream to deserialize. */
			ByteArrayInputStream input = new ByteArrayInputStream(
					serialized);
			ois = new ObjectInputStream(input);

			// done, return the result
			return (T) ois.readObject();

		} catch (IOException e) {

			// TODO throw an exception to display an appropriate message
			e.printStackTrace();
			throw new RuntimeException();

		} catch (ClassNotFoundException e) {

			// TODO throw an exception to display an appropriate message
			throw new RuntimeException();

		} finally {

			/* Make sure the stream is closed. */
			IOUtils.closeQuietly(ois);
		}
	}
}
