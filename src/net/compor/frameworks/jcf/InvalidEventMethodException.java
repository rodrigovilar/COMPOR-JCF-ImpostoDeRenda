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

import net.compor.commons.lang.ComporRuntimeException;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * This exception is throw when the methd defined to be invoked in an event of
 * interest is not defined.
 * @author <a href="mailto:marcos@compor.net">Marcos Pereira</a>
 */
public class InvalidEventMethodException extends ComporRuntimeException {

  /** Types of the method parameters. */
  private Class[] parameterTypes;

  /** Method name. */
  private String methodName;

  /**
   * Creates a new instance of <code>InvalidEventMethodException</code>
   * @param _method
   *          method that has been invoked.
   */
  public InvalidEventMethodException(final Method _method) {
    parameterTypes = _method.getParameterTypes();
    methodName = _method.getName();
  }

  /**
   * {@inheritDoc}
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(JCFMessages
        .getInstance().get("exception.invalideventmethod"), //$NON-NLS-1$
        new Object[] { this.getMethodName(), this.getParameterTypes() });
  }

  /**
   * Returns the types of the method parameters.
   * @return Class[] parameter types
   */
  public Class[] getParameterTypes() {
    return parameterTypes;
  }

  /**
   * Returns the method name.
   * @return String method name.
   */
  public String getMethodName() {
    return methodName;
  }
}
