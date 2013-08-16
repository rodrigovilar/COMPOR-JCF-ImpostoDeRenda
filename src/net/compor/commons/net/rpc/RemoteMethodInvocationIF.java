package net.compor.commons.net.rpc;

/**
 * Represents a call to a remote method.
 *
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public interface RemoteMethodInvocationIF {

  /**
   * Returns the method that has been remotely invoked.
   *
   * @return RemoteMethod.
   */
  RemoteMethodIF getMethod();

  /**
   * Returns the result of the remote method invocation, if any.
   *
   * @return Object.
   */
  Object getReturn();
}
