package net.compor.commons.net.socket;

import net.compor.commons.net.ComporCannotSendDataException;
import net.compor.commons.net.NetworkPacketIF;

/**
 * Interface for connectionless sockets (i.e., the ones which do not require a
 * previously established connection) that may send data through the network in
 * an asynchronous way.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public interface AsynchronousConnectionlessSenderSocketIF extends SocketIF {

  /**
   * Sends the array of <code>bytes</code> through the network.
   *
   * @param _packet
   *          The packet to be sent.
   * @throws ComporCannotSendDataException
   *           If the data cannot be sent due to some error.
   */
  void send(final NetworkPacketIF _packet)
      throws ComporCannotSendDataException;
}
