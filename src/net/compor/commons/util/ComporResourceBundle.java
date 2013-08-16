package net.compor.commons.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Represents an internationalization manager in the contet of the COMPOR
 * project. This class possui a Java <code>ResourceBundle</code> to load
 * property files supporting the locale changes. This classe is abstract, so it
 * may not be instantiated. When definig subclasses for this class, it is
 * automatically created a {@link ComporResourceBundle}for a property file with
 * the same name of created subclass. For example, the subclass
 * {@link net.compor.commons.CommonsMessages}extends this class and is
 * associated to the file that is localized in the same package, called
 * <i>CommonsMessage.properties </i>. Therefore, when extending this class, it
 * must be created a property file with the same name, or else an exception will
 * be thrown at the moment of instantiating the subclass.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author Hyggo Oliveira de Almeida
 * @author Emerson Loureiro
 * @version 1.0
 */
public abstract class ComporResourceBundle {

  /** Reference to a <code>ResourceBundle</code> of the Java API. */
  private ResourceBundle resourceBundle;

  /**
   * Name of the file related to the <code>ResourceBundle</code>.
   */
  private String fileName;

  /**
   * Creates a <code>ComporResourceBundle</code> specifying the content of the
   * file, whose name is passed as a parameter.
   *
   * @param _fileName
   *          The name of the file containing the properties.
   * @throws ComporCannotLoadLocaleException
   *           Runtime exception thrown if it is not possible to load the
   *           neither specifyed file nor the default one.
   */
  public ComporResourceBundle(final String _fileName)
      throws ComporCannotLoadLocaleException {
    this.createBundle(_fileName);
  }

  /**
   * Creates a <code>ComporResourceBundle</code> with a file whose path is the
   * same of the the class.
   *
   * @throws ComporCannotLoadLocaleException
   *           Runtime exception thrown if it is not possible to load the
   *           neither specifyed file nor the default one.
   */
  public ComporResourceBundle() throws ComporCannotLoadLocaleException {
    this.createBundle(this.getClass().getName());
  }

  /**
   * Factory method [Gamma et al 1995] for creating a Java
   * <code>ResourceBundle</code> from the content of the specifyed file.
   *
   * @param _fileName
   *          The name of the file containing the properties.
   * @throws ComporCannotLoadLocaleException
   *           Runtime exception thrown if it is not possible to load the
   *           neither specifyed file nor the default one.
   */
  private void createBundle(final String _fileName)
      throws ComporCannotLoadLocaleException {
    try {
      fileName = _fileName;
      resourceBundle = ResourceBundle.getBundle(fileName);
    } catch (MissingResourceException mre) {
      throw new ComporCannotLoadLocaleException(_fileName, mre);
    } catch (NullPointerException npe) {
      throw new ComporCannotLoadLocaleException(_fileName, npe);
    }
  }

  /**
   * Loads a localization ( <i>locale </i>) related to the specifyed language
   * and contry. If it is not possible to load the specifyed localization, it
   * loads the default one from the operating system. If it fails to load the
   * default localization, an exception
   * {@link net.compor.commons.lang.ComporRuntimeException}is thrown.
   *
   * @param _language
   *          Language to be loaded.
   * @param _country
   *          Country related to the language.
   * @throws ComporCannotLoadLocaleException
   *           Runtime exception thrown if it is not possible to load the
   *           neither specifyed file nor the default one.
   */
  public void loadLocale(final String _language, final String _country)
      throws ComporCannotLoadLocaleException {
    try {
      resourceBundle = ResourceBundle.getBundle(fileName, new Locale(
          _language, _country));
    } catch (MissingResourceException mre) {
      throw new ComporCannotLoadLocaleException(fileName, _language, _country,
          mre);
    } catch (NullPointerException npe) {
      throw new ComporCannotLoadLocaleException(fileName, _language, _country,
          npe);
    }
  }

  /**
   * Returns the value of a property with the specifyed key. If the key is not
   * valid, the key itself is returned.
   *
   * @param _key
   *          The key of the property to be returned.
   * @return String The value of the property.
   */
  public String get(final String _key) {
    try {
      return resourceBundle.getString(_key);
    } catch (Exception e) {
      return _key;
    }
  }
}
