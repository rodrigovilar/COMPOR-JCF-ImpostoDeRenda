package net.compor.commons.net;

/**
 * Represents an interface for a specific client connection according to the
 * Abstract Factory Design Pattern [GoF 95].
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public interface ClientConnectionIF {

  /**
   * Sends a <code>char</code> value through the network for a server related
   * to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final char _content) throws ComporCannotSendDataException;

  /**
   * Sends an array of <code>char</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final char[] _content) throws ComporCannotSendDataException;

  /**
   * Sends a <code>byte</code> value through the network for a server related
   * to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final byte _content) throws ComporCannotSendDataException;

  /**
   * Sends an array of <code>byte</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final byte[] _content) throws ComporCannotSendDataException;

  /**
   * Sends a <code>short</code> value through the network for a server related
   * to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final short _content) throws ComporCannotSendDataException;

  /**
   * Sends an array of <code>short</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final short[] _content) throws ComporCannotSendDataException;

  /**
   * Sends an <code>int</code> value through the network for a server related
   * to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final int _content) throws ComporCannotSendDataException;

  /**
   * Sends an array of <code>int</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final int[] _content) throws ComporCannotSendDataException;

  /**
   * Sends a <code>long</code> value through the network for a server related
   * to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final long _content) throws ComporCannotSendDataException;

  /**
   * Sends an array of <code>long</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final long[] _content) throws ComporCannotSendDataException;

  /**
   * Sends a <code>float</code> value through the network for a server related
   * to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final float _content) throws ComporCannotSendDataException;

  /**
   * Sends an array of <code>float</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final float[] _content) throws ComporCannotSendDataException;

  /**
   * Sends a <code>double</code> value through the network for a server
   * related to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final double _content) throws ComporCannotSendDataException;

  /**
   * Sends an array of <code>double</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final double[] _content) throws ComporCannotSendDataException;

  /**
   * Sends a <code>boolean</code> value through the network for a server
   * related to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final boolean _content) throws ComporCannotSendDataException;

  /**
   * Sends an array of <code>boolean</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final boolean[] _content) throws ComporCannotSendDataException;

  /**
   * Sends an <code>String</code> through the network for a server related to
   * this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final String _content) throws ComporCannotSendDataException;

  /**
   * Sends an array of <code>Strings</code> through the network for a server
   * related to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final String[] _content) throws ComporCannotSendDataException;

  /**
   * Sends an <code>Object</code> value through the network for a server
   * related to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final Object _content) throws ComporCannotSendDataException;

  /**
   * Sends an array of <code>Objects</code> through the network for a server
   * related to this conection.
   *
   * @param _content
   *          content to be sent
   * @throws ComporCannotSendDataException
   *           If there are problems with the data sending
   */
  void send(final Object[] _content) throws ComporCannotSendDataException;

  /**
   * Stops/Closes this connection.
   *
   * @throws ComporCannotStopConnectionException
   *           If there are problems with the connection closing
   */
  void stop() throws ComporCannotStopConnectionException;

}
