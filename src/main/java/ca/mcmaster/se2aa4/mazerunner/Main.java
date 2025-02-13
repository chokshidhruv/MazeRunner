package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

import java.io.IOException;

/**
 * Main class for the Maze Runner application.
 * Responsible for initializing the maze, solving it, and comparing the solution with the provided path.
 */

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        ConfigMaze config = new ConfigMaze(null, 0, 0, null);
        config.setInputFileName(args);

        try {
            // Generate the maze using ConfigMaze
            GenerateMaze newMaze = new GenerateMaze(config.getInputFileName(), config.getMazeHeight(), config.getMazeWidth());
            MazeLocation loc = new MazeLocation(newMaze.getMaze()); 
            MazeSolver rightHandAlgorithm = new RightHandAlgorithm(loc);

            boolean isCanonical = true;
            boolean spaced = true;

            if (config.getStringPath() != null) {   // Compare the provided path with the computed path if the path is provided
                if (config.getStringPath().equals(rightHandAlgorithm.solveMazePath(isCanonical, !spaced))) {
                    System.out.println("correct path");
                }
                else if (config.getStringPath().equals(rightHandAlgorithm.solveMazePath(!isCanonical, !spaced))) {
                    System.out.println("correct path");
                }
                else {
                    System.out.println("incorrect path");
                }

            } else {    // Compute and print the factorized path if the path is not provided
                String factorizedPath = rightHandAlgorithm.solveMazePath(!isCanonical, spaced);
                System.out.println(factorizedPath);
            }         

        } catch (Exception e) {
            throw new IllegalArgumentException("Error reading maze file: " + e.getMessage());
        }
    }
}
