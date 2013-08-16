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
 * This exception is thrown when a service newAlias already in use is defined
 * for a given service.
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @version 1.0
 */
public class AliasAlreadyInUseException extends ComporRuntimeException {

  /**
   * New alias.
   */
  private String newAlias;

  /**
   * Current alias.
   */
  private String currentAlias;

  /**
   * Creates a new reference of <code>AliasAlreadyInUseException</code>.
   * @param _newAlias
   *          new alias.
   * @param _currentAlias
   *          current alias.
   */
  public AliasAlreadyInUseException(final String _newAlias,
      final String _currentAlias) {
    newAlias = _newAlias;
    currentAlias = _currentAlias;
  }

  /**
   * Gets new alias.
   * @return String new alias.
   */
  public final String getAlias() {
    return newAlias;
  }

  /**
   * Gets current alias.
   * @return String current alias.
   */
  public final String getAncestor() {
    return currentAlias;
  }

  /**
   * {@inheritDoc}
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(JCFMessages
        .getInstance().get("exception.aliasalreadyinuse"), //$NON-NLS-1$
        new Object[] { this.getAlias(), this.getAncestor() });
  }
}
