package net.compor.commons.net;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Exception thrown when some data may not be received by a socket or a
 * connection.
 *
 * @author Emerson Loureiro.
 */
public class ComporCannotReceiveDataException extends ComporException {

  /**
   * Creates a <code>ComporCannotReceiveDataException</code> with the
   * specified exception.
   *
   * @param _exception
   *          The exception which generated this exception.
   */
  public ComporCannotReceiveDataException(final Throwable _exception) {
    super(_exception);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("net.exception.cannotreceivedata"), new Object[] {});
  }
}
