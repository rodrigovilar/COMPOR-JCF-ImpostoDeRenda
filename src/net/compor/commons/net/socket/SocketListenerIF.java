package net.compor.commons.net.socket;

import java.io.Serializable;

import net.compor.commons.net.NetworkPacketIF;

/**
 * Interface for receiving data from the network through a connectionless
 * socket.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public interface SocketListenerIF {

  /**
   * Receives some data from the network from any remote host without a
   * previously established connection.
   *
   * @param _applicationPacket
   *          The package that has been received.
   * @throws Exception
   *           If some error occurs while receiving the data.
   */
  void receive(final NetworkPacketIF _applicationPacket) throws Exception;

  /**
   * Receives data from a connected remote host.
   *
   * @param _data
   *          The data to be sent.
   * @throws Exception
   *           If some error occurs while receiving the data.
   */
  void receive(final Serializable _data) throws Exception;
}
