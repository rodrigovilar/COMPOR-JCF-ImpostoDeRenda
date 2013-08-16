package net.compor.commons.net.socket;

import java.io.Serializable;

/**
 * The messages to be exchanged between sockets. It works as a wrapper of java
 * primitive data and objets and is internally used by the sockets
 * implementations to exchange data.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public class SocketMessage implements Serializable {

  /** The type boolean. */
  public static final int TYPE_BOOLEAN = 1;

  /** The type byte. */
  public static final int TYPE_BYTE = 2;

  /** The type char. */
  public static final int TYPE_CHAR = 4;

  /** The type double. */
  public static final int TYPE_DOUBLE = 8;

  /** The type float. */
  public static final int TYPE_FLOAT = 16;

  /** The type int. */
  public static final int TYPE_INT = 32;

  /** The type long. */
  public static final int TYPE_LONG = 64;

  /** The type object. */
  public static final int TYPE_SERIALIZABLE = 128;

  /** The type short. */
  public static final int TYPE_SHORT = 256;

  /** The type string. */
  public static final int TYPE_STRING = 512;

  /** The type array. */
  public static final int TYPE_ARRAY = 1024;

  /** The type object. */
  public static final int TYPE_NETWORK_PACKAGE = 2048;

  /** The type of the message. */
  private int type;

  /** The data to be sent. */
  private Serializable content;

  /**
   * Creates a new <code>SocketConnectionMessage</code> with the specified
   * content.
   *
   * @param _content
   *          The content to be carried by the socket.
   */
  public SocketMessage(final Serializable _content, final int _type) {
    content = _content;
    type = _type;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.MessageIF#getType()
   */
  public int getType() {
    return type;
  }

  /**
   * Returns the content of the message.
   *
   * @return Object.
   */
  public Serializable getContent() {
    return content;
  }
}
