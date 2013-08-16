package net.compor.commons.net;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Exception thrown when there is an erros while trying to connect a socket to a
 * remote address.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public class ComporCannotEstablishConnectionException extends ComporException {

  private String address;

  private int port;

  /**
   * Creates a new <code>ComporCouldNotConnectSocketException</code> with the
   * specified address, port and cause.
   *
   * @param _address
   *          The address of the remote host.
   * @param _port
   *          The port on the remote host.
   * @param _cause
   *          The exception that generated the exception.
   */
  public ComporCannotEstablishConnectionException(final String _address,
      final int _port, final Throwable _cause) {
    super(_cause);
    address = _address;
    port = _port;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("net.exception.cannotestablishconnection"),
        new Object[] { address, String.valueOf(port) });
  }
}
