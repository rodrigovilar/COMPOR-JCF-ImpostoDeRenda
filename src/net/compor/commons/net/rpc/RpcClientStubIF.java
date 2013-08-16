package net.compor.commons.net.rpc;

/**
 * Interface for objects intented to remotely invoke methods.
 *
 * @author <a href="mailto:emerson@labpesquisas.tci.ufal.br">Emerson Loureiro.
 *         </a>
 * @version 1.0
 */
public interface RpcClientStubIF {

  /**
   * Invokes a remote method in a synchronous way.
   *
   * @param _remoteMethod
   *          The method to be invoked remotely.
   * @return RemoteMethodResult.
   */
  RemoteMethodInvocationIF invoke(final RemoteMethodIF _remoteMethod)
      throws ComporCannotInvokeRemoteMethodException;

  /**
   * Invokes a remote method in an asynchronous way.
   *
   * @param _remoteMethod
   *          The remote method invoked.
   * @param _callbackListener
   *          The object that should receive the return.
   */
  void invokeAsynchronously(final RemoteMethodIF _remoteMethod,
      final CallbackListenerIF _callbackListener)
      throws ComporCannotInvokeRemoteMethodException;
}
