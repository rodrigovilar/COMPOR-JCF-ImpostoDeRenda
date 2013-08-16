package net.compor.commons.util;

import net.compor.commons.lang.ComporSystemConstants;
import net.compor.commons.text.ComporMessageFormat;

/**
 * This class represents a formatter of messages to be used by all
 * {@link ComporResourceBundle}. It provides methods to format messages,
 * allowing the insertion of special characters in the property file.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public final class ComporResourceBundleMessageFormat {

  /** Default code for line break in the message files. */
  private static final String LINE_SEPARATOR_CODE = "!#!"; //$NON-NLS-1$

  /**
   * Private constructor to avoid instantiations.
   */
  private ComporResourceBundleMessageFormat() {
  }

  /**
   * Formats the message of a determined property, taking into account the
   * special characters defined by
   * <code>ComporResourceBundleMessageFormat</code>, inserting the argument
   * in <code>String</code> of return.
   *
   * @param _pattern
   *          Pattern to be formatted.
   * @param _argument
   *          Argumento to be inserted.
   * @return String.
   */
  public static String formatProperty(final String _pattern,
      final Object _argument) {
    return ComporResourceBundleMessageFormat.formatProperty(_pattern,
        new Object[] { _argument });
  }

  /**
   * Formats the message of a determined property, taking into account the
   * special characters defined by
   * <code>ComporResourceBundleMessageFormat</code>, inserting the argument
   * in <code>String</code> of return.
   *
   * @param _pattern
   *          Pattern to be formatted.
   * @param _arguments
   *          Arguments to be inserted.
   * @return String Formatted <code>String</code>.
   */
  public static String formatProperty(final String _pattern,
      final Object[] _arguments) {
    return ComporMessageFormat.format(ComporResourceBundleMessageFormat
        .applySpecialChars(_pattern), _arguments);
  }

  /**
   * Creates a new <code>String</code> applying the special characters to the
   * <code>String</code> argument.
   *
   * @param _text
   *          The text to be formatted.
   * @return String Formatted <code>String</code>.
   *
   * @throws NullPointerException if _text is null.
   */
  private static String applySpecialChars(final String _text)
      throws NullPointerException {
    String temp = _text;
    for (int i = 0; (i = temp.indexOf(LINE_SEPARATOR_CODE)) != -1;) {
      temp = temp.substring(0, i) + ComporSystemConstants.LINE_SEPARATOR
      + temp.substring(i + LINE_SEPARATOR_CODE.length(), temp.length());
    }
    return temp;
  }
}
