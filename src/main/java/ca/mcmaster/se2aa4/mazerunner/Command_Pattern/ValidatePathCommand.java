package ca.mcmaster.se2aa4.mazerunner.Command_Pattern;

import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Algorithm.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Paths.CanonicalPath;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Paths.FactorizedPath;

/**
 * Command to compare a provided path with both the canonical and factorized solver paths.
 */
public class ValidatePathCommand implements Command {
    private final String providedPath;
    private final MazeSolver mazeSolver;

    public ValidatePathCommand(String providedPath, MazeSolver mazeSolver) {
        this.providedPath = providedPath;
        this.mazeSolver = mazeSolver;
    }

    @Override
    public String execute() {
        // Solve the maze to get the raw path
        String rawPath = mazeSolver.solveMazePath();

        // Generate canonical and factorized paths
        CanonicalPath canonicalPath = new CanonicalPath();
        FactorizedPath factorizedPath = new FactorizedPath();

        // Get canonical and factorized solutions without spaces
        String canonicalSolution = canonicalPath.solvePath(mazeSolver).replace(" ", "");
        String factorizedSolution = factorizedPath.solvePath(mazeSolver).replace(" ", "");

        // Normalize the provided path by removing spaces
        String normalizedProvidedPath = providedPath.replace(" ", "");

        // Compare the normalized provided path against both formats
        if (normalizedProvidedPath.equals(canonicalSolution) || normalizedProvidedPath.equals(factorizedSolution)) {
            return "correct path";
        } else {
            return "incorrect path";
        }
    }
}
