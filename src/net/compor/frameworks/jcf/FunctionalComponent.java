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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.compor.commons.util.AliasIF;
import net.compor.commons.util.AliasTable;

/**
 * Represents a componoent according to <i><a href="http://www.compor.net">
 * COMPOR CMS </a> </i>.
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author <a href="mailto:loreno@compor.net">Loreno Oliveira</a>
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public abstract class FunctionalComponent extends AbstractComponent {

  /**
   * Table of services provided by this functional component. (service alias,
   * {@link net.compor.frameworks.jcf.ProvidedService})
   */
  private AliasTable aliasTableProvidedServices;

  /**
   * Table of services required for this functional component. (service alias,
   * {@link net.compor.frameworks.jcf.RequiredService})
   */
  private AliasTable aliasTableRequiredServices;

  /**
   * Table of events of interest to this functional component. (event alias,
   * {@link net.compor.frameworks.jcf.EventOfInterest})
   */
  private AliasTable aliasTableEventsOfInterest;

  /**
   * Table of events announced by this functional component. (event alias,
   * {@link net.compor.frameworks.jcf.AnnouncedEvent})
   */
  private AliasTable aliasTableAnnouncedEvents;

  /**
   * Table of initialization properties (property name, property value).
   */
  private Map initializationProperties;

  /** Indicates if this functional component is active. */
  private boolean active;

  /**
   * Manager of asynchronous operations for this functional component.
   */
  private FunctionalComponentAsyncManager asyncManager;

  /**
   * Creates a new <code>FunctionalComponent</code> with the specified name.
   * @param _name
   *          component name.
   */
  public FunctionalComponent(final String _name) {
    super(_name);
    initializationProperties = new HashMap();
    aliasTableProvidedServices = new AliasTable();
    aliasTableRequiredServices = new AliasTable();
    aliasTableAnnouncedEvents = new AliasTable();
    aliasTableEventsOfInterest = new AliasTable();
    asyncManager = new FunctionalComponentAsyncManager(this);
  }

  /**
   * Returns the alias table of provided service.
   * @return AliasTable alias table of provided service.
   */
  private AliasTable getAliasTableProvidedServices() {
    return aliasTableProvidedServices;
  }

  /**
   * Returns the alias table of events of interest.
   * @return AliasTable alias table of events of interest.
   */
  private AliasTable getAliasTableEventsOfInterest() {
    return aliasTableEventsOfInterest;
  }

  /**
   * Returns the alias table of required services.
   * @return AliasTable alias table of required services.
   */
  private AliasTable getAliasTableRequiredServices() {
    return aliasTableRequiredServices;
  }

  /**
   * Returns the alias table of announced events.
   * @return AliasTable alias table of announced events
   */
  private AliasTable getAliasTableAnnouncedEvents() {
    return aliasTableAnnouncedEvents;
  }

  /**
   * Returns the asyncronous manager.
   * @return FunctionalComponentAsyncManager asyncronous manager.
   */
  private FunctionalComponentAsyncManager getAsyncManager() {
    return asyncManager;
  }

  /**
   * Returns the current value of the specified initialization property.
   * @param _propertyName
   *          name of the initialization property.
   * @return Object current value of the specified initialization property.
   */
  public final Object getInitializationPropertyValue(final String _propertyName) {
    return this.getInitializationProperties().get(_propertyName);
  }

  /**
   * Defines a new initialization property with the specified value.
   * @param _propertyName
   *          name of the new initialization property.
   * @param _propertyValue
   *          new value for the specified initialization property.
   */
  protected final void putInitializationPropertyValue(
      final String _propertyName, final Object _propertyValue) {
    this.getInitializationProperties().put(_propertyName, _propertyValue);
  }

  /**
   * Sets the value of an existing initialization property. Before setting the
   * property value, the property must have been put in this functional
   * component through the method
   * {@link FunctionalComponent#putInitializationPropertyValue(String, Object)}
   * @param _propertyName
   *          name of the property to be set.
   * @param _propertyValue
   *          value of the property.
   * @throws InvalidInitializationPropertyException
   *           if such property has not been put in this functional component
   *           yet.
   */
  public final void setInitializationPropertyValue(final String _propertyName,
      final Object _propertyValue)
      throws InvalidInitializationPropertyException {
    Object value = this.getInitializationProperties().get(_propertyName);

    if (value != null) {
      this.getInitializationProperties().put(_propertyName, _propertyValue);
    } else {
      throw new InvalidInitializationPropertyException(_propertyName,
          _propertyValue);
    }
  }

  /**
   * Returns the table of initialization properties of this functional
   * component.
   * @return Map table of initialization properties.
   */
  public final Map getInitializationProperties() {
    return initializationProperties;
  }

  /**
   * Adds a provided service to this functional component.
   * @param _providedService
   *          {@link net.compor.frameworks.jcf.ProvidedService} to be added.
   */
  protected final void addProvidedService(final ProvidedService _providedService) {
    this.getAliasTableProvidedServices().put(_providedService);
  }

  /**
   * Returns the alias for the specified provided service of this component.
   * @param _providedServiceName
   *          name of the provided service.
   * @return String alias for the provided service.
   */
  public final String getAliasOfProvidedService(
      final String _providedServiceName) {
    return this.getAliasTableProvidedServices().getAlias(_providedServiceName);
  }

  /**
   * Sets the alias for the specified provided service.
   * @param _providedServiceName
   *          name of the provided service.
   * @param _providedServiceAlias
   *          alias of the provided service.
   * @throws AliasAlreadyInUseException
   *           if the alias already in use.
   * @throws AliasNullException
   *           if the alias is null.
   */
  public final void setAliasOfProvidedService(
      final String _providedServiceName, final String _providedServiceAlias)
      throws AliasAlreadyInUseException, AliasNullException {
    if (_providedServiceAlias == null) {
      throw new AliasNullException(_providedServiceName);
    }
    String oldAlias = this.getAliasTableProvidedServices().getAlias(
        _providedServiceName);
    if (this.getContainer() != null) {
      this.getContainer().updateAliasReferenceOfParent(oldAlias,
          _providedServiceAlias);
    }
    this.getAliasTableProvidedServices().changeAlias(_providedServiceName,
        _providedServiceAlias);
  }

  /**
   * Returns the alias for the specified required service of this component.
   * @param _requiredServiceName
   *          name of the required service.
   * @return String alias for the required service.
   */
  public final String getAliasOfRequiredService(
      final String _requiredServiceName) {
    return this.getAliasTableRequiredServices().getAlias(_requiredServiceName);
  }

  /**
   * Sets the alias for the specified required service.
   * @param _requiredServiceName
   *          name of the required service.
   * @param _requiredServiceAlias
   *          alias for the required service.
   */
  public final void setAliasOfRequiredService(
      final String _requiredServiceName, final String _requiredServiceAlias) {
    AliasIF alias = this.getAliasTableRequiredServices().getValueByName(
        _requiredServiceName);
    String oldAlias = alias.getAlias();
    this.getAliasTableRequiredServices().changeAlias(_requiredServiceName,
        _requiredServiceAlias);
    for (Iterator iter = getProvidedServices().iterator(); iter.hasNext();) {
      ProvidedService current = (ProvidedService) iter.next();
      current.updateRequiredServiceAlias(oldAlias, _requiredServiceAlias);
    }
  }

  /**
   * Returns the services required for this functional component.
   * @return {@link java.util.Collection} Services required for this functional
   *         component.
   */
  public final Collection getRequiredServices() {
    return this.getAliasTableRequiredServices().getValues();
  }

  /**
   * Returns the service required for this component with the specified name.
   * @param _requiredServiceName
   *          service name.
   * @return {@link net.compor.frameworks.jcf.RequiredService} Service required
   *         for this component.
   */
  public final RequiredService getRequiredService(
      final String _requiredServiceName) {
    return (RequiredService) this.getAliasTableRequiredServices()
        .getValueByName(_requiredServiceName);
  }

  /**
   * Adds a service required for this component.
   * @param _requiredService
   *          {@link net.compor.frameworks.jcf.RequiredService} to be added.
   */
  protected final void addRequiredService(final RequiredService _requiredService) {
    this.getAliasTableRequiredServices().put(_requiredService);
  }

  /**
   * Adds an event of interest to this functional component.
   * @param _eventOfInterest
   *          {@link net.compor.frameworks.jcf.EventOfInterest} to be added.
   */
  protected final void addEventOfInterest(final EventOfInterest _eventOfInterest) {
    this.getAliasTableEventsOfInterest().put(_eventOfInterest);
  }

  /**
   * Returns the table of events announced by this component.
   * @return {@link AliasTable} table of events announced by this component.
   */
  public final Collection getAnnouncedEvents() {
    return this.getAliasTableAnnouncedEvents().getValues();
  }

  /**
   * Returns the alias for the specified interest event of this component.
   * @param _eventOfInterestName
   *          name of the interest event.
   * @return String alias for the event.
   */
  public final String getAliasOfEventOfInterest(final String _eventOfInterestName) {
    return this.getAliasTableEventsOfInterest().getAlias(_eventOfInterestName);
  }

  /**
   * Sets the alias for the specified interest event.
   * @param _eventOfInterestName
   *          name of the interest event.
   * @param _eventOfInterestAlias
   *          alias of the interest event.
   */
  public final void setAliasOfEventOfInterest(final String _eventOfInterestName,
      final String _eventOfInterestAlias) {
    String oldAlias = this.getAliasTableEventsOfInterest().getAlias(
        _eventOfInterestName);
    this.getAliasTableEventsOfInterest().changeAlias(_eventOfInterestName,
        _eventOfInterestAlias);
    this.updateEventAliasReference(oldAlias, _eventOfInterestAlias);
  }

  /**
   * {@inheritDoc}
   */
  protected void updateEventAliasReference(final String _oldAlias,
      final String _newAlias) {
    if (this.getContainer() != null) {
      this.getContainer().updateEventAliasReference(_oldAlias, _newAlias);
    }
  }

  /**
   * Adds an announced event to this functional component.
   * @param _announcedEvent
   *          {@link net.compor.frameworks.jcf.AnnouncedEvent} to be added.
   */
  protected final void addAnnouncedEvent(final AnnouncedEvent _announcedEvent) {
    this.getAliasTableAnnouncedEvents().put(_announcedEvent);
  }

  /**
   * Returns the alias for the specified event announced by this functional
   * component.
   * @param _announcedEventName
   *          name of the announced event.
   * @return String alias for the event.
   */
  public final String getAliasOfAnnouncedEvent(final String _announcedEventName) {
    return this.getAliasTableAnnouncedEvents().getAlias(_announcedEventName);
  }

  /**
   * Sets the alias for the specified announced event.
   * @param _announcedEventName
   *          name of the announced event.
   * @param _announcedEventAlias
   *          alias of the announced event.
   */
  public final void setAliasOfAnnouncedEvent(final String _announcedEventName,
      final String _announcedEventAlias) {
    this.getAliasTableAnnouncedEvents().changeAlias(_announcedEventName,
        _announcedEventAlias);
  }

  /**
   * Announces the specifyed event.
   * @param _eventAnouncement
   *          announcement of the event.
   * @throws AnnouncedEventNotDeclaredException
   *           announced event has not been declared.
   */
  protected final void announceEvent(final EventAnnouncement _eventAnouncement)
      throws AnnouncedEventNotDeclaredException {
    String eventAlias = this.getAliasTableAnnouncedEvents().getAlias(
        _eventAnouncement.getEventName());
    if (eventAlias != null) {
      _eventAnouncement.setEventAlias(eventAlias);
      new ComponentAsyncEvent(this.getAsyncManager(), _eventAnouncement)
          .invoke();
    } else {
      throw new AnnouncedEventNotDeclaredException(_eventAnouncement
          .getEventName(), this);
    }
  }

  /**
   * Forwards a service request to the parent container of the component.
   * @param _serviceRequest
   *          request of the container.
   * @return {@link ServiceResponse} response of the service.
   * @throws InvalidParametersException
   *           if the parameters are invalid.
   * @throws IllegalAccessMethodException
   *           if there is no access to the service.
   * @throws ServiceNotImplementedException
   *           if the service is not implemented by any component of the
   *           hierarchy.
   * @throws ComponentNotStartedException
   *           if the component has not been started yet.
   */
  private ServiceResponse invokeContainer(final ServiceRequest _serviceRequest)
      throws InvalidParametersException, IllegalAccessMethodException,
      ServiceNotImplementedException, ComponentNotStartedException {
    Container container = super.getContainer();
    if (container == null) {
      throw new ServiceNotImplementedException(_serviceRequest);
    } else {
      return container.doIt(_serviceRequest);
    }
  }

  /**
   * Invokes the method specifyed in the service request and returns the
   * response of the invocation.
   * @param _serviceRequest
   *          request of the service.
   * @param _method
   *          method which implements the service.
   * @return {@link ServiceResponse} response of the invocation of the service.
   * @throws IllegalArgumentException
   *           if the parameters are incorrect.
   * @throws IllegalAccessException
   *           if there is no access to the method.
   */
  private ServiceResponse invokeMethod(final ServiceRequest _serviceRequest,
      final Method _method) throws IllegalArgumentException,
      IllegalAccessException {
    ServiceResponse serviceResponse = new ServiceResponse(_serviceRequest
        .getId());
    try {
      Object[] parameters = _serviceRequest.getParametersAsArray();
      Object result = _method.invoke(this, parameters);
      serviceResponse.setData(result);
    } catch (InvocationTargetException ite) {
      serviceResponse.setException(ite.getTargetException());
    }

    return serviceResponse;
  }

  /**
   * Invokes a service request asyncronous.
   * @param _serviceRequest
   *          service request.
   * @return ServiceRequestId service request identification.
   */
  protected final ServiceRequestId doItAsync(
      final ServiceRequest _serviceRequest) {
    new ComponentAsyncService(this.getAsyncManager(), _serviceRequest).invoke();
    return _serviceRequest.getId();
  }

  /**
   * {@inheritDoc}
   */
  public final boolean containsProvidedService(final String _serviceAlias) {
    return this.getAliasTableProvidedServices().contains(_serviceAlias);
  }

  /**
   * {@inheritDoc}
   */
  public final boolean containsEventOfInterest(final String _eventAlias) {
    return this.getAliasTableEventsOfInterest().contains(_eventAlias);
  }

  /**
   * {@inheritDoc}
   */
  public final Collection getProvidedServices() {
    return this.getAliasTableProvidedServices().getValues();
  }

  /**
   * {@inheritDoc}
   */
  public final Collection getEventsOfInterest() {
    return this.getAliasTableEventsOfInterest().getValues();
  }

  /**
   * {@inheritDoc}
   */
  protected final void receiveEvent(final EventAnnouncement _eventAnounce) {
    if (this.isActive()) {
      EventOfInterest interestingEvent = (EventOfInterest) this
          .getAliasTableEventsOfInterest().getValueByAlias(
              _eventAnounce.getEventAlias());
      Method method = interestingEvent.getMethod();
      if (method != null) {
        try {
          method.invoke(this, _eventAnounce.getParametersAsArray());
        } catch (IllegalArgumentException iae) {
          this.cannotReceiveEvent(_eventAnounce, iae);
        } catch (IllegalAccessException iae) {
          this.cannotReceiveEvent(_eventAnounce, iae);
        } catch (InvocationTargetException ite) {
          // TODO: Discuss about that.
        }
      }
    } else {
      this.cannotReceiveEvent(_eventAnounce, new ComponentNotStartedException(
          this.getName()));
    }
  }

  /**
   * {@inheritDoc}
   */
  protected final ServiceResponse doIt(final ServiceRequest _serviceRequest)
      throws InvalidParametersException, IllegalAccessMethodException,
      ServiceNotImplementedException, ComponentNotStartedException {
    String serviceAlias = this.getAliasTableRequiredServices().getAlias(
        _serviceRequest.getServiceName());
    if (serviceAlias != null) {
      _serviceRequest.setServiceAlias(serviceAlias);
      return this.invokeContainer(_serviceRequest);
    }
    throw new RequiredServiceNotDeclaredException(_serviceRequest
        .getServiceName(), this);
  }

  /**
   * {@inheritDoc}
   */
  protected ServiceResponse receiveRequest(
      final ServiceRequest _serviceRequest) throws InvalidParametersException,
      IllegalAccessMethodException, ComponentNotStartedException {
    if (this.isActive()) {
      ProvidedService providedService = (ProvidedService) this
          .getAliasTableProvidedServices().getValueByAlias(
              _serviceRequest.getServiceAlias());
      Method method = providedService.getMethod();
      try {
        return this.invokeMethod(_serviceRequest, method);
      } catch (IllegalArgumentException iae) {
        throw new InvalidParametersException(_serviceRequest, method, iae);
      } catch (IllegalAccessException iae) {
        throw new IllegalAccessMethodException(method, iae);
      }
    } else {
      throw new ComponentNotStartedException(this.getName());
    }
  }

  /**
   * {@inheritDoc}
   */
  public void startImpl() {
  }

  /**
   * {@inheritDoc}
   */
  public void stopImpl() {
  }

  /**
   * This method is invoked when the specifyed event cannot be delivered due to
   * some exception. It does not include exceptions throw in the target method
   * of the interested component. This method must be subscribed to register
   * problems about events that have not been delivered.
   * @param _eventAnounce
   *          event that could not be delivered.
   * @param _exception
   *          exception thrown.
   */
  protected void cannotReceiveEvent(final EventAnnouncement _eventAnounce,
      final Throwable _exception) {

  }

  /**
   * This method is invoked when receiving an asynchronous service response. It
   * must be implemented to receive responses for asynchronous service
   * invocations.
   * @param _response
   *          response of the service.
   */
  protected void receiveAsyncServiceResponse(final ServiceResponse _response) {
  }

  /**
   * {@inheritDoc}
   */
  public final boolean isActive() {
    return active;
  }

  /**
   * {@inheritDoc}
   */
  public final void start() {
    if (!this.isActive()) {
      this.startImpl();
      active = true;
    }
  }

  /**
   * {@inheritDoc}
   */
  public final void stop() {
    if (this.isActive()) {
      this.stopImpl();
      active = true;
    }
  }

}
