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
        ConfigMaze config = new ConfigMaze(null, 0, 0, null);
        config.setInputFileName(args);

        try {
            // Generate the maze using ConfigMaze
            GenerateMaze newMaze = new GenerateMaze(config.getInputFileName(), config.getMazeHeight(), config.getMazeWidth());
            
            newMaze.printMaze();
            
            MazeLocation loc = new MazeLocation(newMaze.getMaze()); 
            MazeSolver rightHandAlgorithm = new RightHandAlgorithm(loc);

            logger.info("**** Reading the maze from file: " + config.getInputFileName());

            logger.info("**** Path: " + config.getStringPath());

            boolean isCanonical = true;

            if (config.getStringPath() != null) {
                if (config.getStringPath() == rightHandAlgorithm.solveMazePath(isCanonical) || config.getStringPath() == rightHandAlgorithm.solveMazePath(!isCanonical)) {
                    System.out.println("Path is correct");
                }
                else {
                    System.out.println("Path is incorrect");
                }

            } else {
                logger.info("**** Computing path");
                String factorizedPath = rightHandAlgorithm.solveMazePath(false);
                System.out.println(factorizedPath);
            }         

        } catch (Exception e) {
            logger.error("/!\\ An error has occurred /!\\");
            logger.error("PATH NOT COMPUTED: " + e.getMessage());
            System.exit(1);
        }

        logger.info("** End of Maze Runner **");
    }
}
