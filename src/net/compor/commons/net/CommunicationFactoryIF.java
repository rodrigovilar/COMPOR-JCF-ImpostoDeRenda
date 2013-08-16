package net.compor.commons.net;

/**
 * Represents a communication factory interface according to the Abstract
 * Factory Design Pattern [GoF 95].
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public interface CommunicationFactoryIF {

  /**
   * Creates a client connection for sending data trough the network.
   *
   * @param _address
   *          Server connection address
   * @param _port
   *          Server connection port
   * @return ClientConnectionIF Connection for sending data trough the network
   * @throws ComporCannotCreateClientConnectionException
   *           if the client connection cannot be created
   */
  ClientConnectionIF createClientConnection(final String _address,
      final int _port) throws ComporCannotCreateClientConnectionException;

  /**
   * Creates a connection for receiving data from the network.
   *
   * @param _port
   *          Server listener port
   * @param _listener
   *          An listener object to receive the messages
   * @return ServerConnectionIF a server connection
   * @throws ComporCannotCreateServerConnectionException
   *           if the server connection cannot be created
   */
  ServerConnectionIF createServerConnection(int _port,
      ServerListenerIF _listener)
      throws ComporCannotCreateServerConnectionException;
}
