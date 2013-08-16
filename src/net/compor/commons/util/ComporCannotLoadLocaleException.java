package net.compor.commons.util;

import net.compor.commons.lang.ComporRuntimeException;

/**
 * Indicates that the operation of loading the localization (language and
 * country) has failed. The message of this exception is not internationalized
 * because it is directly related to the internationalization mechanism.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class ComporCannotLoadLocaleException extends ComporRuntimeException {

  /** Default message. */
  private static final String EXCEPTION_MESSAGE =
    "Cannot load locale for:[filename = {0}, [language = {1}], [country = {2}]";

  /** The name of the file related to this exception. */
  private String fileName;

  /** Language related to this exception. */
  private String language;

  /** Country related to this exception. */
  private String country;

  /**
   * Creates a new <code>ComporCannotLoadLocaleException</code> specifying the
   * file language and country related to the localization.
   *
   * @param _fileName
   *          The name of the file related to the localization.
   * @param _language
   *          Language related to the localization.
   * @param _country
   *          Country related to the localization.
   * @param _cause
   *          The cause of the exception. The value <code>null</code> is
   *          allowed and indicates the it is inexistent of unknown.
   */
  public ComporCannotLoadLocaleException(final String _fileName,
      final String _language, final String _country, final Throwable _cause) {
    super(_cause);
    fileName = _fileName;
    language = _language;
    country = _country;
  }

  /**
   * Creates a new <code>ComporCannotLoadLocaleException</code> specifying the
   * file and cause of the exception.
   *
   * @param _fileName
   *          The name of the file related to the localization.
   * @param _cause
   *          The cause of the exception. The value <code>null</code> is
   *          allowed and indicates the it is inexistent of unknown.
   */
  public ComporCannotLoadLocaleException(final String _fileName,
      final Throwable _cause) {
    super(_cause);
    fileName = _fileName;
  }

  /**
   * Returns the country related to the exception.
   *
   * @return String.
   */
  public String getCountry() {
    return country;
  }

  /**
   * Returns the name of the file related to the exception.
   *
   * @return String.
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * Returns the language related to the exception.
   *
   * @return String
   */
  public String getLanguage() {
    return language;
  }

  /*
   * (non-Javadoc)
   *
   * @see compor.commons.lang.ComporException#getFormattedMessage()
   */
  protected String getFormattedMessage() {
    return ComporResourceBundleMessageFormat.formatProperty(EXCEPTION_MESSAGE,
        new Object[] { fileName, language, this.country });
  }
}
