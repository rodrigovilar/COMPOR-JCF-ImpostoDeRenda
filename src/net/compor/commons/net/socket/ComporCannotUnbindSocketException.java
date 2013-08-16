package net.compor.commons.net.socket;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Exception thrown when a socket may not be unbound.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public class ComporCannotUnbindSocketException extends ComporException {

  /** The port that was used to bind the socket. */
  private int portNumber;

  /**
   * Creates a <code>ComporCannotUnbindSocketException</code> with the
   * specified port number and exception.
   *
   * @param _portNumber
   *          The port that was used to bind the socket.
   * @param _exception
   *          The exception that generated this exception.
   */
  public ComporCannotUnbindSocketException(final int _portNumber,
      final Throwable _exception) {
    super(_exception);
    portNumber = _portNumber;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("net.exception.cannotunbind"), new Object[] { String
        .valueOf(this.portNumber) });
  }
}
