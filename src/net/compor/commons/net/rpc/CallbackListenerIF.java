package net.compor.commons.net.rpc;

/**
 * Interface for receiving the return of asynchronous remote method invocations.
 *
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public interface CallbackListenerIF {

  /**
   * Receives the return of a remote method invocation.
   *
   * @param _methodReturn
   *          The return of the invocation.
   */
  void receiveResult(final RemoteMethodInvocationIF _methodReturn);
}
