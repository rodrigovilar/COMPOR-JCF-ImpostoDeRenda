package net.compor.commons.io;

import java.io.File;
import java.io.FileFilter;

/**
 * Representa um filtro de arquivo no contexto do Projeto COMPOR.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class ComporFileFilter implements FileFilter {

  /** Extens�o do arquivo */
  private String fileExtension;

  /**
   * Cria um novo <code>ComporFileFilter</code> com extens�o de arquivo
   * especificada.
   *
   * @param _fileExtension
   *          extens�o de arquivo.
   */
  public ComporFileFilter(final String _fileExtension) {
    fileExtension = _fileExtension;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.io.FileFilter#accept(java.io.File)
   */
  public boolean accept(final File _file) {
    return _file.getName().toUpperCase().endsWith(fileExtension.toUpperCase());
  }
}
