package com.github.xy8864.webGenerator.base;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.8.26 0026 15:25
 * To change this template use File | Settings | File Templates.
 */
public class ServiceException extends RuntimeException{
	private static final long serialVersionUID=8570790517318678710L;
	/** Constructs a new runtime exception with <code>null</code> as its
	 * detail message.  The cause is not initialized, and may subsequently be
	 * initialized by a call to {@link #initCause}.
	 */
	public ServiceException() {
		super();
	}

	/** Constructs a new runtime exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *
	 * @param   message   the detail message. The detail message is saved for
	 *          later retrieval by the {@link #getMessage()} method.
	 */
	public ServiceException(String message) {
		super(message);
	}
	public ServiceException(String format, Object... args) {
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
	public ServiceException(String message, Throwable cause) {
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
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
