package net.compor.commons.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Vector;

/**
 * A list which does not contain repeated elements.
 *
 * @author Emerson Loureiro.
 * @version 1.0
 */
public class ListSet implements Set {

  /** The elements of the list. */
  private List elements;

  /**
   * Creates a new <code>ListSet</code>.
   */
  public ListSet() {
    this(0);
  }

  /**
   * Creates a new <code>ListSet</code> with the specified size.
   *
   * @param _size
   *          The initial size of the list.
   */
  public ListSet(final int _size) {
    this.elements = new Vector(_size);
  }

  /**
   * Changes the position of an existing object on the list the one passed on
   * the parameter.
   *
   * @param _element
   *          The element contained in the list.
   * @param _newPosition
   *          The new position of the object.
   */
  public boolean reset(final Object _element, final int _newPosition) {
    /* Verifica se � poss�vel remover o objeto da lista... */
    if (!elements.remove(_element)) {
      /*
       * ...caso n�o seja, o elemento n�o est� contido na lista, e portanto o
       * m�todo retorna false.
       */
      return false;
    }

    elements.add(_newPosition, _element);
    return true;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.List#add(int, java.lang.Object)
   */
  public boolean add(final int _position, final Object _element) {
    if (!elements.contains(_element)) {
      elements.add(_position, _element);
      return true;
    }
    return false;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.List#remove(int)
   */
  public Object remove(final int _position) {
    return elements.remove(_position);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.List#set(int, java.lang.Object)
   */
  public Object set(final int _position, final Object _element) {
    return elements.set(_position, _element);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.Collection#size()
   */
  public int size() {
    return elements.size();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.List#addAll(int, java.util.Collection)
   */
  public boolean addAll(final int _position, final Collection _elements) {
	for (Iterator iter = _elements.iterator(); iter.hasNext();) {
		if (elements.contains(iter.next())) {
			return false;
		}
	}
    return elements.addAll(_position, _elements);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.List#get(int)
   */
  public Object get(final int _position) {
    return elements.get(_position);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.List#indexOf(java.lang.Object)
   */
  public int indexOf(final Object _position) {
    return elements.indexOf(_position);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.List#lastIndexOf(java.lang.Object)
   */
  public int lastIndexOf(final Object _position) {
    return elements.lastIndexOf(_position);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.List#listIterator()
   */
  public ListIterator listIterator() {
    return elements.listIterator();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.List#listIterator(int)
   */
  public ListIterator listIterator(final int _position) {
    return elements.listIterator(_position);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.List#subList(int, int)
   */
  public List subList(final int _start, final int _offset) {
    return elements.subList(_start, _offset);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.Collection#add(java.lang.Object)
   */
  public boolean add(final Object _element) {
    return this.add(this.elements.size(), _element);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.Collection#addAll(java.util.Collection)
   */
  public boolean addAll(final Collection _elements) {	  
    return elements.addAll(this.elements.size(), _elements);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.Collection#clear()
   */
  public void clear() {
    elements.clear();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.Collection#contains(java.lang.Object)
   */
  public boolean contains(final Object _element) {
    return elements.contains(_element);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.Collection#containsAll(java.util.Collection)
   */
  public boolean containsAll(final Collection _elements) {
    return elements.contains(_elements);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.Collection#isEmpty()
   */
  public boolean isEmpty() {
    return elements.isEmpty();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Iterable#iterator()
   */
  public Iterator iterator() {
    return elements.iterator();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.Collection#remove(java.lang.Object)
   */
  public boolean remove(final Object _element) {
    return elements.remove(_element);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.Collection#removeAll(java.util.Collection)
   */
  public boolean removeAll(final Collection _elements) {
    return elements.removeAll(_elements);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.Collection#retainAll(java.util.Collection)
   */
  public boolean retainAll(final Collection _elements) {
    return elements.retainAll(_elements);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.Collection#toArray()
   */
  public Object[] toArray() {
    return elements.toArray();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.util.Collection#toArray(java.lang.Object[])
   */
  public Object[] toArray(final Object[] _elements) {
    return elements.toArray(_elements);
  }

}
