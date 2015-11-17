package com.github.xy8864.webGenerator.core;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.17 0017 9:28
 * To change this template use File | Settings | File Templates.
 */
public class GeneratorException extends RuntimeException{
	private static final long serialVersionUID=5359613320197811996L;

	public GeneratorException() {
		super();
	}

	/** Constructs a new runtime exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *
	 * @param   message   the detail message. The detail message is saved for
	 *          later retrieval by the {@link #getMessage()} method.
	 */
	public GeneratorException(String message) {
		super(message);
	}
	public GeneratorException(String format, Object ... args) {
		super(String.format(format,args));
	}
	/**
	 * Constructs a new runtime exception with the specified detail message and
	 * cause.  <p>Note that the detail message associated with
	 * <code>cause</code> is <i>not</i> automatically incorporated in
	 * this runtime exception's detail message.
	 *
	 * @param  message the detail message (which is saved for later retrieval
	 *         by the {@link #getMessage()} method).
	 * @param  cause the cause (which is saved for later retrieval by the
	 *         {@link #getCause()} method).  (A <tt>null</tt> value is
	 *         permitted, and indicates that the cause is nonexistent or
	 *         unknown.)
	 * @since  1.4
	 */
	public GeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	/** Constructs a new runtime exception with the specified cause and a
	 * detail message of <tt>(cause==null ? null : cause.toString())</tt>
	 * (which typically contains the class and detail message of
	 * <tt>cause</tt>).  This constructor is useful for runtime exceptions
	 * that are little more than wrappers for other throwables.
	 *
	 * @param  cause the cause (which is saved for later retrieval by the
	 *         {@link #getCause()} method).  (A <tt>null</tt> value is
	 *         permitted, and indicates that the cause is nonexistent or
	 *         unknown.)
	 * @since  1.4
	 */
	public GeneratorException(Throwable cause) {
		super(cause);
	}
}
