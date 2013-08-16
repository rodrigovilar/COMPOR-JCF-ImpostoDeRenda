package net.compor.commons.net.rpc;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Exception thrown when it is not possible to create a stub.
 *
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public class ComporCannotCreateStubException extends ComporException {

  /** The full URL on which the stub should be created. */
  private String fullURL;

  /**
   * Creates a new <code>ComporCannotCreateStubException</code> with the
   * specified full URl and cause.
   *
   * @param _fullURL
   * @param _cause
   */
  public ComporCannotCreateStubException(final String _fullURL,
      final Throwable _cause) {
    super(_cause);
    fullURL = _fullURL;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("net.rpc.exception.cannotcreatestub"),
        new Object[] { fullURL });
  }
}
