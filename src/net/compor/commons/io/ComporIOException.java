package net.compor.commons.io;

import net.compor.commons.CommonsMessages;
import net.compor.commons.lang.ComporException;

/**
 * Indica que alguma opera��o de entrada e sa�da falhou.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class ComporIOException extends ComporException {

  /**
   * Cria uma nova exce��o <code>ComporIOException</code> relacionada � causa
   * especificados.
   *
   * @param _cause
   *          a causa da exce��o. O valor <code>null</code> � permitido e
   *          indica que � inexistente ou desconhecida.
   */
  public ComporIOException(final Throwable _cause) {
    super(_cause);
  }

  /*
   * (non-Javadoc)
   *
   * @see compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return CommonsMessages.getInstance().get("io.exception.io");
  }

}
