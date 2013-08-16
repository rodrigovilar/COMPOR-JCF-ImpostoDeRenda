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

import net.compor.commons.async.ComporAsynchronousMethodInvocation;
import net.compor.commons.async.ComporAsynchronousResult;

/**
 * Represents an event dispatched as an asynchronous method invocation.
 * @author <a href="mailto:hyggo@compor.net">Hyggo Almeida</a>
 * @version 1.0
 */
public class ComponentAsyncEvent extends ComporAsynchronousMethodInvocation {

  /** Event dispatched asynchronously. */
  private EventAnnouncement eventAnounce;

  /** Async event announcer. */
  private AsyncEventAnnouncerIF announcer;

  /**
   * Creates a new <code>ComponentAsyncEvent</code> specifying the announcer
   * and announcement of the event.
   * @param _announcer
   *          asynchronous event announcer.
   * @param _eventAnounce
   *          generated event.
   */
  public ComponentAsyncEvent(final AsyncEventAnnouncerIF _announcer,
      final EventAnnouncement _eventAnounce) {
    announcer = _announcer;
    eventAnounce = _eventAnounce;
  }

  /**
   * {@inheritDoc}
   */
  protected void receiveAsynchronousMethodResult(
      final ComporAsynchronousResult _result) {
  }

  /**
   * {@inheritDoc}
   */
  protected Object invokeAsynchronousMethod() throws Exception {
    announcer.announceAsyncEvent(eventAnounce);
    return null;
  }
}
