package net.compor.commons.async;

/**
 * Represents the result of an asynchronous method invocation.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class ComporAsynchronousResult {

  /** Result of the method invocation. */
  private Object data;

  /** Indicates of the result is already set. */
  private boolean resultIsSet;

  /**
   * Exception that possibly occured during the execution of the method that has
   * generated the response.
   */
  private Throwable exception;

  /**
   * Indicates if the result has already been defined.
   *
   * @return boolean true if the result has already been defined, false
   *         otherwise.
   */
  private boolean checkResult() {
    return resultIsSet;
  }

  /**
   * Returns the result of the execution. If the result has not been defined
   * yet, it blocks until it is defined.
   *
   * @return Object Result of the invocation.
   */
  public synchronized Object getResult() {
    while (!this.checkResult()) {
      try {
        super.wait();
      } catch (InterruptedException ie) {
        this.setException(new ComporAsynchronousInvocationException(ie));
      }
    }
    return data;
  }

  /**
   * Defines the result. This method should be called once. If it is called
   * again, nothin will be done and the result will be kept.
   *
   * @param _data
   *          Result to be defined.
   */
  public synchronized void setResult(final Object _data) {
    if (!this.checkResult()) {
      data = _data;
      resultIsSet = true;
      super.notifyAll();
    }
  }

  /**
   * Defines that the specifyed execution occured during the execution of the
   * method in an asynchronous way.
   *
   * @param _exception
   *          Exceprion thown during the method invocation.
   */
  public void setException(final Throwable _exception) {
    exception = _exception;
    resultIsSet = true;
  }

  /**
   * Returns the execution occured in the request that has originated the
   * result.
   *
   * @return exception
   */
  public Throwable getException() {
    return exception;
  }
}
