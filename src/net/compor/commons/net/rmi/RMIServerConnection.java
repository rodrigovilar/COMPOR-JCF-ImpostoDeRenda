package net.compor.commons.net.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import net.compor.commons.net.ComporCannotStartConnectionException;
import net.compor.commons.net.ComporCannotStopConnectionException;
import net.compor.commons.net.ServerConnectionIF;
import net.compor.commons.net.ServerListenerIF;

/**
 * Implements an RMI server for receiving messages through remote method
 * invocations.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class RMIServerConnection extends UnicastRemoteObject implements
    RMIRemoteInterface, ServerConnectionIF {

  /**
   * Server binding name.
   */
  protected static final String RMI_SERVER_NAME = "RMIServer";

  /**
   * Server port.
   */
  private int port;

  /**
   * Listener for the messages that arrive to the server.
   */
  private ServerListenerIF listener;

  /**
   * RMI Registry reference.
   */
  private Registry registry;

  /**
   * Creates a new <code>RMIServerConnection</code> with the specified port
   * and listener.
   *
   * @param _port
   *          Server port
   * @param _listener
   *          Listener for the messages that arrive to the server
   * @throws RemoteException
   *           If there are problems with the remote object creation.
   */
  public RMIServerConnection(final int _port, final ServerListenerIF _listener)
      throws RemoteException {
    super();
    port = _port;
    listener = _listener;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ServerConnectionIF#start()
   */
  public void start() throws ComporCannotStartConnectionException {
    try {
      registry = LocateRegistry.createRegistry(port);
      Naming.rebind("//" + ServerConnectionIF.DEFAULT_SERVER_ADDRESS + ":"
          + port + "/" + RMI_SERVER_NAME, this);
    } catch (RemoteException re) {
      throw new ComporCannotStartConnectionException(
          ServerConnectionIF.DEFAULT_SERVER_ADDRESS, port, re);
    } catch (MalformedURLException mue) {
      throw new ComporCannotStartConnectionException(
          ServerConnectionIF.DEFAULT_SERVER_ADDRESS, port, mue);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ServerConnectionIF#stop()
   */
  public void stop() throws ComporCannotStopConnectionException {
    try {
      UnicastRemoteObject.unexportObject(this, false);
      UnicastRemoteObject.unexportObject(registry, false);
    } catch (NoSuchObjectException nsoe) {
      throw new ComporCannotStartConnectionException(
          ServerConnectionIF.DEFAULT_SERVER_ADDRESS, port, nsoe);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ServerConnectionIF#addServerListener(com.ServerListenerIF)
   */
  public void setServerListener(final ServerListenerIF _listener) {
    listener = _listener;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.rmi.RMIRemoteInterface#send(java.lang.Class, java.lang.Object)
   */
  public void callRemoteMethod(final Class _contentType, final Object _content)
      throws RemoteException {
    // Verifying char based types
    if (_contentType == char.class) {
      listener.receive(((Character) _content).charValue());
    } else if (_contentType == char[].class) {
      listener.receive((char[]) _content);
      // Verifying byte based types
    } else if (_contentType == byte.class) {
      listener.receive(((Byte) _content).byteValue());
    } else if (_contentType == byte[].class) {
      listener.receive((byte[]) _content);
      // Verifying short based types
    } else if (_contentType == short.class) {
      listener.receive(((Short) _content).shortValue());
    } else if (_contentType == short[].class) {
      listener.receive((short[]) _content);
      // Verifying int based types
    } else if (_contentType == int.class) {
      listener.receive(((Integer) _content).intValue());
    } else if (_contentType == int[].class) {
      listener.receive((int[]) _content);
      // Verifying long based types
    } else if (_contentType == long.class) {
      listener.receive(((Long) _content).longValue());
    } else if (_contentType == long[].class) {
      listener.receive((long[]) _content);
      // Verifying float based types
    } else if (_contentType == float.class) {
      listener.receive(((Float) _content).floatValue());
    } else if (_contentType == float[].class) {
      listener.receive((float[]) _content);
      // Verifying double based types
    } else if (_contentType == double.class) {
      listener.receive(((Double) _content).doubleValue());
    } else if (_contentType == double[].class) {
      listener.receive((double[]) _content);
      // Verifying boolean based types
    } else if (_contentType == boolean.class) {
      listener.receive(((Boolean) _content).booleanValue());
    } else if (_contentType == boolean[].class) {
      listener.receive((boolean[]) _content);
      // Verifying String based types
    } else if (_contentType == String.class) {
      listener.receive((String) _content);
    } else if (_contentType == String[].class) {
      listener.receive((String[]) _content);
      // Verifying Object arrays
    } else if (_contentType == Object[].class) {
      listener.receive((Object[]) _content);
      // Otherwise, it is considered an Object based type
    } else {
      listener.receive(_content);
    }
  }

}
