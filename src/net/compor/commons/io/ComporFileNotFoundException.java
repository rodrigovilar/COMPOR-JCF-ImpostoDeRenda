package net.compor.commons.io;

import java.io.File;

import net.compor.commons.CommonsMessages;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Indica que a opera��o de acesso a algum arquivo representado por
 * <code>File</code> falhou devido � inexist�ncia do arquivo.
 *
 * @author Hyggo Oliveira de Almeida
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @version 1.0
 */
public class ComporFileNotFoundException extends ComporIOException {

  /** Arquivo relacionado � esta exce��o */
  private File file;

  /**
   * Cria uma nova exce��o <code>ComporFileNotFoundException</code>
   * relacionada ao arquivo e causa especificados.
   *
   * @param _file
   *          arquivo que gerou a exce��o.
   * @param _cause
   *          a causa da exce��o. O valor <code>null</code> � permitido e
   *          indica que � inexistente ou desconhecida.
   */
  public ComporFileNotFoundException(final File _file, final Throwable _cause) {
    super(_cause);
    file = _file;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.io.ComporIOException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat
        .formatProperty(CommonsMessages.getInstance().get(
            "io.exception.filenotfound"), file.getAbsolutePath()); //$NON-NLS-1$
  }

  /**
   * @return Returns the file.
   */
  public File getFile() {
    return file;
  }
}
