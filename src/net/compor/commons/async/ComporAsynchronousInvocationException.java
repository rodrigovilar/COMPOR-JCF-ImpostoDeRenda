package net.compor.commons.async;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;

/**
 * Indicates that an operation of asynchronous method invocation failed.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class ComporAsynchronousInvocationException extends ComporException {

  /**
   * Creates a new <code>ComporAsynchronousInvocationException</code> with the
   * specifyed cause.
   *
   * @param _cause
   *          The cause of the exception. The null value is allowed and
   *          indicates that it is inexistent of unknown.
   */
  public ComporAsynchronousInvocationException(final Throwable _cause) {
    super(_cause);
  }

  /*
   * (non-Javadoc)
   *
   * @see compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return CommonsMessages.getInstance().get(
        "async.exception.asynchronousinvocation"); //$NON-NLS-1$
  }
}
