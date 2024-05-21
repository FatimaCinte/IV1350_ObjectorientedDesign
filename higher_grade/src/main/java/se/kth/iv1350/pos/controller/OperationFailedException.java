package se.kth.iv1350.pos.controller;

/**
 * Represents an exception that is thrown when an operation in the system fails.
 */
public class OperationFailedException extends Exception {

    /**
     * Creates a new instance representing the condition described in the specified message,
     * and includes the underlying cause of the exception.
     *
     * @param msg   The message describing the reason for the exception.
     * @param cause The underlying exception that caused this exception to be thrown.
     */
    public OperationFailedException(String msg, Exception cause){
        super(msg, cause);
    }
}
