package ca.mcmaster.se2aa4.mazerunner.Command_Pattern;

import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Algorithm.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Paths.Pathway;

/**
 * Command to solve the maze and return the computed path.
 */
public class SolveMazeCommand implements Command {
    private final Pathway solvePath;
    private final MazeSolver mazeSolver;

    public SolveMazeCommand(Pathway solvePath, MazeSolver mazeSolver) {
        this.solvePath = solvePath;
        this.mazeSolver = mazeSolver;
    }

    @Override
    public String execute() {
        // Solve the maze using the provided solver
        return solvePath.solvePath(mazeSolver);
    }
}