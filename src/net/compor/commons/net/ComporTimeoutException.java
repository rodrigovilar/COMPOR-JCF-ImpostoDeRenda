package net.compor.commons.net;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Exception thrown when some amount of time has been reached and due to this,
 * some task is not be performed.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public class ComporTimeoutException extends ComporException {

  /**
   * Creates a new <code>ComporTimeoutException</code>.
   */
  public ComporTimeoutException() {
    super();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("net.exception.timeout"), new Object[] {});
  }
}
