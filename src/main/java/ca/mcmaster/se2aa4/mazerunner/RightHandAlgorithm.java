package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class implementing the right-hand rule algorithm to solve the maze.
 * Implements the MazeSolver interface.
 */

public class RightHandAlgorithm implements MazeSolver {
    private static final Logger logger = LogManager.getLogger(RightHandAlgorithm.class);
    private Cardinal direction;
    private int x, y;
    private Symbol[][] maze;
    private MazeLocation mazeLocation;
    private Instruction instruction = new Instruction();

    public RightHandAlgorithm(MazeLocation mazeLocation) { 
        this.mazeLocation = mazeLocation;
    }

    /**
     * Gets the next position based on the current direction.
     * 
     * @param dir The current direction.
     * @return An array containing the row and column of the next position.
     * @throws IllegalStateException If the direction is invalid.
     */

    @Override
    public int[] getNextPosition(Cardinal dir) {
        // All pathways are provided as Forward directions respective to the current direction. For example if the current direction is EAST, the forward direction will be towards EAST.
        if (dir == Cardinal.NORTH) {
            return new int[]{x - 1, y};
        } else if (dir == Cardinal.EAST) {
            return new int[]{x, y + 1};
        } else if (dir == Cardinal.SOUTH) {
            return new int[]{x + 1, y};
        } else if (dir == Cardinal.WEST) {
            return new int[]{x, y - 1};
        } else {
            throw new IllegalStateException("Invalid direction");
        }
    }

    /**
     * Solves the maze using the right-hand rule algorithm and returns the path.
     * 
     * @param isCanonical Whether to return the canonical path.
     * @param spaced Whether to include spaces in the path.
     * @return The path as a string based on the provided parameters.
     */

    @Override
    public String solveMazePath(boolean isCanonical, boolean spaced) {
        instruction = new Instruction();
        Path path = new Path(instruction);

        int[] end = mazeLocation.findEnd();
        int[] start = mazeLocation.findStart();

        x = start[0];
        y = start[1];
        direction = Cardinal.EAST;

        while (x != end[0] || y != end[1]) {
            Cardinal rightDirection = direction.getRightDirection();
            int[] rightPos = getNextPosition(rightDirection);
            // If the right position is valid, turn right and move forward respective to the current direction.
            if (mazeLocation.isValidLocation(rightPos[0], rightPos[1])) {
                direction = rightDirection;
                x = rightPos[0]; 
                y = rightPos[1];

                instruction.setInstruction('R');
                instruction.setInstruction('F');
            } else {
                int[] forwardPos = getNextPosition(direction);
                // If the forward position is valid, move forward respective to the current direction.
                if (mazeLocation.isValidLocation(forwardPos[0], forwardPos[1])) {
                    x = forwardPos[0];
                    y = forwardPos[1];

                    instruction.setInstruction('F');
                } else {
                    // If the forward position is not valid, turn left respective to the current direction.
                    direction = direction.getLeftDirection();
                    instruction.setInstruction('L');
                }
            }
        }
           
        if (isCanonical && spaced) {
            return path.getCanonicalPath();
        }else if (!isCanonical && spaced) {    
            return path.getFactorizedPath();
        }
        else if (isCanonical && !spaced) {
            return path.getCanonicalPathWithoutSpaces();
        }
        else {
            return path.getFactorizedPathWithoutSpaces();
        }
    }
}