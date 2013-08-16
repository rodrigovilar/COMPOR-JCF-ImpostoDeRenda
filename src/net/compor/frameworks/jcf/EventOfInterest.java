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

import java.lang.reflect.Method;

/**
 * Represents an event of interest to a functional component. The event is
 * associated to a {@link java.lang.reflect.Method} to be invoked when the event
 * is received by any component.
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @version 1.0
 */
public class EventOfInterest extends AbstractEvent {

  /**
   * {@link java.lang.reflect.Method} to be invoked when the event has been
   * received.
   */
  private Method method;

  /**
   * Creates a new <code>EventOfInterest</code> with the specified
   * specification. The {@link java.lang.reflect.Method} to be invoked when the
   * event has been received is defined too.
   * @param _specification
   *          specification for the event of interest.
   * @param _method
   *          {@link java.lang.reflect.Method} to be invoked when the event is
   *          received.
   */
  public EventOfInterest(final EventSpecification _specification,
      final Method _method) {
    super(_specification);
    if ((!_method.getReturnType().equals(void.class))
        || (_method.getExceptionTypes().length > 0)) {
      throw new InvalidEventMethodException(_method);
    }
    method = _method;
  }

  /**
   * Returns the instance of {@link java.lang.reflect.Method} to be invoked when
   * the event is received.
   * @return Method {@link java.lang.reflect.Method} to be invoked when the
   *         event is received.
   */
  public final Method getMethod() {
    return method;
  }
}
