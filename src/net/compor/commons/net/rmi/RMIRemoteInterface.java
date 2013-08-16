package net.compor.commons.net.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Default remote interface for RMI connections.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public interface RMIRemoteInterface extends Remote {

  /**
   * Makes a RMI based remote call.
   *
   * @param _contentType
   *          Type of the content to be sent.
   * @param _content
   *          Content to be sent.
   * @throws RemoteException
   *           If there are problems with the remote method invocation
   */
  void callRemoteMethod(final Class _contentType, final Object _content)
      throws RemoteException;

}
