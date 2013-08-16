package net.compor.commons.net.socket;

/**
 * The super interface of all kinds of sockets.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public interface SocketIF {

  /**
   * Returns the local port on which the socket is bound.
   *
   * @return int.
   * @throws ComporSocketNotBoundException
   *           If the socket is not bound to any port.
   */
  int getLocalPort() throws ComporSocketNotBoundException;

  /**
   * Returns the address of the local host on which the socket is bound.
   *
   * @return String.
   * @throws ComporSocketNotBoundException
   *           If the socket is not bound to any port.
   */
  String getLocalAddress() throws ComporSocketNotBoundException;

  /**
   * Binds the socket to the local port.
   *
   * @param _localPort
   *          The port on which the socket should bind.
   * @throws ComporCannotBindSocketException
   *           If there is an error while binding the socket.
   * @throws ComporSocketAlreadyBoundException
   *           If the socket is already bound to another port.
   */
   void bind(final int _localPort) throws ComporCannotBindSocketException,
      ComporSocketAlreadyBoundException;

  /**
   * Returns <code>true</code> if the socket is bound to some port and
   * <code>false</code> otherwise.
   *
   * @return boolean.
   */
  boolean isBound();

  /**
   * Closes this socket.
   */
  void close() throws ComporCannotUnbindSocketException;

  /**
   * Returns <code>true</code> if the socket is closed and <code>false</code>
   * otherwise.
   *
   * @return boolean.
   */
  boolean isClosed();
}
