package net.compor.commons.net.socket;

import net.compor.commons.net.ComporCannotEstablishConnectionException;

/**
 * The interface for the sockets that may connect to remote sockets, that is,
 * connection oriented sockets.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public interface ConnectableSocketIF extends SocketIF {

  /**
   * Returns the port of the host to which this socket is connected.
   *
   * @return int.
   * @throws ComporSocketNotConnectedException
   *           If the socket is not connected to a remote host.
   */
  int getRemotePort() throws ComporSocketNotConnectedException;

  /**
   * Returns the remote address of the host to which this socket is connected.
   *
   * @return String.
   * @throws ComporSocketNotConnectedException
   *           If the socket is not connected to a remote host.
   */
  String getRemoteAddress() throws ComporSocketNotConnectedException;

  /**
   * Connects this socket to a remote one. Once an UDPSocket is connected, data
   * may only be send to and received from the remote address.
   *
   * @param _address
   *          The address of the remote host (e.g., 200.123.24.123).
   * @param _port
   *          The port on which the connection should be established.
   * @throws ComporCannotEstablishConnectionException
   *           If there is an error while establishing the connection.
   */
  void connect(final String _address, final int _port)
      throws ComporCannotEstablishConnectionException;

  /**
   * Disconnects the socket from the remote host.
   */
  void disconnect() throws ComporCannotCloseConnectionException;

  /**
   * Returns <code>true</code> if the socket is connected and
   * <code>false</code> otherwise.
   *
   * @return boolean.
   */
  boolean isConnected() throws ComporSocketNotBoundException;
}
