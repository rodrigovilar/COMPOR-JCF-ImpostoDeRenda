package net.compor.commons.net.filetransfer;

import java.io.InputStream;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporRuntimeException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Indicates problems on sending stream operation.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @version 1.0
 */
public class ComporCannotSendStreamException extends ComporRuntimeException {

  /**
   * Sending stream.
   */
  private InputStream inputStream;

  /**
   * Stream identifier.
   */
  private String identifier;

  /**
   * Creates a new <code>ComporCannotSendFileException</code> with the
   * specified stream, identifier and cause.
   *
   * @param _inputStream
   *          Sending stream.
   * @param _identifier
   *          Stream identifier.
   * @param _cause
   *          The cause of the exception. The null value is allowed and
   *          indicates that it is inexistent of unknown.
   */
  public ComporCannotSendStreamException(final InputStream _inputStream,
      final String _identifier, final Throwable _cause) {
    super(_cause);
    inputStream = _inputStream;
    identifier = _identifier;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporRuntimeException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("net.exception.cannotsendstream"),
        new Object[] { identifier });
  }

  /**
   * Returns the inputStream of this
   * <code>ComporCannotSendStreamException</code>.
   *
   * @return InputStream the inputStream of this
   *         <code>ComporCannotSendStreamException</code>.
   */
  public InputStream getInputStream() {
    return inputStream;
  }

  /**
   * Returns the identifier of this
   * <code>ComporCannotSendStreamException</code>.
   *
   * @return String the identifier of this
   *         <code>ComporCannotSendStreamException</code>.
   */
  public String getIdentifier() {
    return identifier;
  }
}
