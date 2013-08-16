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

import java.util.List;

/**
 * Represents an event specification. All events {@link AbstractEvent} are
 * associated to an event specification.
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @version 1.0
 */
public class EventSpecification {

  /** Name of the event represented by this specification. */
  private String eventName;

  /** Description of the event represented by this specification. */
  private String eventDescription;

  /** List of parameters for the event represented by this specification. */
  private List eventParameters;

  /**
   * Creates a new <code>EventSpecification</code> with the specified name,
   * description, and parameters.
   * @param _eventName
   *          name of the event represented by this specification.
   * @param _eventDescription
   *          description of the event represented by this specification.
   * @param _eventParameters
   *          list of parameters for the event represented by this
   *          specification.
   */
  public EventSpecification(final String _eventName,
      final String _eventDescription, final List _eventParameters) {
    if (_eventName == null) {
      throw new EventNameException();
    }
    eventName = _eventName;
    eventDescription = _eventDescription;
    eventParameters = _eventParameters;
  }

  /**
   * Creates a new <code>EventSpecification</code> with the specified name.
   * @param _eventName
   *          name of the event represented by this specification.
   */
  public EventSpecification(final String _eventName) {
    this(_eventName, null, null);
  }

  /**
   * Returns the name of the event represented by this specification.
   * @return String event name.
   */
  public final String getEventName() {
    return eventName;
  }

  /**
   * Returns the description of the event represented by this specification.
   * @return String description of the event represented by this specification.
   */
  public final String getEventDescription() {
    return eventDescription;
  }

  /**
   * Returns the list of parameters for the event represented by this
   * specification.
   * @return List list of parameters for the event represented by this
   *         specification.
   */
  public final List getEventParameters() {
    return eventParameters;
  }

}
