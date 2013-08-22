package org.keyboardplaying.dailytasks.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.keyboardplaying.dailytasks.exception.DeserializationException;
import org.keyboardplaying.dailytasks.exception.SerializationException;

/**
 * A utility class to convert {@link Serializable} objects to byte arrays and
 * back.
 * <p/>
 * This class uses the {@link ObjectOutputStream} and {@link ObjectInputStream}.
 * 
 * @author cyChop (http://keyboardplaying.org/)
 */
public final class Serializer {

	/** Private constructor for utility class. */
	private Serializer() {
	}

	/**
	 * Marshals a {@link Serializable} object to a byte array.
	 * 
	 * @param object
	 *            the object to serialize
	 * @return the object serialized as a byte array
	 * @throws SerializationException
	 *             if serialization fails
	 */
	public static <T extends Serializable> byte[] serialize(T object)
			throws SerializationException {
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

			throw new SerializationException(
					String.format(
							"An error occurred while serializing an object of type %s: <%s>",
							object.getClass().getName(), object.toString()), e);

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
	 * @throws DeserializationException
	 *             if deserialization fails
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deserialize(byte[] serialized)
			throws DeserializationException {
		ObjectInputStream ois = null;

		try {

			/* Use the ObjectInputStream to deserialize. */
			ByteArrayInputStream input = new ByteArrayInputStream(serialized);
			ois = new ObjectInputStream(input);

			// done, return the result
			return (T) ois.readObject();

		} catch (IOException e) {

			throw new DeserializationException(String.format(
					"An error occurred while deserializing data: <%s>",
					new String(serialized)), e);

		} catch (ClassNotFoundException e) {

			throw new DeserializationException(String.format(
					"An error occurred while deserializing data: <%s>",
					new String(serialized)), e);

		} finally {

			/* Make sure the stream is closed. */
			IOUtils.closeQuietly(ois);
		}
	}
}
