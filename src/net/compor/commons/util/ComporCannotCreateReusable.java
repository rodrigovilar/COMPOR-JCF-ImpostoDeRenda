package net.compor.commons.util;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;

/**
 * Exception thrown when there is an error while creating a new object in an
 * object pool.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public class ComporCannotCreateReusable extends ComporException {

  /**
   * Creates a new <code>ComporCouldNotCreateReusable</code> with the
   * specified cause.
   *
   * @param _cause
   *          The exception that generated this one.
   */
  public ComporCannotCreateReusable(final Throwable _cause) {
    super(_cause);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return CommonsMessages.getInstance().get(
        "util.exception.cannotcreatereusable");
  }
}
