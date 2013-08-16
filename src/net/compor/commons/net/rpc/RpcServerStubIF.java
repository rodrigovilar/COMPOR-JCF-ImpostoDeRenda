package net.compor.commons.net.rpc;

/**
 * A server stub, that is, the one which receives method invocation requests. In
 * order to receive method invocation requestes instances of the server stubs
 * must fist be started, through the <code>start()</code> method. When no
 * longer needed, the server stub must be stopped, through the
 * <code>stop()</code> method, releasing all associated resources (e.g.,
 * connections).
 *
 * @author <a href="mailto:emerson@labpesquisas.tci.ufal.br">Emerson Loureiro.
 *         </a>
 * @version 1.0
 */
public interface RpcServerStubIF {

  /**
   * Starts the execution of the server stub.
   */
  void start() throws ComporCannotStartStubException;

  /**
   * Stops the execution of the server stub.
   */
  void stop() throws ComporCannotStopStubException;;
}
