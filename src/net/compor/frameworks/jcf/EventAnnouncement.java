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

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Represents the announcement of an event performed by some component.
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro </a>
 * @version 1.0
 */
public class EventAnnouncement implements Serializable {

  /**
   * Serial Version UID for this class.
   */
  private static final long serialVersionUID = 1L;

  /** Alias of the event. */
  private String eventAlias;

  /** Name of the event. */
  private String eventName;

  /** List of parameters of the event. */
  private List parameters;

  /**
   * Creates a new <code>EventAnounce</code> specifying the its name.
   * @param _eventName
   *          name of the announced event.
   */
  public EventAnnouncement(final String _eventName) {
    if (_eventName == null) {
      throw new EventNameException();
    }
    eventAlias = _eventName;
    eventName = _eventName;
    parameters = new Vector();
  }

  /**
   * Creates a new <code>EventAnounce</code> specifying the its name and list
   * of parameters.
   * @param _eventName
   *          name of the announced event.
   * @param _parameters _
   *          list of parameters of the announced event.
   */
  public EventAnnouncement(final String _eventName, final Object[] _parameters) {
    this(_eventName);
    parameters = Arrays.asList(_parameters);
  }

  /**
   * Returns the alias of the event.
   * @return String alias of the event.
   */
  public final String getEventAlias() {
    return eventAlias;
  }

  /**
   * Sets the alias of the event.
   * @param _alias
   *          alias of the event.
   */
  public final void setEventAlias(final String _alias) {
    eventAlias = _alias;
  }

  /**
   * Adds a new parameter to the announced event.
   * @param _parameter
   *          parameter to be added.
   */
  public final void addParameter(final Object _parameter) {
    parameters.add(_parameter);
  }

  /**
   * Returns an iterator of the list of parameters of the event.
   * @return Iterator iterator of the list of parameters.
   */
  public final Iterator parametersIterator() {
    return parameters.iterator();
  }

  /**
   * Returns an array of the list of parameters of the event.
   * @return Object[] parameters as array.
   */
  public final Object[] getParametersAsArray() {
    return parameters.toArray();
  }

  /**
   * Returns the types of the parameters of the event.
   * @return Object[] parameters type as array.
   */
  public final Object[] getParameterTypes() {
    Object[] types = new Object[parameters.size()];
    int index = 0;

    for (Iterator iter = this.parametersIterator(); iter.hasNext();) {
      types[index] = iter.next().getClass().getName();
      index++;
    }

    return types;
  }

  /**
   * Returns the event name.
   * @return String event name.
   */
  public String getEventName() {
    return eventName;
  }

  /**
   * Sets the event name.
   * @param _eventName
   *          event name.
   */
  public void setEventName(final String _eventName) {
    eventName = _eventName;
  }
}
