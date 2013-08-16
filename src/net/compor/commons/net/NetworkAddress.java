package net.compor.commons.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Class for representing a remote address.
 *
 * @author Emerson Loureiro.
 */
public class NetworkAddress implements NetworkAddressIF {

  /** The ip address related to the network address. */
  private InetAddress address;

  /** The port related to the network address. */
  private int port;

  /**
   * Creates a <code>NetworkAddress</code> with the specified port and
   * address.
   *
   * @param _remotePort
   *          The port related to the remote address.
   * @param _remoteAddress
   *          The address of the remote host, which may be either and IP address
   *          (e.g., 200.178.65.46) or a host name (e.g., compor.net);
   * @throws ComporUnknownHostException
   *           If the address provided may not be resolved of if it is invalid.
   */
  public NetworkAddress(final int _remotePort, final String _remoteAddress)
      throws ComporUnknownHostException {
    try {
      address = InetAddress.getByName(_remoteAddress);
    } catch (UnknownHostException e) {
      throw new ComporUnknownHostException(_remoteAddress, e);
    }

    port = _remotePort;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.NetworkAddress#getPort()
   */
  public int getPort() {
    return port;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.NetworkAddress#getName()
   */
  public String getAddress() {
    return address.getHostAddress();
  }
}
