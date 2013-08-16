package net.compor.commons.net.socket;

import net.compor.commons.net.ComporCannotReceiveDataException;
import net.compor.commons.net.ComporTimeoutException;
import net.compor.commons.net.NetworkPacketIF;

/**
 * Interface for sockets which may receive data from the network given a
 * previously established connection.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public interface SynchronousConnectionlessReceiverSocketIF {

  NetworkPacketIF receive(final int _timeout)
      throws ComporCannotReceiveDataException, ComporTimeoutException;
}
