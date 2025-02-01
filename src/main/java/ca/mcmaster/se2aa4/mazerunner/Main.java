package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

import java.io.IOException;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner **");

        // Parse command-line arguments
        ConfigMaze config = new ConfigMaze(null, 0, 0);
        config.setInputFileName(args);

        try {
            GenerateMaze mazeGenerator = new GenerateMaze(config.getInputFileName(), config.getMazeHeight(), config.getMazeWidth());
            MazeSolver solver = new MazeSolver(mazeGenerator.getMaze());

            logger.info("** Computing Path **");
            solver.solveMaze();
            
        } catch (IOException e) {
            logger.error("Error: " + e.getMessage());
            System.exit(1);
        }

        logger.info("** End of Maze Runner **");
    }
}
