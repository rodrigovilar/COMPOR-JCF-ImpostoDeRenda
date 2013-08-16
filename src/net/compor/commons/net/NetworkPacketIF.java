package net.compor.commons.net;

import java.io.Serializable;

/**
 * An interface to represent network packages which are composed of the sender�s
 * and the receiver�s network address and the body of the package, which is the
 * content it carries.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public interface NetworkPacketIF extends Serializable {

  /**
   * Returns the network address of the sender.
   *
   * @return NetworkAddress.
   */
  NetworkAddressIF getSenderAddress();

  /**
   * Returns the network address of the receiver.
   *
   * @return NetworkAddress.
   */
  NetworkAddressIF getReceiverAddress();
}
