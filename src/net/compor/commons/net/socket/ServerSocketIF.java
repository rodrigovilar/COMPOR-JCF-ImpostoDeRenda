package net.compor.commons.net.socket;

import net.compor.commons.net.ComporCannotEstablishConnectionException;
import net.compor.commons.net.ComporTimeoutException;

/**
 * The interface for the sockets which may act as server sockets, that is,
 * listening and possibly accepting connections request.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public interface ServerSocketIF extends SocketIF {

  /**
   * Listens for a connection request for the amount of time specified in the
   * parameter.
   *
   * @param _timeout
   *          The amount of time for which the socket will wait for a connection
   *          request. A value of "0" is interpreted as an infinite timeout.
   * @throws ComporCannotEstablishConnectionException
   *           If there is an error while listening the local port for incoming
   *           connections.
   * @throws ComporTimeoutException
   *           If the timeout is reached and not connection request is received.
   */
  TCPClientSocket listenConnection(final int _timeout)
      throws ComporTimeoutException, ComporCannotEstablishConnectionException;
}
