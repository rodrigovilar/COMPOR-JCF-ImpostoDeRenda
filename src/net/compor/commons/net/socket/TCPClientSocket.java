package net.compor.commons.net.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import net.compor.commons.net.ComporCannotEstablishConnectionException;
import net.compor.commons.net.ComporCannotReceiveDataException;
import net.compor.commons.net.ComporCannotSendDataException;
import net.compor.commons.net.ComporTimeoutException;
import net.compor.commons.net.NetworkUtil;
import net.compor.commons.net.NetworkAddress;

/**
 * A client socket (all called socket) for connection oriented data transfers.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public class TCPClientSocket implements ConnectableSocketIF,
    AsynchronousSenderSocketIF, AsynchronousReceiverSocketIF,
    SynchronousReceiverSocketIF {

  /**
   * The underlying socket related to this connection oriented client socket.
   * */
  private Socket clientSocket;

  /**
   * Creates a new unbound and disconnected <code>TCPClientSocket</code>.
   */
  public TCPClientSocket() {
    this.clientSocket = new Socket();
  }

  /**
   * Creates a new <code>ConnectionOrientedClientSocket</code> with the
   * specified socket.
   *
   * @param _socket
   *          The underlying socket associated with this cliente socket.
   */
  public TCPClientSocket(final Socket _socket) {
    clientSocket = _socket;
  }

  /**
   * Creates a new <code>TCPClientSocket</code> with the specified local port,
   * remote address and port. The socket is automaticaly connected to the remote
   * address and port provided and bound to the local port provided.
   *
   * @param _localPort
   *          The local port on which the socket should be bound.
   * @param _remotePort
   *          The remote port on which the socket should connect.
   * @param _remoteAddress
   *          The address on which the socket should connect.
   * @throws ComporCannotEstablishConnectionException
   *           If the connection cannot be established.
   */
  public TCPClientSocket(final int _localPort, final String _remoteAddress,
      final int _remotePort) throws ComporCannotEstablishConnectionException {
    try {
      if (_localPort == 0) {
        clientSocket = new Socket();
      } else {
        clientSocket = new Socket(_remoteAddress, _remotePort, InetAddress
            .getByName(null), _localPort);
      }
    } catch (Exception e) {
      throw new ComporCannotEstablishConnectionException(_remoteAddress,
          _remotePort, e);
    }
  }

  /**
   * Creates a new <code>ConnectionOrientedClientSocket</code> with the
   * specified remote port and address. The socket is automaticaly connected to
   * the remote address and port provided. The socket binds to a randonly chosen
   * free port.
   *
   * @param _remotePort
   *          The remote port on which the socket should connect.
   * @param _remoteAddress
   *          The address on which the socket should connect.
   * @throws ComporCannotEstablishConnectionException
   *           If the connection cannot be established.
   */
  public TCPClientSocket(final String _remoteAddress, final int _remotePort)
      throws ComporCannotEstablishConnectionException {
    try {
      clientSocket = new Socket(_remoteAddress, _remotePort);
    } catch (Exception e) {
      throw new ComporCannotEstablishConnectionException(_remoteAddress,
          _remotePort, e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#bind(int)
   */
  public void bind(final int _localPort)
      throws ComporSocketAlreadyBoundException,
      ComporCannotBindSocketException {
    if (clientSocket.isBound()) {
      throw new ComporSocketAlreadyBoundException(clientSocket
          .getLocalPort(), _localPort);
    } else {
      try {
        clientSocket.bind(new InetSocketAddress(
            NetworkUtil.LOOPBACK_ADDRESS, _localPort));
      } catch (IOException e) {
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
    return clientSocket.isBound();
  }

  /*
   * (non-Javadoc)
   *   * @see net.compor.commons.net.socket.SocketIF#close()
   */
  public void close() throws ComporCannotUnbindSocketException {
    try {
      clientSocket.close();
    } catch (IOException e) {
      throw new ComporCannotUnbindSocketException(clientSocket.getPort(),
          e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#isClosed()
   */
  public boolean isClosed() {
    return clientSocket.isClosed();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.ConnectableSocketIF
   * #connect(java.lang.String,int)
   */
  public void connect(final String _address, final int _port)
      throws ComporCannotEstablishConnectionException {
    try {
      NetworkAddress remoteAddress = new NetworkAddress(_port, _address);
      clientSocket.connect(new InetSocketAddress(remoteAddress
          .getAddress(), remoteAddress.getPort()));
    } catch (Exception e) {
      throw new ComporCannotEstablishConnectionException(_address, _port, e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.ConnectableSocketIF#disconnect()
   */
  public void disconnect() throws ComporCannotCloseConnectionException {
    try {
      this.close();
    } catch (ComporCannotUnbindSocketException e) {
      throw new ComporCannotCloseConnectionException(e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.ConnectableSocketIF#isConnected()
   */
  public boolean isConnected() {
    return clientSocket.isConnected();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#getLocalPort()
   */
  public int getLocalPort() throws ComporSocketNotBoundException {
    if (!clientSocket.isBound()) {
      throw new ComporSocketNotBoundException();
    } else {
      return clientSocket.getLocalPort();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SocketIF#getLocalAddress()
   */
  public String getLocalAddress() throws ComporSocketNotBoundException {
    if (!clientSocket.isBound()) {
      throw new ComporSocketNotBoundException();
    } else {
      return clientSocket.getLocalAddress().getHostAddress();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.ConnectableSocketIF#getRemotePort()
   */
  public int getRemotePort() throws ComporSocketNotConnectedException {
    if (clientSocket.isConnected()) {
      return clientSocket.getPort();
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
    if (clientSocket.isConnected()) {
      return clientSocket.getInetAddress().getHostAddress();
    } else {
      throw new ComporSocketNotConnectedException();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.AsynchronousSenderSocketIF
   * #send(java.io.Serializable)
   */
  public void send(final Serializable _data)
      throws ComporCannotSendDataException {
    try {
      SocketMessage message = new SocketMessage(_data,
          SocketMessage.TYPE_SERIALIZABLE);
      ObjectOutputStream writer = new ObjectOutputStream(clientSocket
          .getOutputStream());

      writer.writeObject(message);
    } catch (IOException e) {
      throw new ComporCannotSendDataException(clientSocket
          .getInetAddress().getHostAddress(), clientSocket.getPort(),
          Serializable.class, _data, e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.AsynchronousReceiverSocketIF
   * #receive(net.compor.commons.net.socket.SocketListenerIF,int)
   */
  public void receive(final SocketListenerIF _listener, final int _timeout)
      throws ComporCannotReceiveDataException, ComporTimeoutException {
    try {
      clientSocket.setSoTimeout(_timeout);

      ObjectInputStream reader = new ObjectInputStream(
          TCPClientSocket.this.clientSocket.getInputStream());

      SocketMessage receivedObject = (SocketMessage) reader.readObject();
      /*
       * Starts a new thread to handle the dispatching of the received data.
       */
      DispatcherThread dispatcherThread = new DispatcherThread(_listener,
          receivedObject);
      dispatcherThread.invoke();
    } catch (SocketTimeoutException e) {
      throw new ComporTimeoutException();
    } catch (Exception e) {
      throw new ComporCannotReceiveDataException(e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.socket.SynchronousReceiverSocketIF#receive(int)
   */
  public Serializable receive(final int _timeout)
      throws ComporCannotReceiveDataException {
    try {
      clientSocket.setSoTimeout(_timeout);

      ObjectInputStream reader = new ObjectInputStream(clientSocket
          .getInputStream());
      SocketMessage receivedObject = (SocketMessage) reader.readObject();

      if (receivedObject.getType() == SocketMessage.TYPE_SERIALIZABLE) {
        return receivedObject.getContent();
      } else {
        return null;
      }
    } catch (IOException e) {
      throw new ComporCannotReceiveDataException(e);
    } catch (ClassNotFoundException e) {
      throw new ComporCannotReceiveDataException(e);
    }
  }
}
