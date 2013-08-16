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

import java.util.Collection;

/**
 * Represents an abstract class for componoents, containers and adapters
 * according to <i><a href="http://www.compor.net"> COMPOR CMS </a> </i>. This
 * class is used to assure the recursive composition between containers and
 * components/adapters, as defined by the <i>Composite Design Pattern </i>.
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public abstract class AbstractComponent {

  /** Component name. */
  private String name;

  /** Container of this component. */
  private Container container;

  /**
   * Creates a new <code>AbstractComponent</code> with the specified name.
   * @param _name
   *          component name.
   * @throws AbstractComponentNullNameException
   *           if the component name is null.
   */
  public AbstractComponent(final String _name)
      throws AbstractComponentNullNameException {
    if (_name == null) {
      throw new AbstractComponentNullNameException();
    }
    name = _name;
  }

  /**
   * Returns the name of this component.
   * @return String component name.
   */
  public final String getName() {
    return name;
  }

  /**
   * Sets the name of this component.
   * @param _name
   *          component name.
   */
  public final void setName(final String _name) {
    name = _name;
  }

  /**
   * Returns the container of this component.
   * @return {@link Container} container of this component.
   */
  public final Container getContainer() {
    return container;
  }

  /**
   * Sets a container for this component.
   * @param _container
   *          container for which this component must be set.
   */
  protected void setContainer(final Container _container) {
    container = _container;
  }

  /**
   * Removes this component from its container.
   */
  protected void remove() {
    container = null;
  }

  /**
   * Invokes a service specified by <code>_serviceRequest</code>.
   * @param _serviceRequest
   *          service requested.
   * @return {@link ServiceResponse} Service response.
   * @throws InvalidParametersException
   *           if some parameter is invalid.
   * @throws IllegalAccessMethodException
   *           if there is an error while accessing the method that implements
   *           the service.
   * @throws ServiceNotImplementedException
   *           if the service is not implemented by the component.
   * @throws ComponentNotStartedException
   *           if the component has not been started yet.
   */
  protected abstract ServiceResponse doIt(final ServiceRequest _serviceRequest)
      throws InvalidParametersException, IllegalAccessMethodException,
      ServiceNotImplementedException, ComponentNotStartedException;

  /**
   * Implements the receive of service requests. when the calling is going down
   * the components' structure.
   * @param _serviceRequest
   *          request for a service.
   * @return ServiceResponse The result of executing the requested service.
   * @throws InvalidParametersException
   *           if some parameter is invalid.
   * @throws IllegalAccessMethodException
   *           if there is an error while accessing the method that implements
   *           the service.
   * @throws ComponentNotStartedException
   *           if the component has not been started yet.
   */
  protected abstract ServiceResponse receiveRequest(
      final ServiceRequest _serviceRequest) throws InvalidParametersException,
      IllegalAccessMethodException, ComponentNotStartedException;

  /**
   * Receives an interest event announced by some component of the system.
   * @param _eventAnnouncement
   *          announcement object of the event.
   */
  protected abstract void receiveEvent(
      final EventAnnouncement _eventAnnouncement);

  /**
   * Indicates if this component provides the specified service.
   * @param _serviceAlias
   *          alias of the provided service.
   * @return boolean true if the service is provided, false on the contrary.
   */
  public abstract boolean containsProvidedService(final String _serviceAlias);

  /**
   * Indicates if the specified event is of interest to this component.
   * @param _eventAlias
   *          alias of the event.
   * @return boolean true if it is of interest, false on the contrary.
   */
  public abstract boolean containsEventOfInterest(final String _eventAlias);

  /**
   * Returns the services provided by this component.
   * @return Collection services provided by this component.
   */
  public abstract Collection getProvidedServices();

  /**
   * Returns the events that are of interest to this component.
   * @return Collection events that are interesting for this component.
   */
  public abstract Collection getEventsOfInterest();

  /**
   * Starts the execution of the component.
   */
  public abstract void start();

  /**
   * Specific implementation for starting an instance of AbstractComponent
   * subclass.
   */
  protected abstract void startImpl();

  /**
   * Stops de execution of the component.
   */
  public abstract void stop();

  /**
   * Specific implementation for stopping an instance of AbstractComponent
   * subclass.
   */
  protected abstract void stopImpl();

  /**
   * Propagates service providers updates to its child components.
   * @param _serviceAlias
   *          alias of the service to be updated.
   */
  protected void updateServiceReference(final String _serviceAlias) {
  }

  /**
   * Update service alias reference.
   * @param _oldAlias
   *          old alias.
   * @param _newAlias
   *          new alias.
   */
  protected void updateServiceAliasReference(final String _oldAlias,
      final String _newAlias) {
  }

  /**
   * Update event alias reference.
   * @param _oldAlias
   *          old alias.
   * @param _newAlias
   *          new alias.
   */
  protected void updateEventAliasReference(final String _oldAlias,
      final String _newAlias) {
  }

  /**
   * Indicates if the component is active.
   * @return boolean true, if it is active, false on the contrary.
   */
  public abstract boolean isActive();
}
