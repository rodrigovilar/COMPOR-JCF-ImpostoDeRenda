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

import java.lang.reflect.Method;

import net.compor.commons.util.ComporArrays;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * This exception is throw when the parameters of a service request are
 * incorrects.
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public class InvalidParametersException extends ServiceInvocationException {

  /** Service that has caused the exception. */
  private ServiceRequest serviceRequest;

  /** Types of the method parameters. */
  private Class[] parameterTypes;

  /**
   * Creates a new <code>ComporInvalidParametersException</code> specifying
   * the request, method and cause.
   * @param _serviceRequest
   *          request of the service.
   * @param _method
   *          method that implements the service.
   * @param _cause
   *          cause of the exception. The value <code>null</code> is
   *          allowed and indicates the it is inexistent or unknown.
   */
  public InvalidParametersException(final ServiceRequest _serviceRequest,
      final Method _method, final Throwable _cause) {
    super(_cause);
    serviceRequest = _serviceRequest;
    parameterTypes = _method.getParameterTypes();
  }

  /**
   * Returns the service request that has caused the exceptions.
   * @return {@link ServiceRequest} the request that caused the exception.
   */
  public ServiceRequest getServiceRequest() {
    return serviceRequest;
  }

  /**
   * Returns the types of the method parameters.
   * @return Class[] types of the parameters.
   */
  public Class[] getParameterTypes() {
    return parameterTypes;
  }

  /**
   * {@inheritDoc}
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(JCFMessages
        .getInstance().get("exception.invalidparameters"), //$NON-NLS-1$
        new Object[] { serviceRequest.getServiceAlias(),
            ComporArrays.asString(getParameterTypes()),
            ComporArrays.asString(serviceRequest.getParameterTypes()) });
  }

}
