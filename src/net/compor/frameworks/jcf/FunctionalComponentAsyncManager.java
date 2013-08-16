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
 * The manager for asynchronous operations of functional components.
 * @author <a href="mailto:marcos@compor.net">Marcos Pereira</a>
 * @version 1.0
 */
class FunctionalComponentAsyncManager implements AsyncEventAnnouncerIF,
    AsyncServiceInvokerIF {

  /** Functional component. */
  private FunctionalComponent functionalComponent;

  /**
   * Creates a new instance of the <code>FunctionalComponentAsynManager</code> class.
   * @param _functionalComponent
   *          functional component.
   */
  public FunctionalComponentAsyncManager(
      final FunctionalComponent _functionalComponent) {
    functionalComponent = _functionalComponent;
  }

  /**
   * {@inheritDoc}
   */
  public void announceAsyncEvent(final EventAnnouncement _eventAnnouncement)
      throws InvalidParametersException, ServiceNotImplementedException,
      IllegalAccessMethodException, ComponentNotStartedException {
    functionalComponent.getContainer().announceEvent(functionalComponent,
        _eventAnnouncement);
  }

  /**
   * {@inheritDoc}
   */
  public Object invokeAsyncService(final ServiceRequest _serviceRequest)
      throws InvalidParametersException, ServiceNotImplementedException,
      IllegalAccessMethodException, ComponentNotStartedException {
    return functionalComponent.doIt(_serviceRequest);
  }

  /**
   * {@inheritDoc}
   */
  public void receiveAsyncServiceResponse(final ServiceResponse _response) {
    functionalComponent.receiveAsyncServiceResponse(_response);
  }

}
