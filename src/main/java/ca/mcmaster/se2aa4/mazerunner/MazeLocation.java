package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeLocation implements Location {
    private Symbol[][] maze;
    private int x;
    private int y;
    private static final Logger logger = LogManager.getLogger();

    public MazeLocation(Symbol[][] maze) {
        this.maze = maze;
    }

    @Override
    public int[] findStart() {
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == Symbol.EMPTY) {
                logger.info(String.format("Starting point found at: [%d, %d]", i, 0));
                return new int[]{i, 0}; // Start is always in the first column
            }
        }
        throw new IllegalArgumentException("No starting point found");
    }
    
    @Override
    public int[] findEnd() {
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][maze[i].length - 1] == Symbol.EMPTY) {
                logger.info(String.format("Ending point found at: [%d, %d]", i, maze[i].length - 1));
                return new int[]{i, maze[i].length - 1}; // End is always in the last column
            }
        }
        throw new IllegalArgumentException("No ending point found");
    }

    @Override
    public boolean isValidLocation(int row, int col) {
        return row >= 0 && col >= 0 && row < maze.length && col < maze[row].length && maze[row][col] == Symbol.EMPTY;
    }

}