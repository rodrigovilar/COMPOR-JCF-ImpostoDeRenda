package net.compor.commons.text;

import java.text.MessageFormat;

/**
 * This class contains methods for text formatting.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public final class ComporMessageFormat {

  /**
   * Private constructor to avoid instatiation.
   */
  private ComporMessageFormat() {
  }

  /**
   * Formats an <code>String</code> containing a formatting pattern, inserting
   * the argument on the <code>String</code> according to the pattern.
   *
   * @param _pattern
   *          Formatting pattern.
   * @param _argument
   *          Argument to be inserted.
   * @return String Formatted <code>String</code>.
   */
  public static String format(final String _pattern, final Object _argument) {
    return MessageFormat.format(_pattern, new Object[] { _argument });
  }

  /**
   * Formats an <code>String</code> containing a formatting pattern, inserting
   * the arguments on this <code>String</code> according to the pattern.
   *
   * @param _pattern
   *          Formatting pattern.
   * @param _arguments
   *          Arguments to be inserted.
   * @return String Formatted <code>String</code>.
   */
  public static String format(final String _pattern,
      final Object[] _arguments) {
    return MessageFormat.format(_pattern, _arguments);
  }
}
