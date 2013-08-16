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

/**
 * Represents an identifyer of the service request.
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public class ServiceRequestId implements java.io.Serializable {

  /** Initial state of the counter of requisitions. */
  private static int requestcounter = 0;

  /** Identifier of the request. */
  private int id;

  /**
   * Creates a new <code>ServiceRequestId</code> with a automatically
   * generated ID.
   */
  public ServiceRequestId() {
    id = ServiceRequestId.generateId();
  }

  /**
   * Returns the id of the request.
   * @return int the id of the request.
   */
  public final int getId() {
    return id;
  }

  /**
   * Generates and returns the next identifier for a request.
   * @return int the next identifier for a request.
   */
  private static int generateId() {
    requestcounter = (requestcounter + 1) % Integer.MAX_VALUE;
    return requestcounter;
  }

  /**
   * {@inheritDoc}
   */
  public final boolean equals(final Object _obj) {
    if (_obj instanceof ServiceRequestId) {
      return (id == ((ServiceRequestId) _obj).getId());
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  public int hashCode() {
    return this.getId();
  }

  /**
   * {@inheritDoc}
   */
  public final String toString() {
    return String.valueOf(this.getId());
  }
}
