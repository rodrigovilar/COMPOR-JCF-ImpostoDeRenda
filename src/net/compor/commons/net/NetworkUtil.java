package net.compor.commons.net;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/**
 * Interfaces for some networking constants.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public final class NetworkUtil {

  /** The loopback address. */
  public static final String LOOPBACK_ADDRESS = "127.0.0.1";

  /** The biggest int that may be used as a port number. */
  public static final int BIGGEST_PORT_NUMBER = 65535;

  /**
   * Private constructor to avoid instantiations.
   */
  private NetworkUtil() {
  }

  /**
   * Returns a free port number from the number passed in the parameter.
   *
   * @param _startingPort
   *          The starting port of the search.
   * @return int.
   */
  public static synchronized int getFreePort(final int _startingPort) {
    int foundPort = 0;

    for (int i = _startingPort; i < BIGGEST_PORT_NUMBER; i++) {
      try {
        DatagramSocket socket = new DatagramSocket(i);
        socket.close();

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.setSoTimeout(200);
        serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), i));
        serverSocket.close();

        return i;
      } catch (UnknownHostException e) {
      } catch (IOException e) {
      }
    }
    return foundPort;
  }

  /**
   * Returns an array of bytes from a serializable object.
   *
   * @param _serializable
   *          The serializable object.
   * @return byte[]
   * @throws IOException
   *           If there is some error while converting the data to bytes.
   */
  public static byte[] getAsByteArray(final Serializable _serializable)
      throws IOException {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(
        new BufferedOutputStream(byteStream));

    objectOutputStream.flush();
    objectOutputStream.writeObject(_serializable);
    objectOutputStream.flush();

    byte[] byteContents = byteStream.toByteArray();
    objectOutputStream.close();

    return byteContents;
  }
}
