package net.compor.commons.net;

import java.io.Serializable;

/**
 * Interface for representations of network addresses, which are formeed by a
 * name ("www.compor.net") and a port number (9790).
 *
 * @author Emerson Loureiro;
 * @version 1.0
 */
public interface NetworkAddressIF extends Serializable {

  /** Returns the port to which the address is related. */
  int getPort();

  /** Returns the name of the network address. */
  String getAddress();
}
