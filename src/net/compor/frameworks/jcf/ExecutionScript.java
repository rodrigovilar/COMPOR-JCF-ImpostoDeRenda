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
import java.util.HashMap;
import java.util.Map;

import net.compor.commons.util.AliasTable;

/**
 * The class that represents a initialization script according to <i><a
 * href="http://www.compor.net"> COMPOR CMS </a> </i>.
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @version 1.0
 */
public class ExecutionScript {

  /** The root container of an application. */
  private ScriptContainer scriptContainer;

  /** Indicates if the execution script is active or not. */
  private boolean active;

  /** The manager asyncronous of this execution script. */
  private ExecutionScriptAsyncManager asyncManager;

  /** The alias table of required services. */
  private AliasTable aliasTableProvidedServices;

  /** The alias table of events of interest. */
  private AliasTable aliasTableEventsOfInterest;

  /**
   * Creates a new <code>ComponentScript</code> with the specified root
   * container.
   * @param _rootContainer
   *          root container of the application.
   */
  public ExecutionScript(final ScriptContainer _rootContainer) {
    scriptContainer = _rootContainer;
    scriptContainer.setExecutionScript(this);
    asyncManager = new ExecutionScriptAsyncManager(this);
    aliasTableEventsOfInterest = new AliasTable();
    aliasTableProvidedServices = new AliasTable();
  }

  /**
   * Gets the alias table of events of interest.
   * @return AliasTable alias table.
   */
  public AliasTable getAliasTableEventsOfInterest() {
    return aliasTableEventsOfInterest;
  }

  /**
   * Gets the alias table of provided services.
   * @return AliasTable alias table.
   */
  public AliasTable getAliasTableProvidedServices() {
    return aliasTableProvidedServices;
  }

  /**
   * Add the new event of interest to alias table.
   * @param _eventOfInterest
   *          new event of interest.
   */
  public void addEventOfInterest(final EventOfInterest _eventOfInterest) {
    this.getAliasTableEventsOfInterest().put(_eventOfInterest);
  }

  /**
   * Add the new provided service to alias table.
   * @param _providedService
   *          new provided service.
   */
  public void addProvidedService(final ProvidedService _providedService) {
    this.getAliasTableProvidedServices().put(_providedService);
  }

  /**
   * Remove the event that contains the alias in the alias table.
   * @param _alias
   *          Alias of event.
   */
  public void removeEventOfInterest(final String _alias) {
    this.getAliasTableEventsOfInterest().remove(_alias);
  }

  /**
   * Remove the provided service that contains the alias in the alias table.
   * @param _alias
   *          Alias of provided service.
   */
  public void removeProvidedService(final String _alias) {
    this.getAliasTableProvidedServices().remove(_alias);
  }

  /**
   * Executes the service specified in the parameter.
   * @param _request
   *          request containing the service to be executed.
   * @return ServiceResponse.
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
  public final ServiceResponse exec(final ServiceRequest _request)
      throws IllegalAccessMethodException, InvalidParametersException,
      ServiceNotImplementedException, ComponentNotStartedException {
    String alias = this.getAliasTableProvidedServices().getAlias(
        _request.getServiceName());
    if (alias != null) {
      _request.setServiceAlias(alias);
    }
    return scriptContainer.doIt(_request);
  }

  /**
   * Executes the service specified in the parameter in an asynchronous way.
   * @param _serviceRequest
   *          request containing the service to be executed.
   * @return ServiceRequestId Request identification.
   */
  public final ServiceRequestId execAsync(final ServiceRequest _serviceRequest) {
    new ComponentAsyncService(asyncManager, _serviceRequest).invoke();
    return _serviceRequest.getId();
  }

  /**
   * Initializes the execution script.
   */
  public final void init() {
    if (!active) {
      scriptContainer.start();
      active = true;
      this.main();
    }
  }

  /**
   * Finishes the execution of the script.
   */
  public final void finish() {
    if (active) {
      this.scriptContainer.stop();
      active = false;
    }
  }

  /**
   * This method is invoked when <code>ExecutionScript</code> starts running.
   * Subclasses must override this method to implement specific functionalities.
   */
  protected void main() {
  }

  /**
   * Returns the script container.
   * @return ScriptContainer script container
   */
  public ScriptContainer getScriptContainer() {
    return scriptContainer;
  }

  /**
   * Receives the response of the asyncronous service. Subclasses must implement
   * this method to receive asynchronous service response.
   * @param _response
   *          response of asynchronous calling of service.
   */
  protected void receiveAsyncServiceResponse(final ServiceResponse _response) {
  }

  /**
   * Receives an event announced by some component.
   * @param _eventAnnounce
   *          event that has been announced.
   */
  protected final void receiveEvent(final EventAnnouncement _eventAnnounce) {
    if (this.isActive()) {
      EventOfInterest interestingEvent = (EventOfInterest) this
          .getAliasTableEventsOfInterest().getValueByAlias(
              _eventAnnounce.getEventAlias());
      if (interestingEvent != null) {
        Method method = interestingEvent.getMethod();
        if (method != null) {
          try {
            method.invoke(this, _eventAnnounce.getParametersAsArray());
          } catch (IllegalArgumentException iae) {
            cannotReceiveEvent(_eventAnnounce, iae);
          } catch (IllegalAccessException iae) {
            cannotReceiveEvent(_eventAnnounce, iae);
          } catch (InvocationTargetException ite) {
            // TODO: Discuss about that.
          }
        }
      }
    }

  }

  /**
   * Default method to exception.
   * @param _announce
   *          Event announcement.
   * @param _exception
   *          Exception.
   */
  protected void cannotReceiveEvent(final EventAnnouncement _announce,
      final Exception _exception) {
  }

  /**
   * Indicates if the execution script is running.
   * @return boolean true if this <code>ExecutionScript</code> is active,
   *         false on the contraty.
   */
  public final boolean isActive() {
    return active;
  }

  /**
   * Announces an event to the ones interested on it. The announce covers all
   * the hierarchy of the components.
   * @param _eventAnnounce
   *          announce of the event.
   */
  public final void announceEvent(final EventAnnouncement _eventAnnounce) {
    new ComponentAsyncEvent(asyncManager, _eventAnnounce).invoke();
  }

}
