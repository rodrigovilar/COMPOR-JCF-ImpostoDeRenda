package net.compor.commons.util;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * The super class for object pool implementations based on the
 * <code>ObjectPool</code> design pattern [Gamma et al 1995].
 *
 * @author Emerson Loureiro.
 */
public abstract class ObjectPool {

  /** Indicates that an object is free to be used. */
  private static final String FREE = "free";

  /** Indicates that an object is being used. */
  private static final String USED = "used";

  /**
   * The time (in miliseconds) for which a thread will sleep when trying to
   * acquire a reusable object and all of them are in use.
   */
  private static final int THREAD_SLEEP_TIME = 10;

  /** The table for registering which objects are free and which are not. */
  private Hashtable objectTable;

  /** The maximun number of reusable objects managed by the pool. */
  private int maxNumberOfReusables;

  /**
   * Creates a new <code>ObjectPool</code>.
   */
  public ObjectPool() {
    objectTable = new Hashtable();
    maxNumberOfReusables = 5;
  }

  /**
   * Acquires a reusable object from the pool. This method blocks the current
   * thread, through the the <i>Guarded Suspension </i> design pattern [Lea97],
   * if no reusable object is available .
   *
   * @return Object.
   */
  public final synchronized  Object acquireReusable()
      throws ComporCannotCreateReusable {
    Object returnObject = null;

    while (returnObject == null) {
      /*
       * If the number of already created reusable objects is lesser than the
       * maximun number of reusables...
       */
      if (objectTable.size() < maxNumberOfReusables) {
        /*
         * ...creates a new one through the abstract method below (Template
         * Method [Gamma et al 1995]), sets its status to "used" and return it.
         */
        Object newObject = this.createReusableObject();
        objectTable.put(newObject, USED);
        returnObject = newObject;
      } else {
        returnObject = this.getAnExistingReusable();

        /*
         * If the current thread cannot acquire an reusable object, sleep and
         * try again later.
         */
        if (returnObject == null) {
          try {
            this.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

      }
    }

    return returnObject;
  }

  /**
   * Returns an existing reusable object.
   *
   * @return Object.
   */
  private Object getAnExistingReusable() {
    Enumeration keys = objectTable.keys();

    while (keys.hasMoreElements()) {
      Object object = keys.nextElement();
      String value = (String) objectTable.get(object);

      if (value.equals(FREE)) {
        objectTable.put(object, USED);
        return object;
      }
    }

    return null;
  }

  /**
   * Releases the reusable object so that it may be used by another object.
   *
   * @param _reusable
   *          The object to be released.
   */
  public final void releaseReusable(final Object _reusable)
      throws ComporCannotReleasedObjectException {
    String value = (String) objectTable.get(_reusable);

    if ((value != null) && (value.equals(USED))) {
      objectTable.put(_reusable, FREE);
    }

    value = (String) objectTable.get(_reusable);

    synchronized (this) {
      this.notifyAll();
    }

    try {
      this.releaseImpl(_reusable);
    } catch (Exception e) {
      throw new ComporCannotReleasedObjectException(_reusable, e);
    }
  }

  /**
   * Sets the maximun number of objects managed by the object pool.
   *
   * @param maxNumberOfObjects
   */
  public final void setMaxNumberOfReusables(final int _maxNumberOfReusables) {
    maxNumberOfReusables = _maxNumberOfReusables;
  }

  /**
   * Returns the number of reusable objects being managed by the pool.
   *
   * @return int.
   */
  public final int numberOfReusables() {
    return objectTable.size();
  }

  /**
   * Abstract method for creating new reusable objects.
   *
   * @return Object
   * @throws ComporCannotCreateReusable
   *           If there is an error while creating the new reusable object.
   */
  protected abstract Object createReusableObject()
      throws ComporCannotCreateReusable;

  /**
   * Used by the subclasses to perform aditional action on an object that has
   * just been released.
   *
   * @param _releasedObject
   *          The object that has been released.
   */
  protected abstract void releaseImpl(final Object _releasedObject)
      throws Exception;
}
