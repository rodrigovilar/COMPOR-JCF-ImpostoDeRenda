package net.compor.commons.net.rpc;

/**
 * Represents a remote method.
 *
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public interface RemoteMethodIF {

  /**
   * Returns the name of the object.
   *
   * @return String.
   */
  String getMethodName();

  /**
   * Returns an array representing the parameters of the remote method.
   *
   * @return Object[]
   */
  Object[] getParameters();
}
