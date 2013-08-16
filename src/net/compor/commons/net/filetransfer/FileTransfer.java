package net.compor.commons.net.filetransfer;

import java.io.Serializable;

/*
 * Created on 12/01/2005
 */

/**
 * Represents a file transfer encapsulating the bytes that are sent in each
 * transfer, the name of the file that is sent, adn the total length of the
 * file.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @version 1.0
 */
public class FileTransfer implements Serializable {

  /**
   * The bytes that are sent in each connection.
   */
  private byte[] bytes;

  /**
   * The name of the file that is sent.
   */
  private String name;

  /**
   * The total length of the file that is sent.
   */
  private long length;

  /**
   * Creates a new <code>FileTransfer</code> with the specified bytes, name,
   * and length.
   *
   * @param _bytes
   *          bytes that are sent in each connection.
   * @param _name
   *          name of the file that is sent.
   * @param _length
   *          total length of the file that is sent.
   */
  public FileTransfer(final byte[] _bytes, final String _name,
      final long _length) {
    super();
    bytes = _bytes;
    name = _name;
    length = _length;
  }

  /**
   * Returns the bytes of this <code>FileTransfer</code>.
   *
   * @return byte[] the bytes of this <code>FileTransfer</code>.
   */
  public byte[] getBytes() {
    return bytes;
  }

  /**
   * Returns the name of this <code>FileTransfer</code>.
   *
   * @return String the name of this <code>FileTransfer</code>.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the length of this <code>FileTransfer</code>.
   *
   * @return long the length of this <code>FileTransfer</code>.
   */
  public long getLength() {
    return length;
  }
}
