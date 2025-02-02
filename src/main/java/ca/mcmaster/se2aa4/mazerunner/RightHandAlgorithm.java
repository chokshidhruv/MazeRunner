package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandAlgorithm implements MazeSolver {
    private static final Logger logger = LogManager.getLogger();
    private Cardinal direction;
    private int x, y;
    private Symbol[][] maze;
    private MazeLocation mazeLocation;
    private Instruction instruction = new Instruction();

    public RightHandAlgorithm(MazeLocation mazeLocation) { 
        this.mazeLocation = mazeLocation;
    }

    @Override
    public int[] getNextPosition(Cardinal dir) {
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


    @Override
    public void solveMazePath() {
        int[] end = mazeLocation.findEnd();
        int[] start = mazeLocation.findStart();

        // What this method does is, it will first check the right-hand side of the maze. To implement the RHR, we need to check if the right hand side is empty or not -  and if it is empty, we will move to the right. If it is not empty, we will check if the front is empty or not. If the front is empty, we will move forward. If the front is not empty, we will move to the left.
        // The Cardinal Directions are used because Right, Left, Back and Forward are relative to the direction one facing.
        // For example, if one is facing North, then the right-hand side is East, the left-hand side is West, the back is South and the front is North.
        // Thus, to go right, one must move forward in the east direction. Thus, all the directions are relative forward to the direction one is facing.

        x = start[0];
        y = start[1];
        // East is chosen because the exit is always on the right-hand side of the maze and the enterance is always on the left-hand side of the maze - as the assignment specified it enterance could be left or right side - I will assume it is always on the left side.
        direction = Cardinal.EAST;

        while (x != end[0] || y != end[1]) {
            Cardinal rightDirection = direction.getRightDirection();
            int[] rightPos = getNextPosition(rightDirection);

            if (mazeLocation.isValidLocation(rightPos[0], rightPos[1])) {
                direction = rightDirection;
                x = rightPos[0]; 
                y = rightPos[1];
                // If the right-hand side is empty, move right (but moving right is actually moving forward in the right direction). Thus, add R and F to the instruction pathway. 
                instruction.setInstruction('R');
                instruction.setInstruction('F');
            } else {
                int[] forwardPos = getNextPosition(direction);
                if (mazeLocation.isValidLocation(forwardPos[0], forwardPos[1])) {
                    x = forwardPos[0];
                    y = forwardPos[1];
                    // All next positions are all reletive to the forward direction. 
                    instruction.setInstruction('F');
                } else {
                    direction = direction.getLeftDirection();
                    // If stuck, turn left. 
                    instruction.setInstruction('L');
                }
            }

            logger.info(String.format("Current position: [%d, %d], Facing: %s", x, y, direction));
            logger.info("Path found: " + instruction.getInstruction()); 
        }
    }
}