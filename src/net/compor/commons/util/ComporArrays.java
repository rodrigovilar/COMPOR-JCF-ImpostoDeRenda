package net.compor.commons.util;

import java.util.Arrays;

/**
 * Esta classe cont�m m�todos para manipula��o de arrays.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public final class ComporArrays {

  /**
   * Construtor privado para evitar instancia��es.
   */
  private ComporArrays() {
  }

  /**
   * Retorna uma String com o conte�do dos elementos de um array separados por
   * linha.
   *
   * @param _array
   *          Array a ser retornado como String
   * @return String
   */
  public static String asString(final Object[] _array) {
    return Arrays.asList(_array).toString();
  }
}
