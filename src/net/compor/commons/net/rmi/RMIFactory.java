package net.compor.commons.net.rmi;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import net.compor.commons.net.ClientConnectionIF;
import net.compor.commons.net.CommunicationFactoryIF;
import net.compor.commons.net.ComporCannotCreateClientConnectionException;
import net.compor.commons.net.ComporCannotCreateServerConnectionException;
import net.compor.commons.net.ServerConnectionIF;
import net.compor.commons.net.ServerListenerIF;

/**
 * Implements an RMI Factory for communication through Remote Method Invocation.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class RMIFactory implements CommunicationFactoryIF {

  /*
   * (non-Javadoc)
   *
   * @see CommunicationFactoryIF#createConnection()
   */
  public ClientConnectionIF createClientConnection(final String _address,
      final int _port) throws ComporCannotCreateClientConnectionException {
    ClientConnectionIF clientConnectionIF = null;
    try {
      clientConnectionIF = new RMIClientConnection(_address, _port);
    } catch (MalformedURLException mue) {
      throw new ComporCannotCreateClientConnectionException(_address, _port,
          mue);
    } catch (RemoteException re) {
      throw new ComporCannotCreateClientConnectionException(_address, _port,
          re);
    } catch (NotBoundException nbe) {
      throw new ComporCannotCreateClientConnectionException(_address, _port,
          nbe);
    }
    return clientConnectionIF;
  }

  /*
   * (non-Javadoc)
   *
   * @see CommunicationFactoryIF#createServerConnection(int)
   */
  public ServerConnectionIF createServerConnection(final int _port,
      final ServerListenerIF _listener)
      throws ComporCannotCreateServerConnectionException {
    RMIServerConnection rmiConnection;
    try {
      rmiConnection = new RMIServerConnection(_port, _listener);
    } catch (RemoteException re) {
      throw new ComporCannotCreateServerConnectionException(_port, re);
    }
    return rmiConnection;
  }

}
