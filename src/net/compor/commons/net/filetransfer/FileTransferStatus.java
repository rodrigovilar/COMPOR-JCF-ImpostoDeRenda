package net.compor.commons.net.filetransfer;

import java.io.OutputStream;

/*
 * Created on 10/01/2005
 */

/**
 * Represents the status of a file transfer that stores the bytes transferred,
 * the length of the file and the stream used to write the file.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 */
public class FileTransferStatus {

  /**
   * The number of bytes that has been already transferred.
   */
  private int bytesDone;

  /**
   * The length of the file of this <code>FileTransferStatus</code>.
   */
  private long length;

  /**
   * The outputStream used to write the file.
   */
  private OutputStream outputStream;

  /**
   * Creates a new <code>FileTransferStatus</code> with the specified
   * <code>length</code> and <code>outputStream</code>.
   *
   * @param _length
   * @param _outputStream
   */
  public FileTransferStatus(final long _length,
      final OutputStream _outputStream) {
    length = _length;
    bytesDone = 0;
    outputStream = _outputStream;
  }

  /**
   * Returns the bytesDone of this <code>FileTransferStatus</code>.
   *
   * @return int the bytesDone of this <code>FileTransferStatus</code>.
   */
  public int getBytesDone() {
    return bytesDone;
  }

  /**
   * Returns the length of this <code>FileTransferStatus</code>.
   *
   * @return long the length of this <code>FileTransferStatus</code>.
   */
  public long getLength() {
    return length;
  }

  /**
   * Returns the outputStream of this <code>FileTransferStatus</code>.
   *
   * @return OutputStream the outputStream of this
   *         <code>FileTransferStatus</code>.
   */
  public OutputStream getOutputStream() {
    return outputStream;
  }

  /**
   * Increments the length (bytes) of the file of this
   * <code>FileTransferStatus</code>.
   *
   * @param _newLength the lenght
   */
  public void increment(final int _newLength) {
    bytesDone += _newLength;
  }

  /**
   * Indicates if the transfer finished.
   *
   * @return true if the transfer finished, otherwise false.
   */
  public boolean transferred() {
    return (bytesDone == length);
  }

  /**
   * Returns bytesDone / length - trasnferred().
   *
   * @return bytesDone / length - trasnferred()
   */
  public String toString() {
    return bytesDone + "/" + length + " - " + this.transferred();
  }
}
