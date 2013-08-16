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

package net.compor.frameworks.jcf.soc;

import net.compor.frameworks.jcf.ServiceRequest;
import net.compor.frameworks.jcf.ServiceResponse;

/**
 * Represents an concern for the JCF.
 * @author <a href="mailto:mario@compor.net">Mario Hozano</a>
 * @version 1.0
 */
public class Concern {

  /** Concern name. */
  private String concernName;

  /**
   * Construtor.
   * @param _concernName
   *          Concern name.
   */
  public Concern(final String _concernName) {
    this.setConcernName(_concernName);
  }

  /**
   * Gets the concern name.
   * @return concernName
   */
  public String getConcernName() {
    return this.concernName;
  }

  /**
   * Intercepts the method call before its execution.
   * @param _component
   *          Component.
   * @param _request
   *          Service request.
   */
  public void interceptBefore(final CustomizableComponent _component,
      final ServiceRequest _request) {
  }

  /**
   * Intercepts the method call after its execution.
   * @param _component
   *          Component.
   * @param _request
   *          Service request.
   * @param _response
   *          Service response.
   */
  public void interceptAfter(final CustomizableComponent _component,
      final ServiceRequest _request, final ServiceResponse _response) {
  }

  /**
   * Intercepts the method call on exception.
   * @param _component
   *          Component.
   * @param _request
   *          Service request.
   * @param _response
   *          Service response.
   */
  public void interceptOnException(final CustomizableComponent _component,
      final ServiceRequest _request, final ServiceResponse _response) {
  }

  /**
   * Sets the concern name.
   * @param _concernName
   *          Concern name.
   */
  public void setConcernName(final String _concernName) {
    this.concernName = _concernName;
  }

  /**
   * {@inheritDoc}
   */
  public boolean equals(Object obj) {
    return this.concernName.equals(obj);
  }

  /**
   * {@inheritDoc}
   */
  public int hashCode() {
    return this.concernName.hashCode();
  }

}
