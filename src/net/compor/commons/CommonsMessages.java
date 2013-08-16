package net.compor.commons;

import net.compor.commons.util.ComporCannotLoadLocaleException;
import net.compor.commons.util.ComporResourceBundle;

/**
 * This class represents the {@link ComporResourceBundle}to the
 * internationalized messages of the package {@link compor.commons}.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author Hyggo Oliveira de Almeida
 * @author Emerson Loureiro
 * @version 1.0
 */
public final class CommonsMessages extends ComporResourceBundle {

  /** Single instance of this class. {@link ComporResourceBundle} */
  private static final CommonsMessages SINGLETON_INSTANCE =
    new CommonsMessages();

  /**
   * Private constructor of the class. Creates a new
   * <code>CommonsMessages</code>.
   *
   * @throws ComporCannotLoadLocaleException
   *           <i>Runtime </i> exception if it is not possible to load neither
   *           the specifyed file nor the default one.
   */
  private CommonsMessages() throws ComporCannotLoadLocaleException {
    super();
  }

  /**
   * Returns the single instance of the class.
   *
   * @return CommonsMessages
   */
  public static CommonsMessages getInstance() {
    return SINGLETON_INSTANCE;
  }
}
