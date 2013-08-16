package net.compor.commons.net.socket;

import java.io.Serializable;

import net.compor.commons.net.ComporCannotReceiveDataException;
import net.compor.commons.net.ComporTimeoutException;

/**
 * Interface for sockets which may listen for incoming data in an synchronous
 * way without the need for a previously established connection.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public interface SynchronousReceiverSocketIF {

  Serializable receive(final int _timeout)
      throws ComporCannotReceiveDataException, ComporTimeoutException;
}
