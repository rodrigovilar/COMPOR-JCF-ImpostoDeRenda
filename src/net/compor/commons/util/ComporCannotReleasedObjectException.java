package net.compor.commons.util;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;

/**
 * Comment...
 *
 * @author emerson
 * @version 1.0
 */
public class ComporCannotReleasedObjectException extends ComporException {

  /** The object that should be released. */
  private Object objectToBeReleased;

  /**
   * Creates a new <code>ComporCannotReleasedObjectException</code> with the
   * specified object to be released and cause.
   *
   * @param _objectToBeReleased
   *          The object that should be released.
   * @param _cause
   *          The exception that generated this exception.
   */
  public ComporCannotReleasedObjectException(final Object _objectToBeReleased,
      final Throwable _cause) {
    super(_cause);
    objectToBeReleased = _objectToBeReleased;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return CommonsMessages.getInstance().get(
        "util.exception.cannotreleaseobject");
  }
}
