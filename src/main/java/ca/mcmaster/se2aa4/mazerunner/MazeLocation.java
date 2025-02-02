package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class responsible for managing locations within the maze.
 * Implements the Location interface to find the start and end points and check if a location is valid.
 */

public class MazeLocation implements Location {
    private Symbol[][] maze;
    private int x;
    private int y;
    private static final Logger logger = LogManager.getLogger();

    public MazeLocation(Symbol[][] maze) {
        this.maze = maze;
    }

    /**
     * Finds the starting location in the maze.
     * 
     * @return An array containing the row and column of the starting location.
     * @throws IllegalArgumentException If no starting point is found.
     */

    @Override
    public int[] findStart() throws IllegalArgumentException {
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == Symbol.EMPTY) {
                logger.info(String.format("Starting point found at: [%d, %d]", i, 0));
                return new int[]{i, 0}; // Start is always in the first column
            }
        }
        throw new IllegalArgumentException("No starting point found");
    }

    /**
     * Finds the ending location in the maze.
     * 
     * @return An array containing the row and column of the ending location.
     * @throws IllegalArgumentException If no ending point is found.
     */
    
    @Override
    public int[] findEnd() throws IllegalArgumentException {
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][maze[i].length - 1] == Symbol.EMPTY) {
                logger.info(String.format("Ending point found at: [%d, %d]", i, maze[i].length - 1));
                return new int[]{i, maze[i].length - 1}; // End is always in the last column
            }
        }
        throw new IllegalArgumentException("No ending point found");
    }

    /**
     * Checks if a given location is valid within the maze.
     * 
     * @param row The row of the location to check.
     * @param col The column of the location to check.
     * @return True if the location is valid, false otherwise.
     */

    @Override
    public boolean isValidLocation(int row, int col) {
        return row >= 0 && col >= 0 && row < maze.length && col < maze[row].length && maze[row][col] == Symbol.EMPTY;
    }

}