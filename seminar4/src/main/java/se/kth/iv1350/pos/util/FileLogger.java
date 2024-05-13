package se.kth.iv1350.pos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger {
    private PrintWriter logFile;
    private static final String FILE_NAME = "log.txt";

    public FileLogger() {
        try {
            logFile = new PrintWriter(new FileWriter(FILE_NAME), true);
        } catch (IOException ioe) {
            System.out.println("Could not log.");
            ioe.printStackTrace();
        }
    }

    public void logException(Exception exc){
        logFile.println(exc.getMessage() + "\n");
        exc.printStackTrace(logFile);
    }

    public void logMessage(String msg){
        logFile.println(msg + "\n");
    }
}
