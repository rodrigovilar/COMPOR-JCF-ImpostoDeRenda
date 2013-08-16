package net.compor.commons.lang;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Root exception of the exception structure of the COMPOR project. It
 * implements the nested behaviour of the exceptions, exhibiting the traces of
 * the errors related to the COMPOR e the Java API.
 *
 * @author Hyggo Oliveira de Almeida
 * @version 1.0
 */
public abstract class ComporException extends Exception {

  /** The cause of the exception. */
  private Throwable cause = null;

  /**
   * Creates a new exception in the context of the COMPOR project.
   */
  public ComporException() {
    super();
  }

  /**
   * Creates a new exception in the context of the COMPOR project specifying the
   * origin.
   *
   * @param _cause
   *          The cause of the exception.
   */
  public ComporException(final Throwable _cause) {
    super();
    cause = _cause;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Throwable#getCause()
   */
  public Throwable getCause() {
    return cause;
  }



  /*
   * (non-Javadoc)
   *
   * @see java.lang.Throwable#printStackTrace()
   */
  public void printStackTrace() {
    super.printStackTrace();
    this.stackTraceIntoWriter(new PrintWriter(System.err));
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
   */
  public void printStackTrace(final PrintStream _ps) {
    super.printStackTrace(_ps);
    this.stackTraceIntoWriter(new PrintWriter(_ps));
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
   */
  public void printStackTrace(final PrintWriter _pw) {
    super.printStackTrace(_pw);
    this.stackTraceIntoWriter(_pw);
  }

  /**
   * Returns an String version of the stack trace of the exception.
   *
   * @return String
   */
  public String getStackTraceAsString() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    this.printStackTrace(printStream);
    if (cause != null) {
      this.cause.printStackTrace(printStream);
    }
    return outputStream.toString();
  }

  /**
   * Writer an StackTrace in a writer.
   *
   * @param _printWriter
   *          PrintWriter to be written.
   */
  private void stackTraceIntoWriter(final PrintWriter _printWriter) {
    if (cause != null) {
      this.cause.printStackTrace(_printWriter);
    }
  }

  /**
   * Returns the formatted version of the message to be exhibited when the
   * exception occur.
   *
   * @return String Formatted message.
   */
  protected abstract String getFormattedMessage();
}
