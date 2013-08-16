package net.compor.commons.net;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Exception thrown when a host may not be found for a given address.
 *
 * @author Emerson Loureiro.
 */
public class ComporUnknownHostException extends ComporException {

  /** The address of the host which has not been found. */
  private String hostAddress;

  /**
   * Creates a new <code>ComporUnknownHostException</code> with the specified
   * host exception and address, which may be the IP address or the host name of
   * a remote host.
   *
   * @param _hostAddress
   *          The address of the host which has not been found.
   */
  public ComporUnknownHostException(final String _hostAddress,
      final Throwable _exception) {
    super(_exception);
    hostAddress = _hostAddress;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("net.exception.unknownhost"),
        new Object[] { hostAddress });
  }
}
