package net.compor.commons.net;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporRuntimeException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Indicates problems on creating a server connection operation.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class ComporCannotCreateServerConnectionException extends
    ComporRuntimeException {

  /**
   * Server connection port.
   */
  private int port;

  /**
   * Creates a new <code>ComporCannotCreateServerConnectionException</code>
   * with the specified port and cause.
   *
   * @param _port
   *          server port
   * @param _cause
   *           The cause of the exception. The null value is allowed and
   *           indicates that it is inexistent of unknown.
   */
  public ComporCannotCreateServerConnectionException(final int _port,
      final Throwable _cause) {
    super(_cause);
    port = _port;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporRuntimeException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("net.exception.cannotcreateserverconnection"),
        new Object[] { String.valueOf(port) });
  }

  /**
   * Returns the port of this ComporCannotCreateServerConnectionException.
   *
   * @return int the port of this ComporCannotCreateServerConnectionException.
   */
  public int getPort() {
    return port;
  }
}
