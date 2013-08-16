package net.compor.commons.net.socket;

import net.compor.commons.net.ComporCannotReceiveDataException;
import net.compor.commons.net.ComporCannotSendDataException;
import net.compor.commons.net.ComporTimeoutException;
import net.compor.commons.net.NetworkPacketIF;

/**
 * A connectionless socket that sends a package and waits (blocking) for a
 * response.
 *
 * @author Emerson Loureiro
 * @version 1.0
 */
public class RequestResponseSocket extends SocketListenerAdapter implements
    SynchronousConnectiolessSenderSocketIF {

  /**
   * The connectionless socket related to the this socket.
   * */
  private UDPSocket socket;

  /** The package that has been received. */
  private NetworkPacketIF receivedPacket;

  /**
   * Creates a new and unbound <code>RequestResponseSocket</code>.
   *
   * @throws ComporCannotBindSocketException
   *           If there is any error (i.e., I/O) while creating the socket.
   */
  public RequestResponseSocket() throws ComporCannotBindSocketException {
    socket = new UDPSocket();
  }

  /**
   * Creates a new <code>JDCFConnectionslessSocket</code> with the specified
   * port bound to it.
   *
   * @param _port
   *          The port on whith the socket must bind.
   * @throws ComporCannotBindSocketException
   *           If the socket may not be bound to the specified port.
   */
  public RequestResponseSocket(final int _port)
      throws ComporCannotBindSocketException {
    socket = new UDPSocket(_port);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SynchronousConnectiolessSenderSocketIF
   * #send(net.compor.commons.net.socket.ApplicationPacket,int)
   */
  public NetworkPacketIF send(final NetworkPacketIF _packet, final int _timeout)
      throws ComporCannotSendDataException, ComporTimeoutException,
      ComporCannotReceiveDataException {
    /* Sends the package. */
    socket.send(_packet);
    /* Clears the received package from previous sent packages. */
    receivedPacket = null;

    /*
     * Listens for a response. Blocks until a response is received, or if the
     * timeout is reached.
     */
    socket.receive(this, _timeout);

    try {
      synchronized (this) {
        this.wait();
      }
    } catch (InterruptedException e) {
    }

    return receivedPacket;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#bind(int)
   */
  public void bind(final int _localPort) throws ComporCannotBindSocketException,
      ComporSocketAlreadyBoundException {
    socket.bind(_localPort);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#isBound()
   */
  public boolean isBound() {
    return socket.isBound();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#getLocalPort()
   */
  public int getLocalPort() throws ComporSocketNotBoundException {
    return socket.getLocalPort();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#getLocalAddress()
   */
  public String getLocalAddress() throws ComporSocketNotBoundException {
    return socket.getLocalAddress();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#close()
   */
  public void close() {
    socket.close();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#isClosed()
   */
  public boolean isClosed() {
    return socket.isClosed();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketListenerIF
   * #receive(net.compor.commons.net.NetworkPacketIF)
   */
  public void receive(final NetworkPacketIF _packet) {
    receivedPacket = _packet;

    synchronized (this) {
      this.notifyAll();
    }
  }
}
