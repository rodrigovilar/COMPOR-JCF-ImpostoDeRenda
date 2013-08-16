/*
 * COMPOR Java Component Framework (JCF) JCF is a subproject of the COMPOR
 * project (http://www.compor.net). JCF is a component-based framework for
 * developing software supporting dynamic unanticipated software evolution. JCF
 * is a Java implementation of the COMPOR Component Model Specification (CMS).
 * Further information can be found at http://www.compor.net. Copyright (C) 2006
 * Embedded Systems and Pervasive Computing Lab, Federal University of Campina
 * Grande This program is free software; you can redistribute it and/or modify
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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import net.compor.commons.util.ListSet;

/**
 * Represents a container according to <i><a href="http://www.compor.net">
 * COMPOR CMS </a> </i>.
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public class Container extends AbstractComponent {

  /**
   * Table of services provided by the children of this container (service name,
   * component).
   */
  private Map providedServicesMap;

  /**
   * Table of events interest to the chidren of this container (event alias,
   * components as <code>HashSet</code>).
   */
  private Map eventsOfInterestMap;

  /** The components that belong to this container. */
  private ListSet components;

  /**
   * Creates a new <code>Container</code> with the specified name.
   * @param _name
   *          container name.
   */
  public Container(final String _name) {
    super(_name);
    providedServicesMap = new HashMap();
    this.eventsOfInterestMap = new HashMap();
    this.components = new ListSet();
  }

  /**
   * Returns the child components of this container.
   * @return {@link java.util.Collection} child components of this container.
   */
  public final Collection getChildComponents() {
    return components;
  }

  /**
   * Returns the map of provided services of this container.
   * @return Map provided services map
   */
  Map getProvidedServicesMap() {
    return providedServicesMap;
  }

  /**
   * Returns the map of events of interest to this container.
   * @return Map interest events map.
   */
  Map getEventsOfInterestMap() {
    return eventsOfInterestMap;
  }

  /**
   * Returns the starting order of the component provided in the parameter.
   * @param _component
   *          component for which it will be returned the starting order.
   * @return int starting order of the component provided in the parameter.
   */
  public final int getComponentStartingOrder(final AbstractComponent _component) {
    return ((ListSet) this.getChildComponents()).indexOf(_component);
  }

  /**
   * Adds a new component to this container in the specified starting order.
   * @param _startingOrder
   *          order on which the component will be started.
   * @param _component
   *          {@link AbstractComponent} to be added.
   * @return boolean true if the component was added, false in the contrary.
   */
  public boolean addComponent(final int _startingOrder,
      final AbstractComponent _component) {
    if (this.getChildComponents().contains(_component)) {
      return false;
    }
    _component.setContainer(this);
    ((ListSet) this.getChildComponents()).add(_startingOrder, _component);
    this.addAllProvidedServicesOfComponent(_component);
    this.addAllEventsOfInterestOfComponent(_component);
    return true;
  }

  /**
   * Returns the service references to be updated.
   * @param _component
   *          Component.
   * @return Collection references to services.
   */
  private Collection getServiceReferencesToUpdate(
      final AbstractComponent _component) {
    Collection servicesToUpdate = new ArrayList();
    for (Iterator iter = this.getProvidedServices().iterator(); iter.hasNext();) {
      String currentAlias = ((AbstractService) iter.next()).getAlias();
      if (_component.containsProvidedService(currentAlias)) {
        servicesToUpdate.add(currentAlias);
      }
    }
    return servicesToUpdate;
  }

  /**
   * Adds a new component for this container and sets its starting order to be
   * the last one.
   * @param _component
   *          {@link AbstractComponent} to be added.
   * @return boolean true if the component was added, false in the contrary.
   */
  public final boolean addComponent(final AbstractComponent _component) {
    return this.addComponent(this.getChildComponents().size(), _component);
  }

  /**
   * Changes the starting order of a child component.
   * @param _component
   *          component which will have its starting order changed.
   * @param _newStartingOrder
   *          new starting order of the component.
   * @return boolean true if the starting order was set, false in the contrary.
   */
  public final boolean setComponentStartingOrder(
      final AbstractComponent _component, final int _newStartingOrder) {
    boolean removed = this.getChildComponents().remove(_component);
    if (!removed) {
      return false;
    }
    ((ListSet) this.getChildComponents()).add(_newStartingOrder, _component);
    return this.getChildComponents().contains(_component);
  }

  /**
   * Adds all services of the specified component to this container.
   * @param _component
   *          {@link AbstractComponent} whose services must be added.
   */
  void addAllProvidedServicesOfComponent(
      final AbstractComponent _component) {

    Collection servicesToUpdate = getServiceReferencesToUpdate(_component);
    for (Iterator iter = servicesToUpdate.iterator(); iter.hasNext();) {
      String existingServiceAlias = (String) iter.next();
      AbstractComponent provider = (AbstractComponent) this
          .getProvidedServicesMap().get(existingServiceAlias);
      if ((provider != null) && (!provider.equals(_component))) {
        provider.updateServiceReference(existingServiceAlias);
      }
    }

    Collection componentServices = _component.getProvidedServices();
    if (componentServices != null) {
      Iterator servicesIterator = componentServices.iterator();
      while (servicesIterator.hasNext()) {
        AbstractService providedService = (AbstractService) servicesIterator
            .next();
        this.addProvidedService(providedService, _component);
      }
      Container container = super.getContainer();
      if (container != null) {
        container.addAllProvidedServicesOfComponent(this);
      }
    }
  }

  /**
   * Adds a service and the component that implements such a service to the
   * table of provided services of this container.
   * @param _providedService
   *          services to be added.
   * @param _component
   *          component that implements the service.
   */
  void addProvidedService(final AbstractService _providedService,
      final AbstractComponent _component) {
    this.getProvidedServicesMap().put(_providedService.getAlias(), _component);
  }

  /**
   * Adds all events of the specified component to this container.
   * @param _component
   *          {@link AbstractComponent} whose events must be added.
   */
  void addAllEventsOfInterestOfComponent(
      final AbstractComponent _component) {

    Collection eventsOfInterest = _component.getEventsOfInterest();
    if (eventsOfInterest != null) {
      Iterator eventsIterator = eventsOfInterest.iterator();
      while (eventsIterator.hasNext()) {
        this
            .addEventOfInterest((AbstractEvent) eventsIterator.next(), _component);
      }
      Container container = super.getContainer();
      if (container != null) {
        container.addAllEventsOfInterestOfComponent(this);
      }
    }
  }

  /**
   * Adds an event and the component that is interested in such event to the
   * table of interest events of this container.
   * @param _eventOfInterest
   *          event to be added.
   * @param _component
   *          component that is interested in the event.
   */
  void addEventOfInterest(final AbstractEvent _eventOfInterest,
      final AbstractComponent _component) {
    HashSet interestedComponents = (HashSet) this.getEventsOfInterestMap().get(
        _eventOfInterest.getAlias());
    if (interestedComponents == null) {
      interestedComponents = new HashSet();
    }
    interestedComponents.add(_component);
    this.getEventsOfInterestMap().put(_eventOfInterest.getAlias(),
        interestedComponents);
  }

  /**
   * Removes a component from this container.
   * @param _component
   *          {@link AbstractComponent} to be removed.
   */
  public final void removeComponent(final AbstractComponent _component) {
    this.getChildComponents().remove(_component);
    this.removeAllProvidedServicesOfComponent(_component);
    this.removeAllEventsOfInterestOfComponent(_component);
    _component.remove();
  }

  /**
   * Removes all provided services of the specified component from this
   * container.
   * @param _component
   *          {@link AbstractComponent} whose services must be removed.
   */
  private void removeAllProvidedServicesOfComponent(
      final AbstractComponent _component) {
    if (this.getProvidedServicesMap().size() == 0) {
      return;
    }
    Collection servicesToRemove = new Vector();
    Iterator servicesIterator = _component.getProvidedServices().iterator();

    while (servicesIterator.hasNext()) {
      AbstractService providedService = (AbstractService) servicesIterator
          .next();
      if (this.getProvidedServicesMap().get(providedService.getAlias()).equals(
          _component)) {
        servicesToRemove.add(providedService);
      }
    }
    if (servicesToRemove.size() > 0) {
      this.removeCollectionOfProvidedServices(servicesToRemove);
    }
  }

  void removeCollectionOfProvidedServices(final Collection _services) {
    for (Iterator iter = _services.iterator(); iter.hasNext();) {
      AbstractService service = (AbstractService) iter.next();
      this.removeProvidedService(service);
    }
    Container container = super.getContainer();
    if (container != null) {
      container.removeCollectionOfProvidedServices(_services);
    }
  }

  /**
   * Removes the specified service from the list of provided services of this
   * container.
   * @param _providedService
   *          service to be removed.
   */
  void removeProvidedService(final AbstractService _providedService) {
    this.getProvidedServicesMap().remove(_providedService.getAlias());
  }

  /**
   * Removes all interest events of the specified component from this container.
   * @param _component
   *          {@link AbstractComponent} whose events must be removed.
   */
  void removeAllEventsOfInterestOfComponent(
      final AbstractComponent _component) {
    Iterator servicesIterator = _component.getEventsOfInterest().iterator();
    while (servicesIterator.hasNext()) {
      AbstractEvent eventOfInterest = (AbstractEvent) servicesIterator.next();
      this.removeEventOfInterest(eventOfInterest, _component);
    }
    Container container = super.getContainer();
    if (container != null) {
      container.removeAllEventsOfInterestOfComponent(_component);
    }
  }

  /**
   * Removes a specified event of interest to the specified component from this
   * container.
   * @param _eventOfInterest
   *          event to be removed.
   * @param _component
   *          component that is interested in the event to be removed.
   */
  void removeEventOfInterest(final AbstractEvent _eventOfInterest,
      final AbstractComponent _component) {
    Collection interestedComponents = (Collection) this
        .getEventsOfInterestMap().get(_eventOfInterest.getAlias());
    if (interestedComponents != null) {
      interestedComponents.remove(_component);
      this.getEventsOfInterestMap().put(_eventOfInterest.getAlias(),
          interestedComponents);
    }
  }

  /**
   * Announces an event to the ones interested in it. The announcement covers
   * all the hierarchy of components.
   * @param _announcer
   *          component that announces the event.
   * @param _eventAnnouncement
   *          announcement of the event.
   */
  protected void announceEvent(final AbstractComponent _announcer,
      final EventAnnouncement _eventAnnouncement) {
    Collection interestedComponentsCollection = (Collection) this
        .getEventsOfInterestMap().get(_eventAnnouncement.getEventAlias());
    if (interestedComponentsCollection != null) {
      for (Iterator iter = interestedComponentsCollection.iterator(); iter
          .hasNext();) {
        AbstractComponent interestedComponent = (AbstractComponent) iter.next();
        if ((!interestedComponent.equals(_announcer))
            || (_announcer instanceof FunctionalComponent)) {
          interestedComponent.receiveEvent(_eventAnnouncement);
        }
      }
    }
    Container container = super.getContainer();

    if (container != null) {
      container.announceEvent(this, _eventAnnouncement);
    }
  }

  /**
   * {@inheritDoc}
   */
  public Collection getProvidedServices() {
    Collection providedServices = new HashSet();
    for (Iterator iter = this.getChildComponents().iterator(); iter.hasNext();) {
      providedServices.addAll(((AbstractComponent) iter.next())
          .getProvidedServices());
    }
    return providedServices;
  }

  /**
   * {@inheritDoc}
   */
  public Collection getEventsOfInterest() {
    Collection eventsOfInterest = new HashSet();
    for (Iterator iter = this.getChildComponents().iterator(); iter.hasNext();) {
      eventsOfInterest.addAll(((AbstractComponent) iter.next())
          .getEventsOfInterest());
    }
    return eventsOfInterest;
  }

  /**
   * {@inheritDoc}
   */
  public boolean containsProvidedService(final String _providedServiceName) {
    return this.getProvidedServicesMap().containsKey(_providedServiceName);
  }

  /**
   * {@inheritDoc}
   */
  public boolean containsEventOfInterest(final String _interestingEventName) {
    return this.getEventsOfInterestMap().containsKey(_interestingEventName);
  }

  /**
   * {@inheritDoc}
   */
  public void start() {
    if (!this.isActive()) {
      this.startImpl();
    }
  }

  /**
   * {@inheritDoc}
   */
  public void stop() {
    if (this.isActive()) {
      this.stopImpl();
    }
  }

  /**
   * {@inheritDoc}
   */
  public final void startImpl() {
    for (Iterator iterator = this.getChildComponents().iterator(); iterator
        .hasNext();) {
      AbstractComponent component = (AbstractComponent) iterator.next();
      component.start();
    }
  }

  /**
   * {@inheritDoc}
   */
  public final void stopImpl() {
    Object[] comps = this.getChildComponents().toArray();
    for (int i = comps.length - 1; i >= 0; i--) {
      AbstractComponent component = (AbstractComponent) comps[i];
      component.stop();
    }
  }

  /**
   * {@inheritDoc}
   */
  protected void receiveEvent(final EventAnnouncement _eventAnouncement) {
    Collection interestedComponentsCollection = (Collection) this
        .getEventsOfInterestMap().get(_eventAnouncement.getEventAlias());
    AbstractComponent interestedComponent;

    for (Iterator iter = interestedComponentsCollection.iterator(); iter
        .hasNext();) {
      interestedComponent = (AbstractComponent) iter.next();
      interestedComponent.receiveEvent(_eventAnouncement);
    }
  }

  /**
   * {@inheritDoc}
   */
  protected ServiceResponse doIt(final ServiceRequest _serviceRequest)
      throws InvalidParametersException, IllegalAccessMethodException,
      ServiceNotImplementedException, ComponentNotStartedException {
    AbstractComponent component = (AbstractComponent) this
        .getProvidedServicesMap().get(_serviceRequest.getServiceAlias());
    if (component == null) {
      Container container = super.getContainer();
      if (container == null) {
        throw new ServiceNotImplementedException(_serviceRequest);
      } else {
        return container.doIt(_serviceRequest);
      }
    }
    return component.receiveRequest(_serviceRequest);
  }

  /**
   * Sub classes must implement this method to define the invocation of services
   * when the calling is going down the components' structure.
   * @param _serviceRequest
   *          request for a service.
   * @return The result of executing the requested service.
   * @throws InvalidParametersException
   *           if some parameter is invalid.
   * @throws IllegalAccessMethodException
   *           if there is an error while accessing the method which implements
   *           the service.
   * @throws ComponentNotStartedException
   *           if the component has not been started yet.
   */
  protected ServiceResponse receiveRequest(final ServiceRequest _serviceRequest)
      throws InvalidParametersException, IllegalAccessMethodException,
      ComponentNotStartedException {
    AbstractComponent component = (AbstractComponent) this
        .getProvidedServicesMap().get(_serviceRequest.getServiceAlias());

    return component.receiveRequest(_serviceRequest);
  }

  /**
   * {@inheritDoc}
   */
  protected final void updateServiceReference(final String _serviceAlias) {
    AbstractComponent currentProvider = (AbstractComponent) this
        .getProvidedServicesMap().remove(_serviceAlias);
    if (currentProvider != null) {
      currentProvider.updateServiceReference(_serviceAlias);
    }
  }

  /**
   * Update the alias reference of parent.
   * @param _oldAlias
   *          old alias.
   * @param _newAlias
   *          new alias.
   * @throws AliasAlreadyInUseException
   *           if the alias already in use.
   */
  protected void updateAliasReferenceOfParent(final String _oldAlias,
      final String _newAlias) throws AliasAlreadyInUseException {
    if (this.getProvidedServicesMap().containsKey(_newAlias)) {
      throw new AliasAlreadyInUseException(_newAlias, this.getName());
    }
    Container container = super.getContainer();
    if (container == null) {
      this.updateServiceAliasReference(_oldAlias, _newAlias);
    } else {
      container.updateAliasReferenceOfParent(_oldAlias, _newAlias);
    }
  }

  /**
   * {@inheritDoc}
   */
  protected void updateServiceAliasReference(final String _oldAlias,
      final String _newAlias) {
    AbstractComponent currentProvider = (AbstractComponent) this
        .getProvidedServicesMap().remove(_oldAlias);
    if (currentProvider != null) {
      this.getProvidedServicesMap().put(_newAlias, currentProvider);
      currentProvider.updateServiceAliasReference(_oldAlias, _newAlias);
    }
  }

  /**
   * {@inheritDoc}
   */
  protected void updateEventAliasReference(final String _oldAlias,
      final String _newAlias) {
    HashSet currentInterested = (HashSet) this.getEventsOfInterestMap().get(
        _oldAlias);
    this.getEventsOfInterestMap().remove(_oldAlias);
    this.getEventsOfInterestMap().put(_newAlias, currentInterested);
    if (this.getContainer() != null) {
      this.getContainer().updateEventAliasReference(_oldAlias, _newAlias);
    }
  }

  /**
   * {@inheritDoc}
   */
  public final boolean isActive() {
    for (Iterator iter = this.getChildComponents().iterator(); iter.hasNext();) {
      AbstractComponent child = (AbstractComponent) iter.next();
      if (!child.isActive()) {
        return false;
      }
    }
    return true;
  }
}
