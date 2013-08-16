package net.compor.commons.net.socket;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import net.compor.commons.net.ComporCannotEstablishConnectionException;
import net.compor.commons.net.ComporCannotReceiveDataException;
import net.compor.commons.net.ComporCannotSendDataException;
import net.compor.commons.net.ComporTimeoutException;
import net.compor.commons.net.NetworkPacketIF;
import net.compor.commons.net.NetworkUtil;

/**
 * Sockets for sending and receiving data without the need for establishing
 * connections among the hosts.
 *
 * @author Emerson Loureiro
 */
public class UDPSocket implements AsynchronousReceiverSocketIF,
    AsynchronousSenderSocketIF, AsynchronousConnectionlessSenderSocketIF {

  /** The local port on which the socket will be bound. */
  private DatagramSocket datagramSocket;

  /** The size, in bytes, of the datagrams sent by this socket. */
  private static final int DATAGRAM_LENGTH = 1024;

  /**
   * Creates a new and not bound <code>ConnectionlessSocket</code>.
   *
   * @throws ComporCannotBindSocketException
   *           If there is any error (i.e., I/O) while creating the socket.
   */
  public UDPSocket() throws ComporCannotBindSocketException {
    try {
      datagramSocket = new DatagramSocket();
    } catch (SocketException e) {
      throw new ComporCannotBindSocketException(-1, e);
    }
  }

  /**
   * Creates a <code>ConnectionLessSocket</code> with the specified port and
   * bount to it.
   *
   * @param _localPort
   *          The local port on which the socket should be bound.
   * @throws ComporCannotBindSocketException
   *           If the there is an error while biding the socket to the local
   *           port.
   */
  public UDPSocket(final int _localPort)
      throws ComporCannotBindSocketException {
    try {
      datagramSocket = new DatagramSocket(_localPort);
    } catch (SocketException e) {
      throw new ComporCannotBindSocketException(_localPort, e);
    }
  }

  /**
   * Handles the receiving of data from the network.
   *
   * @param _packet
   *          The packet that has been received from network.
   * @param _listener
   *          The object that should be receive the data.
   * @throws ComporCannotReceiveDataException
   *           If there is an error while receiving the data.
   */
  private void unpackData(final DatagramPacket _packet,
      final SocketListenerIF _listener)
      throws ComporCannotReceiveDataException {
    byte[] packageContent = _packet.getData();

    try {
      ObjectInputStream objectInputStream = new ObjectInputStream(
          new ByteArrayInputStream(packageContent));
      SocketMessage receivedPackage = (SocketMessage) objectInputStream
          .readObject();

      DispatcherThread dispatcherThread = new DispatcherThread(_listener,
          receivedPackage);
      dispatcherThread.invoke();
    } catch (Exception e) {
      throw new ComporCannotReceiveDataException(e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#bind(int)
   */
  public void bind(final int _localPort) throws ComporCannotBindSocketException,
      ComporSocketAlreadyBoundException {
    if ((datagramSocket != null) && (datagramSocket.isBound())) {
      throw new ComporSocketAlreadyBoundException(datagramSocket
          .getLocalPort(), _localPort);
    } else {
      try {
        datagramSocket.bind(new InetSocketAddress(
            NetworkUtil.LOOPBACK_ADDRESS, _localPort));
      } catch (SocketException e) {
        throw new ComporCannotBindSocketException(_localPort, e);
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#isBound()
   */
  public boolean isBound() {
    return datagramSocket.isBound();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#close()
   */
  public void close() {
    datagramSocket.close();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#isClosed()
   */
  public boolean isClosed() {
    return datagramSocket.isClosed();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.ConnectableSocketIF#
   * connect(java.lang.String,int)
   */
  public void connect(final String _address, final int _port)
      throws ComporCannotEstablishConnectionException {
    InetSocketAddress remoteHostAddress = new InetSocketAddress(_address,
        _port);
    try {
      datagramSocket.connect(remoteHostAddress);
    } catch (Exception e) {
      throw new ComporCannotEstablishConnectionException(_address, _port, e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.ConnectableSocketIF#disconnect()
   */
  public void disconnect() {
    datagramSocket.disconnect();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.ConnectableSocketIF#isConnected()
   */
  public boolean isConnected() {
    return datagramSocket.isConnected();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.AsynchronousSenderSocketIF#
   * send(java.io.Serializable)
   */
  public void send(final Serializable _data)
      throws ComporCannotSendDataException, ComporSocketNotConnectedException {
    if (this.isConnected()) {
      try {
        SocketMessage message = new SocketMessage(_data,
            SocketMessage.TYPE_SERIALIZABLE);
        byte[] bytesToSend = NetworkUtil.getAsByteArray(message);
        int port = datagramSocket.getPort();
        InetAddress remoteAddress = datagramSocket.getInetAddress();

        DatagramPacket packet = new DatagramPacket(bytesToSend,
            bytesToSend.length, remoteAddress, port);

        datagramSocket.send(packet);
      } catch (IOException e) {
        throw new ComporCannotSendDataException(datagramSocket
            .getInetAddress().getHostAddress(), datagramSocket.getPort(),
            Serializable.class, _data, e);
      }
    } else {
      throw new ComporSocketNotConnectedException();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.AsynchronousConnectionlessSenderSocketIF
   * #send(net.compor.commons.net.socket.ApplicationPacket)
   */
  public void send(final NetworkPacketIF _applicationPackage)
      throws ComporCannotSendDataException {
    try {
      InetAddress remoteAddress = InetAddress.getByName(_applicationPackage
          .getReceiverAddress().getAddress());

      int port = _applicationPackage.getReceiverAddress().getPort();

      SocketMessage message = new SocketMessage(_applicationPackage,
          SocketMessage.TYPE_NETWORK_PACKAGE);
      byte[] bytesToSend = NetworkUtil.getAsByteArray(message);

      DatagramPacket packet = new DatagramPacket(bytesToSend,
          bytesToSend.length, remoteAddress, port);

      datagramSocket.send(packet);
    } catch (Exception e) {
      throw new ComporCannotSendDataException(_applicationPackage
          .getReceiverAddress().getAddress(), _applicationPackage
          .getReceiverAddress().getPort(), NetworkPacketIF.class,
          _applicationPackage, e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.ReceiverSocketIF#
   * listen(net.compor.commons.net.socket.SocketListenerIF,int)
   */
  public void receive(final SocketListenerIF _listener, final int _timeout)
      throws ComporTimeoutException, ComporCannotReceiveDataException {
    DatagramPacket packet = new DatagramPacket(new byte[DATAGRAM_LENGTH],
        DATAGRAM_LENGTH);

    try {
      /* Blocks until some data is received or the timeout is reached. */
      datagramSocket.setSoTimeout(_timeout);
      datagramSocket.receive(packet);
    } catch (SocketTimeoutException e) {
      throw new ComporTimeoutException();
    } catch (Exception e) {
      throw new ComporCannotReceiveDataException(e);
    }

    this.unpackData(packet, _listener);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#getLocalPort()
   */
  public int getLocalPort() throws ComporSocketNotBoundException {
    if (!datagramSocket.isBound()) {
      throw new ComporSocketNotBoundException();
    } else {
      return datagramSocket.getLocalPort();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#getLocalAddress()
   */
  public String getLocalAddress() throws ComporSocketNotBoundException {
    if (!datagramSocket.isBound()) {
      throw new ComporSocketNotBoundException();
    } else {
      return datagramSocket.getLocalAddress().getHostAddress();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.ConnectableSocketIF#getRemotePort()
   */
  public int getRemotePort() throws ComporSocketNotConnectedException {
    if (datagramSocket.isConnected()) {
      return datagramSocket.getPort();
    } else {
      throw new ComporSocketNotConnectedException();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.ConnectableSocketIF#getRemoteAddress()
   */
  public String getRemoteAddress() throws ComporSocketNotConnectedException {
    if (datagramSocket.isConnected()) {
      return datagramSocket.getInetAddress().getHostAddress();
    } else {
      throw new ComporSocketNotConnectedException();
    }
  }
}
