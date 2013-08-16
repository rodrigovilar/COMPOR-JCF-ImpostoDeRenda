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

import java.util.Iterator;

/**
 * An special container intended to be used as the root container of a COMPOR
 * based application.
 * @author <a href="mailto:emerson@compor">Emerson Loureiro</a>
 * @author <a href="mailto:hyggo@compor">Hyggo Almeida</a>
 * @version 1.0
 */
public class ScriptContainer extends Container {

  /** The component script related to this script container. */
  private ExecutionScript componentScript;

  /**
   * Creates a new <code>ScriptContainer</code> with the specified name.
   * @param _name
   *          name of the script container.
   */
  public ScriptContainer(final String _name) {
    super(_name);
  }

  /**
   * Defines the execution script for this script container.
   * @param _componentScript
   *          execution script to be defined.
   */
  public final void setExecutionScript(final ExecutionScript _componentScript) {
    componentScript = _componentScript;
  }

  /**
   * Returns the current execution script for this script container.
   * @return ExecutionScript execution script for this script container.
   */
  public final ExecutionScript getExecutionScript() {
    return componentScript;
  }

  /**
   * {@inheritDoc}
   */
  void addAllEventsOfInterestOfComponent(final AbstractComponent _component) {
    for (Iterator iter = _component.getEventsOfInterest().iterator(); iter
        .hasNext();) {
      EventOfInterest event = (EventOfInterest) iter.next();
    }
    super.addAllEventsOfInterestOfComponent(_component);
  }

  /**
   * {@inheritDoc}
   */
  protected void updateEventAliasReference(final String _oldAlias,
      final String _newAlias) {

    this.getExecutionScript().getAliasTableEventsOfInterest().changeAlias(
        _oldAlias, _newAlias);
    super.updateEventAliasReference(_oldAlias, _newAlias);
  }

  /**
   * {@inheritDoc} Update the map of required services of the execution script.
   */
  void addAllProvidedServicesOfComponent(final AbstractComponent _component) {
    for (Iterator iter = _component.getProvidedServices().iterator(); iter
        .hasNext();) {
      ProvidedService providedService = (ProvidedService) iter.next();
      this.getExecutionScript().getAliasTableProvidedServices().put(
          providedService);
    }
    super.addAllProvidedServicesOfComponent(_component);
  }

  /**
   * {@inheritDoc} Update the map of required services of the execution script.
   */
  protected void updateAliasReferenceOfParent(final String _oldAlias,
      final String _newAlias) throws AliasAlreadyInUseException {
    this.getExecutionScript().getAliasTableProvidedServices().changeAlias(
        _oldAlias, _newAlias);
    super.updateAliasReferenceOfParent(_oldAlias, _newAlias);
  }

  /**
   * {@inheritDoc}
   */
  protected final void announceEvent(final AbstractComponent _announcer,
      final EventAnnouncement _eventAnnounce) {
    if (componentScript != null) {
      componentScript.receiveEvent(_eventAnnounce);
    }
    super.announceEvent(_announcer, _eventAnnounce);
  }
}
