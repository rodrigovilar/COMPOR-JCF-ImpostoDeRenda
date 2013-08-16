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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.compor.frameworks.jcf.ServiceRequest;
import net.compor.frameworks.jcf.ServiceResponse;

/**
 * Represents an concern switch for the JCF.
 * @author <a href="mailto:mario@compor.net">Mario Hozano</a>
 * @version 1.0
 */
public class ConcernSwitch {

  /** The concerns. */
  private Map concerns;

  /**
   * Constructor.
   */
  public ConcernSwitch() {
    this.concerns = new HashMap();
  }

  /**
   * Activates the concern specified at the parameter.
   * @param _concern
   *          Concern.
   */
  public void activate(final Concern _concern) {
    this.concerns.put(_concern, Boolean.TRUE);
  }

  /**
   * Deactivates the concern specified at the parameter.
   * @param _concern
   *          Concern.
   */
  public void deactivate(final Concern _concern) {
    this.concerns.put(_concern, Boolean.FALSE);
  }

  /**
   * Adds the concern to the Concern Switch.
   * @param _concern
   *          Concern.
   * @param _state
   *          Concern state.
   */
  public void addConcern(final Concern _concern, final Boolean _state) {
    this.concerns.put(_concern, _state);
  }

  /**
   * Gets the concerns.
   * @return concerns.
   */
  public Map getConcerns() {
    return this.concerns;
  }

  /**
   * Executes the after implementation of the enabled concerns.
   * @param _component component.
   * @param _request request.
   * @param _response response.
   */
  public void executeAfter(final CustomizableComponent _component,
      final ServiceRequest _request, final ServiceResponse _response) {
    Set enabledConcerns = this.getEnabledConcerns();
    for (Iterator iter = enabledConcerns.iterator(); iter.hasNext();) {
      Concern concern = (Concern) iter.next();
      concern.interceptAfter(_component, _request, _response);
    }
  }

  /**
   * Executes the before implementation of the enabled concerns.
   * @param _component component.
   * @param _request request.
   * @param _response response.
   */
  public void executeBefore(final CustomizableComponent _component,
      final ServiceRequest _request) {
    Set enabledConcerns = this.getEnabledConcerns();
    for (Iterator iter = enabledConcerns.iterator(); iter.hasNext();) {
      Concern concern = (Concern) iter.next();
      concern.interceptBefore(_component, _request);
    }
  }

  /**
   * Executes the exception implementation of the enabled concerns.
   * @param _component component.
   * @param _request request.
   * @param _response response.
   */
  public void executeOnException(final CustomizableComponent _component,
      final ServiceRequest _request, final ServiceResponse _response) {
    Set enabledConcerns = this.getEnabledConcerns();
    for (Iterator iter = enabledConcerns.iterator(); iter.hasNext();) {
      Concern concern = (Concern) iter.next();
      concern.interceptOnException(_component, _request, _response);
    }
  }

  /**
   * Gets the enabled concerns.
   * @return The enabled concerns.
   */
  private Set getEnabledConcerns() {
    Set enabledConcerns = new HashSet();
    for (Iterator iter = enabledConcerns.iterator(); iter.hasNext();) {
      Concern concern = (Concern) iter.next();
      if (Boolean.getBoolean(this.concerns.get(concern).toString())) {
        enabledConcerns.add(concern);
      }
    }
    return enabledConcerns;
  }

  /**
   * Removes the concern.
   * @param _concern concern.
   */
  public void removeConcern(final Concern _concern) {
    this.concerns.remove(_concern);
  }

}
