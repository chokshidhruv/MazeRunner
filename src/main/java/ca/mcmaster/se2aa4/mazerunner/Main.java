package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class for the Maze Runner application.
 */
public class Main {
    private static final Logger logger = LogManager.getLogger();
    
    public static void main(String[] args) {
        try {
            MazeRunnerApp app = new MazeRunnerApp();
            app.run(args);
        } catch (Exception e) {
            logger.error("Error running the Maze Runner application: " + e.getMessage());
            throw new IllegalArgumentException("Error: " + e.getMessage());
        }
    }
}
