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

import net.compor.commons.async.ComporAsynchronousMethodInvocation;
import net.compor.commons.async.ComporAsynchronousResult;

/**
 * Represents an asynchronous service invocation as an asynchronous method call.
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @version 1.0
 */
public class ComponentAsyncService extends ComporAsynchronousMethodInvocation {

  /** Service request to be executa asynchronously. */
  private ServiceRequest serviceRequest;

  /** Asynchronous service invoker. */
  private AsyncServiceInvokerIF serviceInvoker;

  /**
   * Creates a new <code>ComponentAsyncService</code> specifyig the functional
   * component and the service request.
   * @param _invoker
   *          asynchronous service invoker.
   * @param _serviceRequest
   *          service request to be done asynchronously.
   */
  public ComponentAsyncService(final AsyncServiceInvokerIF _invoker,
      final ServiceRequest _serviceRequest) {
    serviceRequest = _serviceRequest;
    serviceInvoker = _invoker;
  }

  /**
   * {@inheritDoc}
   */
  protected void receiveAsynchronousMethodResult(
      final ComporAsynchronousResult _response) {
    ServiceResponse serviceResponse = (ServiceResponse) _response.getResult();
    serviceResponse.setException(_response.getException());
    serviceInvoker.receiveAsyncServiceResponse(serviceResponse);
  }

  /**
   * {@inheritDoc}
   */
  protected Object invokeAsynchronousMethod() throws Exception {
    return serviceInvoker.invokeAsyncService(serviceRequest);
  }
}
