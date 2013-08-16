package net.compor.commons.net;

/**
 * Represents an interface for a specific server connection according to the
 * Abstract Factory Design Pattern [GoF 95].
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public interface ServerConnectionIF {

  /**
   * Default server address.
   */
  String DEFAULT_SERVER_ADDRESS = "localhost";

  /**
   * Start the server connection.
   *
   * @throws ComporCannotStartConnectionException
   *           If there are problems on starting the connection.
   */
  void start() throws ComporCannotStartConnectionException;

  /**
   * Stop the server connection.
   *
   * @throws ComporCannotStopConnectionException
   *           If there are problems on stoping the connection.
   */
  void stop() throws ComporCannotStopConnectionException;

  /**
   * Adds a new server listener for receiving the content from the network.
   *
   * @param _serverListenerIF
   *          listener to be added
   */
  void setServerListener(final ServerListenerIF _serverListenerIF);

}
