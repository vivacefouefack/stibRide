package g54490.atlg4.stib.exception;

/**
 * <code>RepositoryException</code> is thrown when an resources can't be access.
 *
 * @author 54490@etu.he2b.be
 */
public class ExceptionsClasse extends Exception {

    /**
     * Creates a new instance of <code>RepositoryException</code> without detail
     * message.
     */
    public ExceptionsClasse() {
        super();
    }

    /**
     * Constructs an instance of <code>RepositoryException</code> with the specified
     * detail message.
     *
     * @param msg message of the exception.
     */
    public ExceptionsClasse(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>RepositoryException</code> and wrapped the
     * source exception.
     *
     * @param exception wrapped exception.
     */
    public ExceptionsClasse(Exception exception) {
        super(exception);
    }
}
