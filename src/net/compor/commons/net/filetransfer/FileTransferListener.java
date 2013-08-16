/*
 * Created on 12/01/2005
 */
package net.compor.commons.net.filetransfer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Map;

import net.compor.commons.net.ServerListenerAdapter;

/**
 * Implements a file transfer listener. All other methods of the
 * {@link net.compor.commons.net.ServerListenerIF} are final. Subclasses must
 * implement the abstract method {@link FileTransferListener#receive(File)}
 *
 * @author <a href="mailto:glauber@compor.net">Glauber Ferreira</a>
 * @version 1.0
 */
public abstract class FileTransferListener extends ServerListenerAdapter {

  /**
   * Directory in which the file is wrote.
   */
  private File fileDirectory;

  /**
   * Map that stores the file that are transferred.
   */
  private Map fileTransferMap;

  /**
   * Creates a new <code>FileTransferListener</code> with the specified
   * fileDirectory.
   *
   * @param _fileDirectory
   *          directory in which the file is wrote.
   */
  public FileTransferListener(final File _fileDirectory) {
    fileDirectory = _fileDirectory;
    fileTransferMap = new Hashtable();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerAdapter#receive(java.lang.Object)
   */
  public final void receive(final Object _content) {
    FileTransfer fileTransfer = (FileTransfer) _content;
    FileTransferStatus fileTransferStatus;
    OutputStream outputStream;
    try {
      if (!fileTransferMap.containsKey(fileTransfer.getName())) {
        File file = new File(fileDirectory, fileTransfer.getName());
        outputStream = new BufferedOutputStream(new FileOutputStream(file));
        fileTransferStatus = new FileTransferStatus(fileTransfer.getLength(),
            outputStream);
      } else {
        fileTransferStatus = (FileTransferStatus) fileTransferMap
            .get(fileTransfer.getName());
        outputStream = fileTransferStatus.getOutputStream();
      }
      outputStream.write(fileTransfer.getBytes());
      fileTransferStatus.increment(fileTransfer.getBytes().length);

      fileTransferMap.put(fileTransfer.getName(), fileTransferStatus);
      if (fileTransferStatus.transferred()) {
        outputStream.flush();
        outputStream.close();
        String name = fileTransfer.getName();
        File file = new File(fileDirectory, name);
        fileTransferMap.remove(name);
        this.receive(file);
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * Receives the wrote file.
   *
   * @param _file
   *          the wrote file.
   */
  public abstract void receive(final File _file);

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerAdapter#receive(boolean)
   */
  public final void receive(final boolean _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerAdapter#receive(boolean[])
   */
  public final void receive(final boolean[] _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerAdapter#receive(byte)
   */
  public final void receive(final byte _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerAdapter#receive(byte[])
   */
  public final void receive(final byte[] _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerAdapter#receive(char)
   */
  public final void receive(final char _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerAdapter#receive(char[])
   */
  public final void receive(final char[] _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerAdapter#receive(double)
   */
  public final void receive(final double _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerAdapter#receive(double[])
   */
  public final void receive(final double[] _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerAdapter#receive(float)
   */
  public final void receive(final float _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerAdapter#receive(float[])
   */
  public final void receive(final float[] _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerIF#receive(int)
   */
  public final void receive(final int _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerIF#receive(int[])
   */
  public final void receive(final int[] _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerIF#receive(long)
   */
  public final void receive(final long _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerIF#receive(long[])
   */
  public final void receive(final long[] _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerIF#receive(java.lang.Object[])
   */
  public final void receive(final Object[] _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerIF#receive(short)
   */
  public final void receive(final short _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerIF#receive(short[])
   */
  public final void receive(final short[] _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerIF#receive(java.lang.String)
   */
  public final void receive(final String _content) {
  }

  /*
   * (non-Javadoc)
   *
   * @see net.compor.commons.net.ServerListenerIF#receive(java.lang.String[])
   */
  public final void receive(final String[] _content) {
  }

}
