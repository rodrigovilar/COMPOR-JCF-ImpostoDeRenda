package net.compor.commons.net;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Exception thrown when some invalid URL is provided to some object.
 *
 * @author <a href="mailto:emerson@labpesquisas.tci.ufal.br">Emerson Loureiro
 *         </a>
 * @version 1.0
 */
public class ComporInvalidURLException extends ComporException {

  /** The invalid URL. */
  private String url;

  /**
   * Creates a new <code>ComporInvalidURLException</code> with the specified
   * full url and cause.
   *
   * @param _fullURL
   *          The full url, that is, <code>host name</code>+
   *          <code>port number</code> (e.g., http://www.compor.com:8081).
   * @param _cause
   *          The exception that generated this exception.
   */
  public ComporInvalidURLException(final String _fullURL,
      final Throwable _cause) {
    super(_cause);
    url = _fullURL;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("net.exception.invalidurl"),
        new Object[] { url });
  }
}
