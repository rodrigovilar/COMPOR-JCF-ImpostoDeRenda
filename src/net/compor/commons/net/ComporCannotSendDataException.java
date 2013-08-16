package net.compor.commons.net;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporRuntimeException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Indicates problems on sending data operation.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class ComporCannotSendDataException extends ComporRuntimeException {

  /**
   * Server address.
   */
  private String address;

  /**
   * Server port.
   */
  private int port;

  /**
   * Type of the sending data.
   */
  private Class dataType;

  /**
   * Sending data.
   */
  private Object data;

  /**
   * Creates a new <code>ComporCannotSendDataException</code> with the
   * specified address, port, dataType, data and cause.
   *
   * @param _address
   *          Server address.
   * @param _port
   *          Server port.
   * @param _dataType
   *          Type of the sending data.
   * @param _data
   *          Sending data.
   * @param _cause
   *          The cause of the exception. The null value is allowed and
   *          indicates that it is inexistent of unknown.
   */
  public ComporCannotSendDataException(final String _address, final int _port,
      final Class _dataType, final Object _data, final Throwable _cause) {
    super(_cause);
    address = _address;
    port = _port;
    dataType = _dataType;
    data = _data;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.lang.ComporRuntimeException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("net.exception.cannotsenddata"), new Object[] {
        address, String.valueOf(port) , dataType, data });
  }

  /**
   * Returns the address of this ComporCannotSendDataException.
   *
   * @return String the address of this ComporCannotSendDataException.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Returns the data of this ComporCannotSendDataException.
   *
   * @return Object the data of this ComporCannotSendDataException.
   */
  public Object getData() {
    return data;
  }

  /**
   * Returns the dataType of this ComporCannotSendDataException.
   *
   * @return Class the dataType of this ComporCannotSendDataException.
   */
  public Class getDataType() {
    return dataType;
  }

  /**
   * Returns the port of this ComporCannotSendDataException.
   *
   * @return int the port of this ComporCannotSendDataException.
   */
  public int getPort() {
    return port;
  }
}
