/*
 * COMPOR Java Component Framework (JCF) JCF is a subproject of the COMPOR
 * project (http://www.compor.net). JCF is a component-based framework for
 * developing software supporting dynamic unanticipated software evolution. JCF
 * is a Java implementation of the COMPOR Component Model Specification (CMS).
 * Further information can be found at http://www.compor.net.
 *
 * Copyright (C) 2006 Embedded Systems and Pervasive Computing Lab,
 *                      Federal University of Campina Grande
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option) any
 * later version. This program is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU
 * General Public License along with this program; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package net.compor.frameworks.jcf;

/**
 * Represents the response for a service request. The response is the result. If
 * a service invocation used during the execution of the system, differently
 * from {@link ComporType}, which is used to specify the type of return of a
 * service request.
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public class ServiceResponse implements java.io.Serializable {

  /** Id of the service request. */
  private ServiceRequestId requestId;

  /** Data related to the response of the service request. */
  private Object data;

  /** Exception throw during the execution of the service. */
  private Throwable exception;

  /**
   * Creates a new <code>ServiceResponse</code> specifying the identifyer of
   * the the request.
   * @param _requestId
   *          identifyer of the service request.
   */
  public ServiceResponse(final ServiceRequestId _requestId) {
    requestId = _requestId;
  }

  /**
   * Returns the identifyer of the sevice request that originated the response
   * to be used in assynchronous invocation.
   * @return {@link ServiceRequestId} the identifyer of the sevice request that
   *         originated the response.
   */
  public final ServiceRequestId getRequestId() {
    return requestId;
  }

  /**
   * Returns the data related to the response of the service invocation.
   * @return Object the data related to the response of the service invocation.
   */
  public final Object getData() {
    return data;
  }

  /**
   * Sets the data related to the response of the service invocation.
   * @param _data
   *          data related to the response of the service invocation.
   */
  public final void setData(final Object _data) {
    data = _data;
  }

  /**
   * Returns the exception throw during the request that originated the
   * response.
   * @return {@link Throwable}.
   */
  public final Throwable getException() {
    return exception;
  }

  /**
   * Sets the exception throw during the request that originated the response.
   * @param _exception
   *          exception throw during the request of the service.
   */
  public final void setException(final Throwable _exception) {
    exception = _exception;
  }

  /**
   * Indicates if an exception has been throw during the service request that
   * originated this response.
   * @return boolean true in case of error, false on the contrary.
   */
  public final boolean hasException() {
    return (exception != null);
  }

}
