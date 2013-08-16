package net.compor.commons.net.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import net.compor.commons.net.ComporCannotEstablishConnectionException;
import net.compor.commons.net.ComporTimeoutException;
import net.compor.commons.net.NetworkUtil;

/**
 * A server socket for connection oriented data transfers.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public class TCPServerSocket implements ServerSocketIF {

  /** The uunderlying socket of this connection oriented socket. */
  private ServerSocket serverSocket;

  /**
   * Creates a new and unbound <code>ConnectionOrientedServerSocket</code>.
   */
  public TCPServerSocket() {
  }

  /**
   * Creates a new <code>ConnectionOrientedSocket</code> with the specified
   * port and binds it to such port.
   *
   * @param _port
   *          The port on which the socket should be bound.
   * @throws ComporCannotBindSocketException
   *           If there is an error while biding the socket to the specified
   *           port.
   */
  public TCPServerSocket(final int _port)
      throws ComporCannotBindSocketException {
    try {
      serverSocket = new ServerSocket(_port);
    } catch (IOException e) {
      throw new ComporCannotBindSocketException(_port, e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.ServerSocketIF#listenConnection(int)
   */
  public TCPClientSocket listenConnection(final int _timeout)
      throws ComporCannotEstablishConnectionException, ComporTimeoutException {

    try {
      serverSocket.setSoTimeout(_timeout);
      Socket socket = serverSocket.accept();

      return new TCPClientSocket(socket);
    } catch (SocketTimeoutException e) {
      throw new ComporTimeoutException();
    } catch (Exception e) {
      throw new ComporCannotEstablishConnectionException(serverSocket
          .getInetAddress().getHostAddress(), serverSocket.getLocalPort(),
          e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#bind(int)
   */
  public void bind(final int _port) throws ComporCannotBindSocketException,
      ComporSocketAlreadyBoundException {
    try {
      if ((serverSocket != null) && (serverSocket.isBound())) {
        throw new ComporSocketAlreadyBoundException(serverSocket
            .getLocalPort(), _port);
      } else {
        this.serverSocket.bind(new InetSocketAddress(
            NetworkUtil.LOOPBACK_ADDRESS, _port));
      }
    } catch (IOException e) {
      throw new ComporCannotBindSocketException(_port, e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#isBound()
   */
  public boolean isBound() {
    if (serverSocket != null) {
      return serverSocket.isBound();
    } else {
      return false;
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#close()
   */
  public void close() throws ComporCannotUnbindSocketException {
    try {
      serverSocket.close();
    } catch (IOException e) {
      throw new ComporCannotUnbindSocketException(serverSocket
          .getLocalPort(), e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#isClosed()
   */
  public boolean isClosed() {
    if (serverSocket != null) {
      return serverSocket.isClosed();
    } else {
      return true;
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#getLocalPort()
   */
  public int getLocalPort() throws ComporSocketNotBoundException {
    if ((serverSocket == null) || (!serverSocket.isBound())) {
      throw new ComporSocketNotBoundException();
    } else {
      return serverSocket.getLocalPort();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#getLocalAddress()
   */
  public String getLocalAddress() throws ComporSocketNotBoundException {
    if ((serverSocket == null) || (!serverSocket.isBound())) {
      return serverSocket.getInetAddress().getHostAddress();
    } else {
      throw new ComporSocketNotBoundException();
    }
  }
}
