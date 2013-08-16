package net.compor.commons.net.socket;

import java.io.Serializable;

import net.compor.commons.net.ComporCannotSendDataException;

/**
 * The interface for connection oriented sockets (i.e., the ones which require a
 * previously established connection) that may send data through the network.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public interface AsynchronousSenderSocketIF extends ConnectableSocketIF {

  /**
   * Sends a <code>Serializable</code> data through the conected host.
   *
   * @param _data
   *          The data to be sent.
   * @throws ComporCannotSendDataException
   *           If there is an error while sending the data.
   */
  void send(final Serializable _data)
      throws ComporCannotSendDataException, ComporSocketNotConnectedException;
}
