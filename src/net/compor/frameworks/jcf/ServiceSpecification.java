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

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a service specification. All services {@link AbstractService} are
 * associated to an event specification.
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @version 1.0
 */
public class ServiceSpecification {

  /** Name of the service represented by this specification. */
  private String serviceName;

  /** Description of the service represented by this specification. */
  private String serviceDescription;

  /** List of parameters for the service represented by this specification. */
  private List serviceParameters;

  /** List of exceptions for the service represented by this specification. */
  private List serviceExceptions;

  /** Return of the service represented by this specification. */
  private ComporType serviceReturn;

  /**
   * Creates a new <code>ServiceSpecification</code> with the specified name,
   * description, parameters, exceptions, and return.
   * @param _serviceName
   *          name of the service represented by this specification.
   * @param _serviceDescription
   *          description of the service represented by this specification.
   * @param _serviceParameters
   *          list of parameters for the service represented by this
   *          specification.
   * @param _serviceExceptions
   *          list of exceptions for the event represented by this
   *          specification.
   * @param _serviceReturn
   *          return of the event represented by this specification.
   */
  public ServiceSpecification(final String _serviceName,
      final String _serviceDescription, final List _serviceParameters,
      final List _serviceExceptions, final ComporType _serviceReturn) {
    if (_serviceName == null) {
      throw new ServiceNameException();
    }
    serviceName = _serviceName;
    serviceDescription = _serviceDescription;
    serviceParameters = _serviceParameters;
    serviceExceptions = _serviceExceptions;
    serviceReturn = _serviceReturn;
  }

  /**
   * Creates a new <code>ServiceSpecification</code> with name
   * <code>serviceName</code>.
   * @param _serviceName
   *          name of the service.
   */
  public ServiceSpecification(final String _serviceName) {
    this(_serviceName, "", new ArrayList(), new ArrayList(), null);
  }

  /**
   * Returns the name of the service represented by this specification.
   * @return String service name.
   */
  public final String getServiceName() {
    return serviceName;
  }

  /**
   * Returns the description of the service represented by this specification.
   * @return String description of the service represented by this
   *         specification.
   */
  public final String getServiceDescription() {
    return serviceDescription;
  }

  /**
   * Returns the list of parameters for the service represented by this
   * specification.
   * @return List list of parameters for the service represented by this
   *         specification.
   */
  public final List getServiceParameters() {
    return serviceParameters;
  }

  /**
   * Returns the list of exceptions for the service represented by this
   * specification.
   * @return List list of exceptions for the service represented by this
   *         specification.
   */
  public final List getServiceExceptions() {
    return serviceExceptions;
  }

  /**
   * Returns the return type of the service represented by this specification.
   * @return {@link ComporType} return type of the service represented by this
   *         specification.
   */
  public final ComporType getServiceReturn() {
    return serviceReturn;
  }
}
