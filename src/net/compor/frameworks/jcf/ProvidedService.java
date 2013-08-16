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
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a service provided by a functional component.
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @version 1.0
 */
public class ProvidedService extends AbstractService {

  /**
   * {@link java.lang.reflect.Method} to be invoked when the service is
   * requested.
   */
  private Method method;

  /**
   * List of alias of services required to execute this provided service. These
   * alias refer to the required services list in {@link FunctionalComponent}.
   */
  private Set requiredServicesAlias;

  /**
   * Creates a new <code>ProvidedService</code> with the specified
   * specification. The {@link java.lang.reflect.Method} to be invoked when the
   * service is requested is defined too.
   * @param _specification
   *          service specification.
   * @param _method
   *          {@link java.lang.reflect.Method} to be invoked when the service
   *          is requested.
   * @param _requiredServicesAlias
   *          set of required services alias.
   */
  public ProvidedService(final ServiceSpecification _specification,
      final Method _method, final Set _requiredServicesAlias) {
    super(_specification);
    method = _method;
    requiredServicesAlias = _requiredServicesAlias;
  }

  /**
   * Creates a new <code>ProvidedService</code> with the specified
   * specification. The {@link java.lang.reflect.Method} to be invoked when the
   * service is requested is defined too.
   * @param _specification
   *          service specification.
   * @param _method
   *          {@link java.lang.reflect.Method} to be invoked when the service
   *          has been requested.
   */
  public ProvidedService(final ServiceSpecification _specification,
      final Method _method) {
    this(_specification, _method, new HashSet());
  }

  /**
   * Returns the {@link java.lang.reflect.Method} to be invoked when the service
   * is requested.
   * @return method {@link java.lang.reflect.Method} to be invoked when the
   *         service is requested.
   */
  public final Method getMethod() {
    return method;
  }

  /**
   * Updates an alias for a service required to execute this provided service.
   * @param _oldAlias
   *          alias to be changed.
   * @param _newAlias
   *          new alias to be set.
   */
  public final void updateRequiredServiceAlias(final String _oldAlias,
      final String _newAlias) {
    if (requiredServicesAlias != null) {
      requiredServicesAlias.remove(_oldAlias);
      requiredServicesAlias.add(_newAlias);
    }
  }

}
