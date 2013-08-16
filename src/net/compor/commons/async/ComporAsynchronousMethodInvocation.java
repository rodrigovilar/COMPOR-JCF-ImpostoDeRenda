package net.compor.commons.async;

/**
 * Represents an anychronous method invocation.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public abstract class ComporAsynchronousMethodInvocation extends Thread {

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Runnable#run()
   */
  public void run() {
    ComporAsynchronousResult result = new ComporAsynchronousResult();
    try {
      result.setResult(invokeAsynchronousMethod());
    } catch (Exception e) {
      result.setException(e);
    }
    receiveAsynchronousMethodResult(result);
  }

  /**
   * Invokes the method in a new thread. Starts the asychronous call.
   */
  public void invoke() {
    super.start();
  }

  /**
   * Method to be implemented by the subclasses for receiving the response of
   * the invocation of the method.
   *
   * @param _result
   *          Result of the method invocation.
   */
  protected abstract void receiveAsynchronousMethodResult(
      final ComporAsynchronousResult _result);

  /**
   * Method to be implemented by the subclasses to invoke the asynchronous
   * method.
   *
   * @return Object Result of the invocation.
   * @throws Exception
   *           If there are errors on the method invocation.
   */
  protected abstract Object invokeAsynchronousMethod() throws Exception;
}
