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

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Represents a service request performed by some component.
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public class ServiceRequest implements Serializable {

  /**
   * Serial Version UID for this class.
   */
  private static final long serialVersionUID = 1L;

  /** Identifier of the service requester. */
  private ServiceRequestId requestId;

  /** Alias of the service. */
  private String serviceAlias;

  /** Service name. */
  private String serviceName;

  /** List of parameters of the service. */
  private List parameters;

  /**
   * Creates a new <code>ServiceRequest</code> specifying a name.
   * @param _serviceName
   *          name of the service.
   */
  public ServiceRequest(final String _serviceName) {
    if (_serviceName == null) {
      throw new ServiceNameException();
    }
    serviceName = _serviceName;
    serviceAlias = _serviceName;
    requestId = new ServiceRequestId();
    parameters = new Vector();
  }

  /**
   * Creates a new <code>ServiceRequest</code> specifying a name and
   * parameters.
   * @param _serviceName
   *          name of the service.
   * @param _parameters
   *          list of parameters of the service.
   */
  public ServiceRequest(final String _serviceName, final Object[] _parameters) {
    this(_serviceName);
    parameters = Arrays.asList(_parameters);
  }

  /**
   * Returns the ID of the requested service.
   * @return {@link ServiceRequestId} service request ID.
   */
  public final ServiceRequestId getId() {
    return requestId;
  }

  /**
   * Returns the alias of the service.
   * @return String alias of te service.
   */
  public final String getServiceAlias() {
    return serviceAlias;
  }

  /**
   * Adds a new parameter to this service request.
   * @param _parameter
   *          parameter to be added.
   */
  public final void addParameter(final Object _parameter) {
    parameters.add(_parameter);
  }

  /**
   * Returns an iterator of the parameters of the service.
   * @return {@link Iterator} iterator for the parameters.
   */
  public final Iterator parametersIterator() {
    return parameters.iterator();
  }

  /**
   * Returns an array with the parameters of the service.
   * @return Object[] parameters as array.
   */
  public final Object[] getParametersAsArray() {
    return parameters.toArray();
  }

  /**
   * Returns the types of the parameters of the service.
   * @return Object[] parameter types.
   */
  public final Object[] getParameterTypes() {
    Object[] types = new Object[parameters.size()];
    int index = 0;
    for (Iterator iter = this.parametersIterator(); iter.hasNext();) {
      types[index] = iter.next().getClass().getName();
      index++;
    }
    return types;
  }

  /**
   * Set the service name.
   * @return String service name.
   */
  public String getServiceName() {
    return serviceName;
  }

  /**
   * Set the service alias.
   * @param _serviceAlias
   *          service alias
   */
  public void setServiceAlias(final String _serviceAlias) {
    serviceAlias = _serviceAlias;
  }

}
