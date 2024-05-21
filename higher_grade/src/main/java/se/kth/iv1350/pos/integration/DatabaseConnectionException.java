package se.kth.iv1350.pos.integration;

/**
 * Represents an exception that is thrown when there is a failure to connect to the database.
 */
public class DatabaseConnectionException extends RuntimeException {
    
    /**
     * Creates a new instance with the exception message.
     */
    public DatabaseConnectionException(){
        super("Unable to connect to the database.");
    }
}
