package net.compor.commons.net;

/**
 * Inteface for listeners of messages that arrive to a network server.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public interface ServerListenerIF {

  /**
   * Receives a <code>char</code> value through the network for a server
   * related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final char _content);

  /**
   * Receives an array of <code>char</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final char[] _content);

  /**
   * Receives a <code>byte</code> value through the network for a server
   * related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final byte _content);

  /**
   * Receives an array of <code>byte</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final byte[] _content);

  /**
   * Receives a <code>short</code> value through the network for a server
   * related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final short _content);

  /**
   * Receives an array of <code>short</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final short[] _content);

  /**
   * Receives an <code>int</code> value through the network for a server
   * related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final int _content);

  /**
   * Receives an array of <code>int</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final int[] _content);

  /**
   * Receives a <code>long</code> value through the network for a server
   * related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final long _content);

  /**
   * Receives an array of <code>long</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final long[] _content);

  /**
   * Receives a <code>float</code> value through the network for a server
   * related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final float _content);

  /**
   * Receives an array of <code>float</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final float[] _content);

  /**
   * Receives a <code>double</code> value through the network for a server
   * related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final double _content);

  /**
   * Receives an array of <code>double</code> values through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final double[] _content);

  /**
   * Receives a <code>boolean</code> value through the network for a server
   * related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final boolean _content);

  /**
   * Receives an array of <code>boolean</code> values through the network for
   * a server related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final boolean[] _content);

  /**
   * Receives an <code>String</code> through the network for a server related
   * to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final String _content);

  /**
   * Receives an array of <code>Strings</code> through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final String[] _content);

  /**
   * Receives an <code>Object</code> value through the network for a server
   * related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final Object _content);

  /**
   * Receives an array of <code>Objects</code> through the network for a
   * server related to this conection.
   *
   * @param _content
   *          content to be sent
   */
  void receive(final Object[] _content);

}
