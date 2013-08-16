package net.compor.commons.net.rpc;

/**
 * A default implementation of a remote method invocation.
 *
 * @author <a href="mailto:emerson@labpesquisas.tci.ufal.br">Emerson Loureiro
 *         </a>
 * @version 1.0
 */
public class RemoteMethodInvocationImpl implements RemoteMethodInvocationIF {

  /** The return of the remote method, if any. */
  private Object methodReturn;

  /** The remote method. */
  private RemoteMethodIF remoteMethod;

  /**
   * Creates a new <code>RemoteMethodInvocationImpl</code> with the specified
   * remote method and method return.
   *
   * @param _remoteMethod
   *          The remote method.
   * @param _methodReturn
   *          The return of the remote method.
   */
  public RemoteMethodInvocationImpl(final RemoteMethodIF _remoteMethod,
      final Object _methodReturn) {
    remoteMethod = _remoteMethod;
    methodReturn = _methodReturn;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.rpc.RemoteMethodInvocation#getMethod()
   */
  public RemoteMethodIF getMethod() {
    return remoteMethod;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.rpc.RemoteMethodInvocation#getReturn()
   */
  public Object getReturn() {
    return methodReturn;
  }
}
