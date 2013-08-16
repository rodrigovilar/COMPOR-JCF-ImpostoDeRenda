package net.compor.commons.net.socket;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Exception thrown when there is an attempt to bind a socket that is already
 * bound to another port.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public class ComporSocketAlreadyBoundException extends ComporException {

  /** The port that was used to bind the socket. */
  private int boundPort;

  /** The port that was used to rebind the socket. */
  private int portToBeBind;

  /**
   * Creates a <code>ComporCannotBindSocketException</code> with the specified
   * port number and exception.
   *
   * @param _boundPort
   *          The port that was used to bind the socket.
   * @param _portToBeBind
   *          The port used to rebind the socket.
   */
  public ComporSocketAlreadyBoundException(final int _boundPort,
      final int _portToBeBind) {
    super();
    this.boundPort = _boundPort;
    this.portToBeBind = _portToBeBind;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("net.exception.socketalreadybound"), new Object[] {
        String.valueOf(boundPort), String.valueOf(portToBeBind) });
  }
}
