package net.compor.commons.net.socket;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Exception thrown when there is an error while trying to close a network
 * connection.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public class ComporCannotCloseConnectionException extends ComporException {

  /**
   * Creates a new <code>ComporCannotCloseConnectionException</code> with the
   * specified cause.
   *
   * @param _cause
   *          The exception that generated this exception.
   */
  public ComporCannotCloseConnectionException(final Throwable _cause) {
    super(_cause);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("net.exception.socketnotbound"), new Object[] {});
  }
}
