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

import net.compor.commons.lang.ComporRuntimeException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * This exception is throw when a required service by a functional component is
 * declared.
 * @author <a href="mailto:marcos@compor.net">Marcos Pereira</a>
 * @version 1.0
 */
public class RequiredServiceNotDeclaredException extends ComporRuntimeException {

  /** Service name. */
  private String serviceName;

  /**
   * Component name.
   */
  private String componentName;

  /**
   * Creates a new instance of the <code>RequiredServiceNotDeclaredException</code> class.
   * @param _serviceName
   *          service name.
   * @param _component
   *          component.
   */
  public RequiredServiceNotDeclaredException(final String _serviceName,
      final FunctionalComponent _component) {
    serviceName = _serviceName;
    componentName = _component.getName();
  }

  /**
   * {@inheritDoc}
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(JCFMessages
        .getInstance().get("exception.requiredservicenotdeclared"),
    //$NON-NLS-1$
        new Object[] { serviceName, this.getComponentName() });
  }

  /**
   * Returns the component name.
   * @return String component name.
   */
  public String getComponentName() {
    return componentName;
  }
}
