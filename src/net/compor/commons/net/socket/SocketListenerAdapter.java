package net.compor.commons.net.socket;

import java.io.Serializable;

import net.compor.commons.net.NetworkPacketIF;

/**
 * An adapter for the <code>SockerListener</code> interface.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public abstract class SocketListenerAdapter implements SocketListenerIF {

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketListenerIF
   * #receive(net.compor.commons.net.socket.ApplicationPacket)
   */
  public void receive(final NetworkPacketIF _applicationPacket)
      throws Exception {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketListenerI
   * #receive(java.io.Serializable)
   */
  public void receive(final Serializable _data) throws Exception {
  }

}
