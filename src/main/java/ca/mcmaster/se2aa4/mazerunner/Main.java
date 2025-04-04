package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Command_Pattern.MazeCommandInvoker;
import ca.mcmaster.se2aa4.mazerunner.Command_Pattern.SolveMazeCommand;
import ca.mcmaster.se2aa4.mazerunner.Command_Pattern.ValidatePathCommand;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Algorithm.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Algorithm.RightHandAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Locations.MazeLocation;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Paths.FactorizedPath;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Paths.Pathway;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Specification.ConfigMaze;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Specification.GenerateMaze;
import ca.mcmaster.se2aa4.mazerunner.Observer_Pattern.MazeSubject;
import ca.mcmaster.se2aa4.mazerunner.Observer_Pattern.ObserverLogger;

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


            // If you need to attach an observer:
            if (rightHandAlgorithm instanceof MazeSubject) {
                MazeSubject subject = (MazeSubject) rightHandAlgorithm;
                ObserverLogger observerLogger = new ObserverLogger();
                subject.attach(observerLogger);
                logger.info("ObserverLogger attached to RightHandAlgorithm.");
            }

            // Solve the maze and get the raw path
            String rawPath = rightHandAlgorithm.solveMazePath();
            logger.info("Raw Path: " + rawPath);

            // Example usage of the Command Pattern
            MazeCommandInvoker invoker = new MazeCommandInvoker();

            // Solve command
            Pathway factorizedPath = new FactorizedPath();
            invoker.setCommand(new SolveMazeCommand(factorizedPath, rightHandAlgorithm));
            String solverPath = invoker.executeCommand();
            logger.info("Factorized Path: " + solverPath);
            System.out.println("Command Pattern Solve Path: " + solverPath);

            // Validate command
            if (config.getStringPath() != null) {
                String normalizedUserPath = config.getStringPath();
                invoker.setCommand(new ValidatePathCommand(normalizedUserPath, rightHandAlgorithm));
                String validationResult = invoker.executeCommand();
                logger.info("Validation Result: " + validationResult);
                System.out.println("Command Pattern Validation: " + normalizedUserPath + " is " + validationResult);
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Error reading maze file: " + e.getMessage());
        }
    }
}
