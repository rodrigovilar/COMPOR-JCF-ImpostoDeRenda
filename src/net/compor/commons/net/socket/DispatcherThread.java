package net.compor.commons.net.socket;

import java.io.Serializable;

import net.compor.commons.async.ComporAsynchronousMethodInvocation;
import net.compor.commons.async.ComporAsynchronousResult;
import net.compor.commons.net.NetworkPacketIF;

/**
 * A thread for dispatching received packages for the applications.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public class DispatcherThread extends ComporAsynchronousMethodInvocation {

  /** The object that will receive the package. */
  private SocketListenerIF listener;

  /** The package that has been received. */
  private SocketMessage message;

  /**
   * Creates a new <code>SocketDispatcherThread</code> with the specified
   * listener and packet.
   *
   * @param _listener
   *          The object that will receive the package.
   * @param _packet
   *          The package that has been received.
   */
  public DispatcherThread(final SocketListenerIF _listener,
      final SocketMessage _packet) {
    listener = _listener;
    message = _packet;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.async.ComporAsynchronousMethodInvocation
   * #invokeAsynchronousMethod()
   */
  protected Object invokeAsynchronousMethod() throws Exception {
    if (this.message.getType() == SocketMessage.TYPE_NETWORK_PACKAGE) {
      this.listener.receive((NetworkPacketIF) this.message.getContent());
    } else {
      this.listener.receive((Serializable) this.message.getContent());
    }

    return null;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.async.ComporAsynchronousMethodInvocation
   * #receiveAsynchronousMethodResult(net.compor.commons.async.
   * ComporAsynchronousResult)
   */
  protected void receiveAsynchronousMethodResult(
      final ComporAsynchronousResult _result) {
    if (_result.getException() != null) {
      _result.getException().printStackTrace();
    }
  }
}
