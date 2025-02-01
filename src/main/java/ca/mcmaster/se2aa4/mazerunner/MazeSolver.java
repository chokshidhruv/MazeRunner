package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeSolver {
    private static final Logger logger = LogManager.getLogger();
    private Symbol[][] maze;

    //Instead of having numerous methods inside this class that did not pertain to the class, I removed them and split it into two new classes
    //MazeLocation and Instruction
    //MazeLocation contains the methods that pertain to the location of the maze
    //Instruction contains the methods that pertain to the instructions of the maze

    // TODO: Implement the Right hand rule - but, turn this into an interface and have RightHandAlgorithm implement it
    // This will allow us to have multiple algorithms that can solve the maze for future use. 

    public MazeSolver(Symbol[][] maze) {
        this.maze = maze;
    }

    public int[] checkPath(int row, int col) {
        if (isPath(row, col + 1)) { // Check forward
            // Move right
            return new int[]{row, col + 1};
        } else if (isPath(row + 1, col)) { // Check downward
            // Move down
            return new int[]{row + 1, col};
        } else if (isPath(row - 1, col)) { // Check upward
            // Move up
            return new int[]{row - 1, col};
        } else {
            throw new IllegalArgumentException("No path found");
        }
    }

    public void solveMaze() {
        int[] start = findStart();
        int[] end = findEnd();
        int row = start[0];
        int col = start[1];

        while (row != end[0] || col != end[1]) {
            int[] next = checkPath(row, col);
            logger.trace(String.format("Current position: [%d, %d]", row, col));
            row = next[0];
            col = next[1];
        }
    }
}