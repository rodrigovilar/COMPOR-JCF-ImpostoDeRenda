package net.compor.commons.net;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporRuntimeException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Indicates problems on creating a client connection operation.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class ComporCannotCreateClientConnectionException extends
    ComporRuntimeException {

  /**
   * Connection address.
   */
  private String address;

  /**
   * Connection port.
   */
  private int port;

  /**
   * Creates a new <code>ComporCannotCreateClientConnectionException</code>
   * with the specified address, port, and cause.
   *
   * @param _address
   *          Connection address.
   * @param _port
   *          Connection port.
   * @param _cause
   *          The cause of the exception. The null value is allowed and
   *          indicates that it is inexistent of unknown.
   */
  public ComporCannotCreateClientConnectionException(final String _address,
      final int _port, final Throwable _cause) {
    super(_cause);
    address = _address;
    port = _port;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporRuntimeException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("net.exception.cannotcreateclientconnection"),
        new Object[] { address, String.valueOf(port) });
  }

  /**
   * Returns the address of this ComporCannotCreateClientConnectionException.
   *
   * @return String the address of this
   *         ComporCannotCreateClientConnectionException.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Returns the port of this ComporCannotCreateClientConnectionException.
   *
   * @return int the port of this ComporCannotCreateClientConnectionException.
   */
  public int getPort() {
    return port;
  }
}
