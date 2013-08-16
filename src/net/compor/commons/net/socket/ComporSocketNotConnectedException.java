package net.compor.commons.net.socket;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Exception thrown when some action is performed on a socket that should be
 * connected and it is not.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public class ComporSocketNotConnectedException extends ComporException {

  /**
   * Creates a new <code>ComporSocketNotConnectedException</code>.
   */
  public ComporSocketNotConnectedException() {
    super();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat
        .formatProperty(CommonsMessages.getInstance().get(
            "net.exception.socketnotconnected"), new Object[] {});
  }
}
