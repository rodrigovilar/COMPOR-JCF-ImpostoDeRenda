package net.compor.commons.net;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporRuntimeException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Indicates problems on stoping a connection.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class ComporCannotStopConnectionException
  extends ComporRuntimeException {

  /**
   * Server address.
   */
  private String address;

  /**
   * Server port.
   */
  private int port;

  /**
   * Creates a new <code>ComporCannotStopConnectionException</code> with the
   * specified.
   *
   * @param _address
   *          Server address
   * @param _port
   *          Server port
   * @param _cause
   *           The cause of the exception. The null value is allowed and
   *           indicates that it is inexistent of unknown.
   */
  public ComporCannotStopConnectionException(final String _address,
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
        .getInstance().get("net.exception.cannotstopconnection"), new Object[] {
        address, String.valueOf(port) });
  }

  /**
   * Returns the address of this ComporCannotStopConnectionException.
   *
   * @return String the address of this ComporCannotStopConnectionException.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Returns the port of this ComporCannotStopConnectionException.
   *
   * @return int the port of this ComporCannotStopConnectionException.
   */
  public int getPort() {
    return port;
  }
}
