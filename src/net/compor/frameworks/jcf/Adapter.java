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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * Represents a adapter according to the <i>COMPOR Component Model </i>.
 * @author <a href="mailto:marcos@compor.net">Marcos Pereira</a>
 * @version 1.0
 */
public class Adapter extends Container {

  /** Functional component. */
  private FunctionalComponent functionalComponent;

  /** Adapted services. */
  private Map adaptedServices;

  /** Adapted events. */
  private Map adaptedEvents;

  /**
   * Creates a new reference to <code>Adapter</code>.
   * @param _name
   *          adapter name.
   */
  public Adapter(final String _name) {
    super(_name);
    adaptedServices = new Hashtable();
    adaptedEvents = new Hashtable();
  }

  /**
   * Invokes the original service.
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
  public ServiceResponse invokeOriginalService(
      final ServiceRequest _serviceRequest) throws InvalidParametersException,
      IllegalAccessMethodException, ComponentNotStartedException {
    if (functionalComponent.containsProvidedService(_serviceRequest
        .getServiceName())) {
      return functionalComponent.receiveRequest(_serviceRequest);
    }
    return null;
  }

  /**
   * Invokes the original event.
   * @param _eventAnnounce
   *          event announcement.
   */
  public void invokeOriginalEvent(final EventAnnouncement _eventAnnounce) {
    if (functionalComponent.containsEventOfInterest(_eventAnnounce
        .getEventName())) {
      functionalComponent.receiveEvent(_eventAnnounce);
    }
  }

  /**
   * Sets the functional component.
   * @param _functionalComponent
   *          functional component
   */
  public void setFunctionalComponent(
      final FunctionalComponent _functionalComponent) {
    this.remove();
    functionalComponent = _functionalComponent;
    functionalComponent.getContainer().addComponent(this);
    this.setContainer(functionalComponent.getContainer());
  }

  /**
   * Adds a adapted service.
   * @param _adaptedService
   *          adapted service
   */
  public final void addAdaptedService(final AdaptedService _adaptedService) {
    _adaptedService.setAdapter(this);
    adaptedServices.put(_adaptedService.getAlias(), _adaptedService);
    this.getContainer().updateServiceReference(_adaptedService.getAlias());
    this.getContainer().addProvidedService(_adaptedService, this);
  }

  /**
   * Adds a adapted event.
   * @param _adaptedEvent
   *          adapted event
   */
  public final void addAdaptedEvent(final AdaptedEvent _adaptedEvent) {
    _adaptedEvent.setAdapter(this);
    adaptedEvents.put(_adaptedEvent.getAlias(), _adaptedEvent);
  }

  /**
   * Gets the adapted services.
   * @return Map Map of adapted services
   */
  private final Map getAdaptedServices() {
    return adaptedServices;
  }

  /**
   * Gets the adapted events.
   * @return Map Map of adapted events
   */
  private final Map getAdaptedEvents() {
    return adaptedEvents;
  }

  /**
   * {@inheritDoc}
   */
  public Collection getEventsOfInterest() {
    return this.getAdaptedEvents().values();
  }

  /**
   * {@inheritDoc}
   */
  public Collection getProvidedServices() {
    return this.getAdaptedServices().values();
  }

  /**
   * Returns the adapted service with the alias specified.
   * @param _alias
   *          alias of the adapted service.
   * @return AdaptedService adapted service with the alias specified.
   */
  public final AdaptedService getAdaptedService(final String _alias) {
    for (Iterator iter = this.getProvidedServices().iterator(); iter.hasNext();) {
      AdaptedService adaptedService = (AdaptedService) iter.next();
      if (adaptedService.getAlias().equals(_alias)) {
        return adaptedService;
      }
    }
    return null;
  }

  /**
   * Gets AdaptedEvent with the alias paramter.
   * @param _alias
   *          alias paramter.
   * @return Adapted event required.
   */
  public final AdaptedEvent getAdaptedEvent(final String _alias) {
    for (Iterator iter = this.getEventsOfInterest().iterator(); iter.hasNext();) {
      AdaptedEvent adaptedEvent = (AdaptedEvent) iter.next();
      if (adaptedEvent.getAlias().equals(_alias)) {
        return adaptedEvent;
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  protected ServiceResponse receiveRequest(final ServiceRequest _serviceRequest)
      throws InvalidParametersException, IllegalAccessMethodException,
      ComponentNotStartedException {
    AdaptedService adaptedService = this.getAdaptedService(_serviceRequest
        .getServiceAlias());
    if (adaptedService != null) {
      return adaptedService.invokeService(_serviceRequest);
    } else {
      return functionalComponent.receiveRequest(_serviceRequest);
    }

  }

  /**
   * {@inheritDoc}
   */
  protected void receiveEvent(final EventAnnouncement _eventAnounce) {
    AdaptedEvent adaptedEvent = this.getAdaptedEvent(_eventAnounce
        .getEventAlias());
    if (adaptedEvent != null) {
      adaptedEvent.invokeEvent(_eventAnounce);
    } else {
      functionalComponent.receiveEvent(_eventAnounce);
    }
  }

  /**
   * Removes this adapter.
   */
  public void removeAdapter() {
    Container container = this.getContainer();
    container.removeComponent(functionalComponent);
    container.removeComponent(this);
    container.addComponent(functionalComponent);
  }

}
