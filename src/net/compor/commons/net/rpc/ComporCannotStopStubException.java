package net.compor.commons.net.rpc;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Exception thrown when there is an error while stoping a server stub.
 *
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public class ComporCannotStopStubException extends ComporException {

  /** The full URL on which the stub should stop. */
  private String fullURL;

  /**
   * Creates a new <code>ComporCannotStartStubException</code> with the
   * specified url and cause.
   *
   * @param _fullURL
   *          The full URL on which the stub should stop (i.e.,
   *          <code>host name</code>+<code>port number</code> (e.g.,
   *          http://www.compor.net:8081).
   * @param _cause
   *          The exception that generated this exception.
   */
  public ComporCannotStopStubException(final String _fullURL,
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
        .getInstance().get("net.rpc.exception.cannotstopserverstub"),
        new Object[] { fullURL });
  }
}
