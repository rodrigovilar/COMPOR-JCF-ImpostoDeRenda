package net.compor.commons.net.rpc;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Exception thrown when there is a local error when trying to invoke a remote
 * method.
 *
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public class ComporCannotInvokeRemoteMethodException extends ComporException {

  /** The method that should�ve been invoked. */
  private String methodName;

  /**
   * Creates a new <code>ComporCannotInvokeRemoteMethod</code> with the
   * specified method name.
   *
   * @param _methodName
   *          The method that should�ve been invoked.
   * @param _cause
   *          The exception that generated this exception.
   */
  public ComporCannotInvokeRemoteMethodException(final String _methodName,
      final Throwable _cause) {
    super(_cause);
    methodName = _methodName;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("net.rpc.exception.cannotinvokeremotemethod"),
        new Object[] { methodName });
  }
}
