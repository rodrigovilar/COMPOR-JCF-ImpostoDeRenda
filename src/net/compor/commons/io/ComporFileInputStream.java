package net.compor.commons.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Fluxo de entrada para opera��es de leitura de arquivos. Encapsula um fluxo da
 * API de Java para prover m�todos de manipula��o de dados em arquivos com a
 * utiliza��o de {@link java.io.DataInputStream} e
 * {@link java.io.BufferedInputStream} e {@link java.io.FileInputStream}.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public class ComporFileInputStream {

  /** Indica o fim do arquivo associado a este fluxo */
  private static final int END_OF_FILE = -1;

  /** Arquivo associado a este fluxo */
  private File file;

  /** Fluxo de entrada de dados da API de Java */
  private DataInputStream dataInputStream;

  /**
   * Cria um novo <code>ComporFileInputStream</code> associando-o ao arquivo
   * especificado.
   *
   * @param _file
   *          arquivo associado a este fluxo de entrada.
   * @throws ComporFileNotFoundException
   *           caso o arquivo n�o exista ou n�o esteja acess�vel.
   */
  public ComporFileInputStream(final File _file)
      throws ComporFileNotFoundException {
    try {
      file = _file;
      dataInputStream = new DataInputStream(new BufferedInputStream(
          new FileInputStream(file)));
    } catch (FileNotFoundException fnfe) {
      throw new ComporFileNotFoundException(file, fnfe);
    }
  }

  /**
   * L� o conte�do do arquivo associado a este fluxo e o retorna em um
   * <code>StringBuffer</code>. Este m�todo deve ser usado para a leitura de
   * arquivos de texto.
   *
   * @return StringBuffer texto do arquivo.
   * @throws ComporCannotReadFileException
   *           caso o arquivo n�o exista ou n�o esteja acess�vel.
   */
  public StringBuffer readText() throws ComporCannotReadFileException {
    int currentByte;
    StringBuffer textOfFile = new StringBuffer();
    try {
      while ((currentByte = dataInputStream.read()) != END_OF_FILE) {
        textOfFile.append((char) currentByte);
      }
      return textOfFile;
    } catch (IOException ioe) {
      throw new ComporCannotReadFileException(file, ioe);
    }
  }

  /**
   * L� o texto do arquivo associado a este fluxo e o retorna em um array de
   * <code>bytes</code>. Este m�todo deve ser utilizado para leitura de
   * arquivos bin�rios.
   *
   * @return byte[] bytes do arquivo
   * @throws ComporCannotReadFileException
   *           caso o arquivo n�o exista ou n�o esteja acess�vel.
   */
  public byte[] readBytes() throws ComporCannotReadFileException {
    final byte[] allBytes;
    try {
      allBytes = new byte[dataInputStream.available()];
      dataInputStream.readFully(allBytes);
      return allBytes;
    } catch (IOException ioe) {
      throw new ComporCannotReadFileException(file, ioe);
    }
  }

  /**
   * Fecha este fluxo de entrada.
   *
   * @throws ComporCannotCloseFileException
   *           caso o arquivo n�o possa ser fechado.
   */
  public void close() throws ComporCannotCloseFileException {
    try {
      dataInputStream.close();
    } catch (IOException ioe) {
      throw new ComporCannotCloseFileException(file, ioe);
    }
  }

  /**
   * Retorna um fluxo de entrada da API de java <code>InputStream</code>
   * associada a este fluxo.
   *
   * @return InputStream java.io.OutputStream referente a este fluxo de sa�da
   */
  public InputStream toInputStream() {
    return dataInputStream;
  }
}
