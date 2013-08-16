package net.compor.commons.net.socket;

import java.io.Serializable;

import net.compor.commons.net.ComporCannotReceiveDataException;
import net.compor.commons.net.ComporCannotSendDataException;
import net.compor.commons.net.ComporTimeoutException;

/**
 * Comment...
 *
 * @author emerson
 * @version 1.0
 */
public interface SynchronousSenderSocketIF extends ConnectableSocketIF {

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
   *           If the timeout is reached and no response arrives.
   * @throws ComporCannotReceiveDataException
   *           If there is some error while receiving the data.
   */
  Serializable send(final Serializable _packet, final int _timeout)
      throws ComporTimeoutException, ComporCannotSendDataException,
      ComporCannotReceiveDataException, ComporSocketNotConnectedException;
}
