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

import net.compor.frameworks.jcf.ComponentNotStartedException;
import net.compor.frameworks.jcf.FunctionalComponent;
import net.compor.frameworks.jcf.IllegalAccessMethodException;
import net.compor.frameworks.jcf.InvalidParametersException;
import net.compor.frameworks.jcf.ServiceRequest;
import net.compor.frameworks.jcf.ServiceResponse;

/**
 * Represents an customizable component for the JCF.
 * @author <a href="mailto:mario@compor.net">Mario Hozano</a>
 * @version 1.0
 */
public abstract class CustomizableComponent extends FunctionalComponent {

  /** Concern switch. */
  private ConcernSwitch concernSwitch;

  /**
   * Constructor.
   * @param _componentName
   *          Component name.
   */
  public CustomizableComponent(final String _componentName) {
    super(_componentName);
    this.concernSwitch = new ConcernSwitch();
  }

  /**
   * Adds a concern.
   * @param _concern
   *          The concern.
   * @param _state
   *          The state of concern.
   */
  public void addConcern(final Concern _concern, final boolean _state) {
    this.concernSwitch.addConcern(_concern, Boolean.valueOf(_state));
  }

  /**
   * Gets the concern switch.
   * @return concernSwitch
   */
  public ConcernSwitch getConcernSwitch() {
    return this.concernSwitch;
  }

  /**
   * {@inheritDoc}
   */
  protected ServiceResponse receiveRequest(final ServiceRequest _request)
      throws InvalidParametersException, IllegalAccessMethodException,
      ComponentNotStartedException {

    this.concernSwitch.executeBefore(this, _request);

    ServiceResponse response = super.receiveRequest(_request);
    if (response.hasException()) {
      this.concernSwitch.executeOnException(this, _request, response);
    }

    this.concernSwitch.executeAfter(this, _request, response);
    return response;
  }

}
