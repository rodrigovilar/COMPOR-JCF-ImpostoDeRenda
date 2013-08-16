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

import net.compor.commons.util.AliasIF;

/**
 * Represents an abstract event in the context of JCF. The interest (
 * {@link EventOfInterest}) and announced ({@link AnnouncedEvent}) events are
 * subclasses of this class.
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @version 1.0
 */
public abstract class AbstractEvent implements AliasIF {

  /** Specification for this event. */
  private EventSpecification specification;

  /** Event alias defined by the developer. */
  private String alias;

  /**
   * Creates a new <code>AbstractEvent</code> with the specified event
   * specification.
   * @param _specification
   *          specification for the event.
   */
  public AbstractEvent(final EventSpecification _specification) {
    specification = _specification;
    alias = specification.getEventName();
  }

  /**
   * Returns the name of this event.
   * @return String event name.
   */
  public final String getName() {
    return specification.getEventName();
  }

  /**
   * Returns the specification of this event.
   * @return {@link EventSpecification} event specification.
   */
  public final EventSpecification getSpecification() {
    return specification;
  }

  /**
   * Returns the alias defined by the developer for this event.
   * @return String event alias.
   */
  public final String getAlias() {
    return alias;
  }

  /**
   * Sets the alias defined for this event.
   * @param _alias
   *          alias defined by the developer for this event.
   */
  public final void setAlias(final String _alias) {
    alias = _alias;
  }

  /**
   * {@inheritDoc}
   */
  public boolean equals(final Object _obj) {
    if (_obj instanceof AbstractEvent) {
      return ((AbstractEvent) _obj).getAlias().equals(this.getAlias());
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  public int hashCode() {
    return this.getAlias().hashCode();
  }
}
