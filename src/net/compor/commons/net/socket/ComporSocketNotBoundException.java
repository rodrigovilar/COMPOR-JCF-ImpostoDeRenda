package net.compor.commons.net.socket;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Exception thrown when some operation os performed on a socket that is not
 * bound to any port.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public class ComporSocketNotBoundException extends ComporException {

  /**
   * Creates a <code>ComporSocketNotBoundException</code> with the specified
   * exception.
   *
   * @param _exception
   *          The exception that generated this exception.
   */
  public ComporSocketNotBoundException(final Throwable _exception) {
    super(_exception);
  }

  /**
   * Creates a <code>ComporCannotUnbindSocketException</code>.
   */
  public ComporSocketNotBoundException() {
    super();
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
