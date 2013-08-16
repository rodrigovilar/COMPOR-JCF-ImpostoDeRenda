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
 * This exception is throw when an invalid property is set as initialization
 * property of a component.
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @version 1.0
 */
public class InvalidInitializationPropertyException extends ComporRuntimeException {

  /** The name of the property that should be set. */
  private String propertyName;

  /** The value that should be set to the property. */
  private Object propertyValue;

  /**
   * Creates a new <code>ComporInvalidInitializationPropertyException</code>
   * with the specified property name and property value.
   * @param _propertyName
   *          name of the property that should be set.
   * @param _propertyValue
   *          value that should be set to the property.
   */
  public InvalidInitializationPropertyException(final String _propertyName,
      final Object _propertyValue) {
    super();
    propertyName = _propertyName;
    propertyValue = _propertyValue;
  }

  /**
   * {@inheritDoc}
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(JCFMessages
        .getInstance().get("exception.invalidinitializationproperty"), //$NON-NLS-1$
        new Object[] { propertyName, propertyValue });
  }
}
