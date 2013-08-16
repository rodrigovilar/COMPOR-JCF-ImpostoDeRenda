package net.compor.commons.util.jar;

import java.io.BufferedInputStream;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import net.compor.commons.io.ComporFile;
import net.compor.commons.io.ComporIOException;

import net.compor.commons.io.ComporFileFilter;

/**
 * Representa um arquivo JAR no contexto do Projeto COMPOR. Disponibiliza
 * m�todos de recupera��o de recursos contidos em arquivos .JAR.
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class ComporJarFile extends ComporFile {

  /** Extens�o do arquivo JAR */
  public static final String FILE_EXTENSION = "jar"; //$NON-NLS-1$

  /** Pasta de informa��es dos arquivos JAR */
  public static final String META_INF_FOLDER = "META-INF/"; //$NON-NLS-1$

  /** Constante representativa de tamanho de arquivo desconhecido */
  private static final int UNKNOWN_FILE_SIZE = -1;

  /** Constante representativa de fim de leitura de arquivo */
  private static final int END_OF_READ_FILE = -1;

  /** Tabela de conte�do do arquivo */
  private Map jarContentsTable = new HashMap();

  /** Tabela de tamanhos do arquivo */
  private Map jarSizesTable = new HashMap();

  /** Refer�ncia a um fluxo de entrada para arquivos ZIP/JAR */
  private ZipInputStream zipInputStream;

  /**
   * Cria um novo <code>ComporJarFile</code> com o nome especificado.
   *
   * @param _fileName
   *          nome do arquivo.
   */
  public ComporJarFile(final String _fileName) throws ComporIOException {
    super(_fileName);
    try {
      zipInputStream = new ZipInputStream(new BufferedInputStream(
          new FileInputStream(this)));
      this.buildContentMap();
    } catch (IOException ioe) {
      throw new ComporIOException(ioe);
    }
  }

  /**
   * Recupera o array de bytes referente ao arquivo que representa o recurso
   * solicitado.
   *
   * @param _resourceName
   *          nome do recurso a ser recuperado.
   * @return byte[] array de bytes referente ao arquivo que representa o recurso
   *         solicitado.
   */
  public byte[] getResource(final String _resourceName) {
    return (byte[]) jarContentsTable.get(_resourceName);
  }

  /**
   * Constr�i a tabela com os as entradas de arquivos contidas no arquivo
   * especificado. O conte�do das entradas armazenado � um array de bytes.
   *
   * @throws ComporIOException
   *           caso haja problemas na leitura do arquivo.
   */
  private void buildContentMap() throws ComporIOException {
    try {
      this.buildSizeMap();
      ZipEntry zipEntry = null;
      while ((zipEntry = zipInputStream.getNextEntry()) != null) {
        if (!zipEntry.isDirectory()) {
          jarContentsTable.put(zipEntry.getName(), this
              .readBytesOfStream(this.getEntrySize(zipEntry)));
        }
      }
    } catch (IOException ioe) {
      throw new ComporIOException(ioe);
    }
  }

  /**
   * Constr�i a tabela com os as entradas de arquivos contidas no arquivo
   * especificado. O conte�do das entradas armazenado � um array de bytes.
   *
   * @throws IOException
   *           caso haja problemas na leitura do arquivo.
   */
  private void buildSizeMap() throws IOException {
    ZipFile zipFile;
    zipFile = new ZipFile(this);
    ZipEntry currentEntry;
    for (Enumeration iter = zipFile.entries(); iter.hasMoreElements();) {
      currentEntry = (ZipEntry) iter.nextElement();
      jarSizesTable.put(currentEntry.getName(), new Integer(
          (int) currentEntry.getSize()));
    }
    zipFile.close();
  }

  /**
   * L� um a quantidade de bytes especificada do fluxo de entrada deste arquivo
   * e o retorna em um array.
   *
   * @param _size
   *          quantidade de bytes a ser lida.
   * @return byte[] array de bytes lidos.
   * @throws IOException
   *           caso haja problemas na leitura do arquivo.
   */
  private byte[] readBytesOfStream(final int _size) throws IOException {
    byte[] byteArray = new byte[_size];
    int initialIndex = 0;
    int bytesToRead = 0;
    while (((int) _size - initialIndex) > 0) {
      bytesToRead = zipInputStream.read(byteArray, initialIndex, _size
          - initialIndex);
      if (bytesToRead == ComporJarFile.END_OF_READ_FILE) {
        break;
      }
      initialIndex += bytesToRead;
    }
    return byteArray;
  }

  /**
   * Retorna o tamanho em bytes de uma entrada de arquivo.
   *
   * @param _zipEntry
   *          entrada do arquivo.
   * @return int tamanho, em bytes, da entrada.
   */
  private int getEntrySize(final ZipEntry _zipEntry) {
    int size = (int) _zipEntry.getSize();
    if (size != ComporJarFile.UNKNOWN_FILE_SIZE) {
      return size;
    } else {
      return ((Integer) jarSizesTable.get(_zipEntry.getName())).intValue();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see ComporFile#getFileFilter()
   */
  public FileFilter getFileFilter() {
    return new ComporFileFilter(ComporJarFile.FILE_EXTENSION);
  }

  /**
   * Retorna as entradas deste arquivo JAR.
   *
   * @return Iterator iterador das entradas do arquivo JAR.
   */
  public Iterator entries() {
    return jarContentsTable.keySet().iterator();
  }

}
