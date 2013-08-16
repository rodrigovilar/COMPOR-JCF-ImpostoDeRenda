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

import net.compor.commons.util.ComporCannotLoadLocaleException;
import net.compor.commons.util.ComporResourceBundle;

/**
 * This class is responsible for loading exception messages for the JCF project.
 * @author <a href="mailto:emerson@compor.net">Emerson Loureiro</a>
 * @version 1.0
 */
public final class JCFMessages extends ComporResourceBundle {

  /** Singleton instance of this class. */
  private static final JCFMessages SINGLETON = new JCFMessages();

  /**
   * Private constructor of the class. Creates a new <code>JCFMessages</code>.
   * @throws ComporCannotLoadLocaleException
   *           <i>Runtime </i> exception if it is not possible to load neither
   *           the specifyed file nor the default one.
   */
  private JCFMessages() throws ComporCannotLoadLocaleException {
    super();
  }

  /**
   * Returns the singleton instance of the class.
   * @return JCFMessages singleton instance.
   */
  public static JCFMessages getInstance() {
    return SINGLETON;
  }
}
