/*
 * Created on 13/01/2005
 */
package net.compor.commons.net.filetransfer;

import java.io.IOException;
import java.io.InputStream;

import net.compor.commons.net.ClientConnectionIF;
import net.compor.commons.net.ComporCannotSendDataException;
import net.compor.commons.net.ComporCannotStopConnectionException;

/**
 * Implements a {@link net.compor.commons.net.ClientConnectionIF} for
 * transferring streams through the network. Streams are divided in small pieces
 * according to the length of a buffer and their bytes are sent through the
 * network. <code>FileTransferClientConnection</code> contains a
 * {@link net.compor.commons.net.ClientConnectionIF} which is used to redirect
 * all other methods that send data.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 *
 * @version 1.0
 */
public class FileTransferClientConnection implements ClientConnectionIF {

  /**
   * {@link net.compor.commons.net.ClientConnectionIF} which is used to redirect
   * all other methods that send data.
   */
  private ClientConnectionIF clientConnection;

  /**
   * Length of the buffer that are used to send bytes.
   */
  private int bufferLength;

  /**
   * Creates a new <code>FileTransferClientConnection</code> with the
   * specified clientConnection and bufferLength.
   *
   * @param _clientConnection
   *          {@link net.compor.commons.net.ClientConnectionIF} which is used to
   *          redirect all other methods that send data.
   * @param _bufferLength
   *          length of the buffer that are used to send bytes.
   */
  public FileTransferClientConnection(
      final ClientConnectionIF _clientConnection, final int _bufferLength) {
    clientConnection = _clientConnection;
    bufferLength = _bufferLength;
  }

  /**
   * Sends a <code>InputStream</code> through the network for a server related
   * to this conection.
   *
   * @param _inputStream
   *          inputStream to be sent.
   * @param _identifier
   *          inputStream identifier.
   * @throws ComporCannotSendStreamException
   *           If there are problems with the stream sending.
   */
  public void send(final InputStream _inputStream, final String _identifier)
      throws ComporCannotSendStreamException {
    try {
      int fileLength = _inputStream.available();
      byte[] bytes = new byte[bufferLength];

      while (_inputStream.available() > bufferLength) {
        _inputStream.read(bytes);
        clientConnection.send(new FileTransfer(bytes, _identifier, fileLength));
        bytes = new byte[bufferLength];
      }

      bytes = new byte[_inputStream.available()];
      _inputStream.read(bytes);
      clientConnection.send(new FileTransfer(bytes, _identifier, fileLength));
    } catch (IOException ioe) {
      throw new ComporCannotSendStreamException(_inputStream, _identifier, ioe);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(char)
   */
  public void send(final char _content) throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(char[])
   */
  public void send(final char[] _content) throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(byte)
   */
  public void send(final byte _content) throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(byte[])
   */
  public void send(final byte[] _content) throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(short)
   */
  public void send(final short _content) throws ComporCannotSendDataException {
   clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(short[])
   */
  public void send(final short[] _content)
      throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(int)
   */
  public void send(final int _content) throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(int[])
   */
  public void send(final int[] _content) throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(long)
   */
  public void send(final long _content) throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(long[])
   */
  public void send(final long[] _content) throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(float)
   */
  public void send(final float _content) throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(float[])
   */
  public void send(final float[] _content)
      throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(double)
   */
  public void send(final double _content)
      throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(double[])
   */
  public void send(final double[] _content)
      throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(boolean)
   */
  public void send(final boolean _content)
      throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(boolean[])
   */
  public void send(final boolean[] _content)
      throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(java.lang.String)
   */
  public void send(final String _content) throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(java.lang.String[])
   */
  public void send(final String[] _content)
      throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(java.lang.Object)
   */
  public void send(final Object _content) throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#send(java.lang.Object[])
   */
  public void send(final Object[] _content)
      throws ComporCannotSendDataException {
    clientConnection.send(_content);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ClientConnectionIF#stop()
   */
  public void stop() throws ComporCannotStopConnectionException {
    clientConnection.stop();
  }
}
