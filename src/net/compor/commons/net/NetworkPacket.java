package net.compor.commons.net;

/**
 * A default implementation of a network package.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public class NetworkPacket implements NetworkPacketIF {

  /** The network address of the sender. */
  private NetworkAddressIF senderAddress;

  /** The network address of the receiver. */
  private NetworkAddressIF receiverAddress;

  /**
   * Creates a new <code>NetworkPackage</code> with the specified sender,
   * receiver and body.
   *
   * @param _senderAddress
   *          The network address of the sender.
   * @param _receiverAddress
   *          The network address of the receiver.
   */
  public NetworkPacket(final NetworkAddressIF _senderAddress,
      final NetworkAddressIF _receiverAddress) {
    senderAddress = _senderAddress;
    receiverAddress = _receiverAddress;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.NetworkPacketIF#getSenderAddress()
   */
  public NetworkAddressIF getSenderAddress() {
    return senderAddress;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.NetworkPacketIF#getReceiverAddress()
   */
  public NetworkAddressIF getReceiverAddress() {
    return receiverAddress;
  }
}
