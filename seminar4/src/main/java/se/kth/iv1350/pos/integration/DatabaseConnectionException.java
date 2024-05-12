package se.kth.iv1350.pos.integration;

public class DatabaseConnectionException extends RuntimeException {
    
    public DatabaseConnectionException(){
        super("Unable to connect to the database.");
    }
}
