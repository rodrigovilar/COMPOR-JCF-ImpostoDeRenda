package net.compor.commons.net.socket;

import net.compor.commons.net.ComporCannotReceiveDataException;
import net.compor.commons.net.ComporTimeoutException;

/**
 * The interface for sockets that may receive data from remote hosts in an
 * asynchronous way.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public interface AsynchronousReceiverSocketIF extends SocketIF {

  /**
   * Listens the local port for a certain amount of time. When some data is
   * received, the method returns, dispathing the received data in a new thread.
   *
   * @param _listener
   *          The object that should receive the data.
   * @param _timeout
   *          The time during which the socket should wait for some data.
   * @throws ComporCannotReceiveDataException
   *           If there is some error while receiving the data.
   */
  void receive(final SocketListenerIF _listener, final int _timeout)
      throws ComporCannotReceiveDataException, ComporTimeoutException;
}
