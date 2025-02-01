package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeSolver {
    private static final Logger logger = LogManager.getLogger();
    private Symbol[][] maze;
    private StringBuilder sequenceOfPath = new StringBuilder();

    public MazeSolver(Symbol[][] maze) {
        this.maze = maze;
    }

    public String getSequenceOfPath() {
        return sequenceOfPath.toString();
    }

    public void setSequenceOfPath(String singlePath) {
        sequenceOfPath.append(singlePath);
    }

    public int[] findStart() {
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == Symbol.EMPTY) {
                logger.info(String.format("Starting point found at: [%d, 0]", i));
                return new int[]{i, 0}; // Start is always in the first column
            }
        }
        throw new IllegalArgumentException("No starting point found");
    }

    public int[] findEnd() {
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][maze[i].length - 1] == Symbol.EMPTY) {
                logger.info(String.format("Ending point found at: [%d, %d]", i, maze[i].length - 1));
                return new int[]{i, maze[i].length - 1}; // End is always in the last column
            }
        }
        throw new IllegalArgumentException("No ending point found");
    }

    public boolean isPath(int row, int col) {
        return row >= 0 && col >= 0 && row < maze.length && col < maze[row].length && maze[row][col] == Symbol.EMPTY;
    }

    public int[] checkPath(int row, int col) {
        if (isPath(row, col + 1)) { // Check forward
            setSequenceOfPath("F");
            return new int[]{row, col + 1};
        } else if (isPath(row + 1, col)) { // Check downward
            setSequenceOfPath("D");
            return new int[]{row + 1, col};
        } else if (isPath(row - 1, col)) { // Check upward
            setSequenceOfPath("U");
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

        System.out.println("Path found: " + getSequenceOfPath());
    }
}
