package org.keyboardplaying.dailytasks.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A utility class to convert {@link Serializable} objects to byte arrays and
 * back.
 * <p/>
 * This class uses the {@link ObjectOutputStream} and {@link ObjectInputStream}.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public final class Serializer {

	/**
	 * Marshals a {@link Serializable} object to a byte array.
	 * 
	 * @param object
	 *            the object to serialize
	 * @return the object serialized as a byte array
	 */
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

			// Designed for use within the application
			// Should not happen
			e.printStackTrace();
			throw new RuntimeException();

		} finally {

			/* Make sure the stream is closed. */
			IOUtils.closeQuietly(oos);
		}
	}

	/**
	 * Unmarshals a serialized object.
	 * 
	 * @param serialized
	 *            the serialized object
	 * @return the deserialized object
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deserialize(byte[] serialized) {
		ObjectInputStream ois = null;

		try {

			/* Use the ObjectInputStream to deserialize. */
			ByteArrayInputStream input = new ByteArrayInputStream(serialized);
			ois = new ObjectInputStream(input);

			// done, return the result
			return (T) ois.readObject();

		} catch (IOException e) {

			// Designed for use within the application
			// Should not happen
			e.printStackTrace();
			throw new RuntimeException();

		} catch (ClassNotFoundException e) {

			// Designed for use within the application
			// Should not happen
			e.printStackTrace();
			throw new RuntimeException();

		} finally {

			/* Make sure the stream is closed. */
			IOUtils.closeQuietly(ois);
		}
	}
}
