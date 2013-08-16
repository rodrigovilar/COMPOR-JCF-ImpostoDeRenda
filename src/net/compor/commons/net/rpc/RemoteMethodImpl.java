package net.compor.commons.net.rpc;

/**
 * Default implementation of a remote method.
 *
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public class RemoteMethodImpl implements RemoteMethodIF {

  /** The name of the method. */
  private String methodName;

  /** The name of the method. */
  private Object[] parameters;

  /**
   * Creates a new <code>RemoteMethodImpl</code> with the specified method
   * name and parameters.
   *
   * @param _methodName
   *          The name of the method.
   * @param _parameters
   *          The name of the method.
   */
  public RemoteMethodImpl(final String _methodName,
      final Object[] _parameters) {
    methodName = _methodName;
    parameters = _parameters;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.rpc.RemoteMethodIF#getMethodName()
   */
  public String getMethodName() {
    return this.methodName;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.rpc.RemoteMethodIF#getParameters()
   */
  public Object[] getParameters() {
    return this.parameters;
  }
}
