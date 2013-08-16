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

import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * This exception is throw when a requested service is not implemented,
 * considering the whole component hyerarchy.
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 */
public class ServiceNotImplementedException extends ServiceInvocationException {

  /** Initial string buffer capacity. */
  private static final int INITIAL_STRING_BUFFER_CAPACITY = 16;

  /** Separator field. */
  private static final String PARAMETER_SEPARATOR = ", ";

  /** Name of the service that originated the exception. */
  private ServiceRequest serviceRequest;

  /**
   * Creates a new <code>ComporServiceNotImplementedException</code>
   * specifying the service.
   * @param _serviceRequest
   *          service that originated the exception.
   */
  public ServiceNotImplementedException(final ServiceRequest _serviceRequest) {
    super();
    serviceRequest = _serviceRequest;
  }

  /**
   * {@inheritDoc}
   */
  protected String getFormattedMessage() {
    ServiceRequest request = this.getServiceRequest();
    Object[] types = request.getParameterTypes();

    StringBuffer typesStr = new StringBuffer(INITIAL_STRING_BUFFER_CAPACITY
        * types.length);

    for (int i = 0; i < types.length; i++) {
      typesStr.append(types[i]);
      if (i < types.length - 1) {
        typesStr.append(PARAMETER_SEPARATOR);
      }
    }

    return ComporResourceBundleMessageFormat.formatProperty(JCFMessages
        .getInstance().get("exception.servicenotimplemented"), new Object[] {
        request.getId(), request.getServiceAlias(), typesStr.toString() });
    //$NON-NLS-1$
  }

  /**
   * Returns the name of the service that has not been implemented.
   * @return String name of the service that has not been implemented.
   */
  public ServiceRequest getServiceRequest() {
    return serviceRequest;
  }

}
