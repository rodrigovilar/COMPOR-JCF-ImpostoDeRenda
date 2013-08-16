package net.compor.commons.io;

import java.io.File;
import java.io.FileFilter;

/**
 * Representa um arquivo no contexto do projeto COMPOR. Todas as classes do
 * sistema de arquivos do projeto s�o subclasses desta classe abstrata.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public abstract class ComporFile extends File {

  /**
   * Cria um novo arquivo no contexto do projeto COMPOR.
   *
   * @param _pathname
   *          caminho do arquivo.
   */
  public ComporFile(final String _pathname) {
    super(_pathname);
  }

  /**
   * Retorna o filtro para exibi��o e localiza��o do arquivo.
   *
   * @return FileFilter filtro do arquivo.
   */
  public abstract FileFilter getFileFilter();

}
