package net.compor.commons.net.socket;

import net.compor.commons.net.ComporCannotReceiveDataException;
import net.compor.commons.net.ComporCannotSendDataException;
import net.compor.commons.net.ComporTimeoutException;
import net.compor.commons.net.NetworkPacketIF;

/**
 * Comment...
 *
 * @author emerson
 * @version 1.0
 */
public interface SynchronousConnectiolessSenderSocketIF extends SocketIF {

  /**
   * Sends the packet through the network. This method blocks until a response
   * is received or a timeout is reached.
   *
   * @param _packet
   *          The package to be sent.
   * @param _timeout
   *          The time during which the socket must wait for a response.
   * @return NetworkPacketIF.
   * @throws ComporTimeoutException
   * @throws ComporCannotReceiveDataException
   * @throws ComporSocketNotConnectedException
   * @throws ComporCannotSendDataException
   */
  NetworkPacketIF send(final NetworkPacketIF _packet, final int _timeout)
      throws ComporCannotSendDataException, ComporTimeoutException,
      ComporCannotReceiveDataException;
}
