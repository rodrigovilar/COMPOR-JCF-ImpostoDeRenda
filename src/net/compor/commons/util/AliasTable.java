package net.compor.commons.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an alias table. This table is used to manage the association of
 * alias to names and values. A {@link AliasIF}value or entity can be
 * associated to and retrivied through an alias, which can be changed without
 * changing the entity name.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class AliasTable {

  /** Mapping table (Name - Alias). */
  private Map nameAlias;

  /** Mapping table (Alias - Value). */
  private Map aliasValue;

  /**
   * Creates a new <code>AliasTable</code>.
   */
  public AliasTable() {
    this.nameAlias = new HashMap();
    this.aliasValue = new HashMap();
  }

  /**
   * Inserts a new entity in the table.
   *
   * @param _aliasEntity
   *          new entity to be inserted in the table.
   */
  public void put(final AliasIF _aliasEntity) {
    nameAlias.put(_aliasEntity.getName(), _aliasEntity.getAlias());
    aliasValue.put(_aliasEntity.getAlias(), _aliasEntity);
  }

  /**
   * Modify the alias for the specified entity.
   *
   * @param _name
   *          entity name.
   * @param _newAlias
   *          new alias for the entity.
   */
  public void changeAlias(final String _name, final String _newAlias) {
    String oldAlias = (String) nameAlias.get(_name);
    AliasIF value = (AliasIF) aliasValue.remove(oldAlias);
    value.setAlias(_newAlias);
    nameAlias.put(_name, _newAlias);
    aliasValue.put(_newAlias, value);
  }

  /**
   * Removes an entity from the alias table.
   *
   * @param _name
   *          entity to be removed.
   */
  public void remove(final String _name) {
    String alias = (String) nameAlias.remove(_name);
    aliasValue.remove(alias);
  }

  /**
   * Indicates if the table contains the specified alias.
   *
   * @param _alias
   *          alias to be verified.
   * @return boolean true, if the table contains it, false, on the contrary.
   */
  public boolean contains(final String _alias) {
    return aliasValue.containsKey(_alias);
  }

  /**
   * Returns the alias for the specified name.
   *
   * @param _name
   *          entity name.
   * @return String alias for the entity name.
   */
  public String getAlias(final String _name) {
    return (String) nameAlias.get(_name);
  }

  /**
   * Returns the {@link AliasIF}value associated to the specified alias.
   *
   * @param _alias
   *          entity alias.
   * @return {@link AliasIF}entity associated to the specified alias.
   */
  public AliasIF getValueByAlias(final String _alias) {
    return (AliasIF) aliasValue.get(_alias);
  }

  /**
   * Returns a {@link AliasIF}value related to the entity name.
   *
   * @param _name
   *          entity name.
   * @return {@link AliasIF}entity associated to the specified name.
   */
  public AliasIF getValueByName(final String _name) {
    String alias = (String) nameAlias.get(_name);
    return (AliasIF) aliasValue.get(alias);
  }

  /**
   * Returns a {@link java.util.Collection}of the {@link AliasIF}values that
   * this table contains.
   *
   * @return Collection entities that belong to this table.
   */
  public Collection getValues() {
    return aliasValue.values();
  }

}
