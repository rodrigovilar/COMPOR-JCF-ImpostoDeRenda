package net.compor.commons.util;

/**
 * Interface implemented by entities that must be accessed through an alias. It
 * is used together with an {@link AliasTable}.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public interface AliasIF {

  /**
   * Returns the name of this entity which is represented by an alias.
   *
   * @return String name of this entity.
   */
  String getName();

  /**
   * Returns the alias for this entity.
   *
   * @return String alias for this entity.
   */
  String getAlias();

  /**
   * Sets an alias for this entity.
   *
   * @param _alias
   *          alias for this entity.
   */
  void setAlias(String _alias);
}
