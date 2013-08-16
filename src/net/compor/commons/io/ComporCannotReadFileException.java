package net.compor.commons.io;

import java.io.File;

import net.compor.commons.CommonsMessages;
import net.compor.commons.util.ComporResourceBundleMessageFormat;

/**
 * Indica que uma opera��o de leitura do arquivo especificado falhou. Esta
 * exce��o ser� lan�ada quando o arquivo especificado n�o existir ou quando n�o
 * estiver acess�vel.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class ComporCannotReadFileException extends ComporIOException {

  private File file;

  /**
   * Cria uma nova exce��o <code>ComporCannotReadFileException</code>
   * relacionada ao arquivo e causa especificados.
   *
   * @param _file
   *          arquivo que gerou a exce��o.
   * @param _cause
   *          a causa da exce��o. O valor <code>null</code> � permitido e
   *          indica que � inexistente ou desconhecida.
   */
  public ComporCannotReadFileException(final File _file,
      final Throwable _cause) {
    super(_cause);
    file = _file;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.io.ComporIOException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(CommonsMessages
        .getInstance().get("io.exception.cannotreadfile"), file.getName());
  }

  /**
   * @return Returns the file.
   */
  public File getFile() {
    return file;
  }
}
