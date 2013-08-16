package net.compor.commons.net.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import net.compor.commons.net.ClientConnectionIF;
import net.compor.commons.net.ComporCannotSendDataException;
import net.compor.commons.net.ComporCannotStopConnectionException;

/**
 * Implements an RMI Client connection for sending data through the network
 * using Remote Method Invocation.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class RMIClientConnection implements ClientConnectionIF {

  /**
   * Remote interface.
   */
  private RMIRemoteInterface remoteInterface;

  /**
   * Server address.
   */
  private String address;

  /**
   * Server port.
   */
  private int port;

  /**
   * Creates a new <code>RMIClientConnection</code> with the specified address
   * and port.
   *
   * @param _address
   *          Server address
   * @param _port
   *          Server port
   * @throws MalformedURLException
   *           If the address is malformed.
   * @throws RemoteException
   *           If there are problems with the remote interface
   * @throws NotBoundException
   *           If there are problems with the server name binding
   */
  public RMIClientConnection(final String _address, final int _port)
      throws MalformedURLException, RemoteException, NotBoundException {
    address = _address;
    port = _port;
    remoteInterface = (RMIRemoteInterface) Naming.lookup("//"
        + address + ":" + port + "/"
        + RMIServerConnection.RMI_SERVER_NAME);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(char)
   */
  public void send(final char _content) throws ComporCannotSendDataException {
    this.sendDataToServer(char.class, new Character(_content));
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(char[])
   */
  public void send(final char[] _content) throws ComporCannotSendDataException {
    this.sendDataToServer(char[].class, _content);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(byte)
   */
  public void send(final byte _content) throws ComporCannotSendDataException {
    this.sendDataToServer(byte.class, new Byte(_content));
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(byte[])
   */
  public void send(final byte[] _content) throws ComporCannotSendDataException {
    this.sendDataToServer(byte[].class, _content);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(short)
   */
  public void send(final short _content) throws ComporCannotSendDataException {
    this.sendDataToServer(short.class, new Short(_content));
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(short[])
   */
  public void send(final short[] _content)
      throws ComporCannotSendDataException {
    this.sendDataToServer(short[].class, _content);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(int)
   */
  public void send(final int _content) throws ComporCannotSendDataException {
    this.sendDataToServer(int.class, new Integer(_content));
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(int[])
   */
  public void send(final int[] _content) throws ComporCannotSendDataException {
    this.sendDataToServer(int[].class, _content);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(long)
   */
  public void send(final long _content) throws ComporCannotSendDataException {
    this.sendDataToServer(long.class, new Long(_content));
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(long[])
   */
  public void send(final long[] _content) throws ComporCannotSendDataException {
    this.sendDataToServer(long[].class, _content);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(float)
   */
  public void send(final float _content) throws ComporCannotSendDataException {
    this.sendDataToServer(float.class, new Float(_content));
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(float[])
   */
  public void send(final float[] _content)
      throws ComporCannotSendDataException {
    this.sendDataToServer(float[].class, _content);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(double)
   */
  public void send(final double _content)
      throws ComporCannotSendDataException {
    this.sendDataToServer(double.class, new Double(_content));
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(double[])
   */
  public void send(final double[] _content)
      throws ComporCannotSendDataException {
    this.sendDataToServer(double[].class, _content);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(boolean)
   */
  public void send(final boolean _content)
      throws ComporCannotSendDataException {
    this.sendDataToServer(boolean.class, Boolean.valueOf(_content));
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(boolean[])
   */
  public void send(final boolean[] _content)
      throws ComporCannotSendDataException {
    this.sendDataToServer(boolean[].class, _content);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(java.lang.String)
   */
  public void send(final String _content)
      throws ComporCannotSendDataException {
    this.sendDataToServer(String.class, _content);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(java.lang.String[])
   */
  public void send(final String[] _content)
      throws ComporCannotSendDataException {
    this.sendDataToServer(String[].class, _content);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(java.lang.Object)
   */
  public void send(final Object _content)
      throws ComporCannotSendDataException {
    this.sendDataToServer(Object.class, _content);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#send(java.lang.Object[])
   */
  public void send(final Object[] _content)
      throws ComporCannotSendDataException {
    this.sendDataToServer(Object[].class, _content);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ClientConnectionIF#stop()
   */
  public void stop() throws ComporCannotStopConnectionException {
  }

  /**
   * Sends the data to the server via RMI.
   *
   * @param _type
   *          Type of the data to be sent.
   * @param _data
   *          Data to be sent.
   * @throws ComporCannotSendDataException
   *           If there are problems with sending.
   */
  private void sendDataToServer(final Class _type, final Object _data)
      throws ComporCannotSendDataException {
    try {
      remoteInterface.callRemoteMethod(_type, _data);
    } catch (RemoteException re) {
      throw new ComporCannotSendDataException(address, port, _type, _data, re);
    }
  }

}
