package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Command_Pattern.MazeCommandInvoker;
import ca.mcmaster.se2aa4.mazerunner.Command_Pattern.SolveMazeCommand;
import ca.mcmaster.se2aa4.mazerunner.Command_Pattern.ValidatePathCommand;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Algorithm.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Algorithm.RightHandAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Locations.MazeLocation;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Paths.FactorizedPath;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Specification.ConfigMaze;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Specification.GenerateMaze;
import ca.mcmaster.se2aa4.mazerunner.Observer_Pattern.MazeSubject;
import ca.mcmaster.se2aa4.mazerunner.Observer_Pattern.ObserverLogger;

/**
 * Runs the logic of the Maze Runner application using the Command Pattern and Observer Pattern.
 * The class initializes the maze, solves it, and compares the solution with the provided path - to be invoked by the Main class.
 */
public class MazeRunnerApp {
    private static final Logger logger = LogManager.getLogger();

    /**
     * run method to run the Maze Runner application.
     * 
     * @param args Command line arguments for the maze configuration.
     */
    public void run(String[] args) {
        ConfigMaze config = initializeConfig(args);
        MazeSolver solver = initializeMazeSolver(config);

        attachObserver(solver);

        if (config.getStringPath() == null) {
            solveMaze(solver);
        } else {
            validatePath(config, solver);
        }
    }

    /**
     * Initializes the configuration for the maze using command line arguments.
     * 
     * @param args
     * @return ConfigMaze object with the maze configuration.
     */
    private ConfigMaze initializeConfig(String[] args) {
        ConfigMaze config = new ConfigMaze(null, 0, 0, null);
        config.setInputFileName(args);
        return config;
    }

    /**
     * Initializes the maze solver using the configuration.
     * 
     * @param config The configuration for the maze.
     * @return MazeSolver object to solve the maze.
     */
    private MazeSolver initializeMazeSolver(ConfigMaze config) {
        try {
            GenerateMaze newMaze = new GenerateMaze(config.getInputFileName(), config.getMazeHeight(), config.getMazeWidth());
            MazeLocation loc = new MazeLocation(newMaze.getMaze());
            return new RightHandAlgorithm(loc);
        } catch (IOException e) {
            logger.error("Error initializing maze solver: " + e.getMessage(), e);
            throw new RuntimeException("Failed to initialize maze solver", e);
        }
    }

    /**
     * Attaches an observer to the maze solver.
     * This is simply to show how the Observer Pattern can be used with the RightHandAlgorithm. 
     * Loggers are to be turned off in the final version of the code, however, they are useful for debugging.
     * 
     * @param solver The maze solver to attach the observer to.
     */
    private void attachObserver(MazeSolver solver) {
        if (solver instanceof MazeSubject subject) {
            ObserverLogger observerLogger = new ObserverLogger();
            subject.attach(observerLogger);
            logger.info("ObserverLogger attached to RightHandAlgorithm.");
        }
    }

    /**
     * Solves the maze using the maze solver and logs the result.
     * 
     * @param solver The maze solver to use.
     */
    private void solveMaze(MazeSolver solver) {
        MazeCommandInvoker invoker = new MazeCommandInvoker();
        invoker.setCommand(new SolveMazeCommand(new FactorizedPath(), solver));
        String solverPath = invoker.executeCommand();
        logger.info("Factorized Path: " + solverPath);
        System.out.println("Command Pattern Solve Path: " + solverPath);
    }

    /**
     * Validates the user-provided path using the maze solver and logs the result.
     * 
     * @param config The configuration for the maze.
     * @param solver The maze solver to use for validation.
     */
    private void validatePath(ConfigMaze config, MazeSolver solver) {
        MazeCommandInvoker invoker = new MazeCommandInvoker();
        String normalizedUserPath = config.getStringPath();
        invoker.setCommand(new ValidatePathCommand(normalizedUserPath, solver));
        String validationResult = invoker.executeCommand();
        logger.info("Validation Result: " + validationResult);
        System.out.println("Command Pattern Validation: " + normalizedUserPath + " is " + validationResult);
    }
}