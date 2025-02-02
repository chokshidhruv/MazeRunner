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
    public String solveMazePath(boolean isCanonical) {
        int[] end = mazeLocation.findEnd();
        int[] start = mazeLocation.findStart();

        x = start[0];
        y = start[1];
        direction = Cardinal.EAST;

        while (x != end[0] || y != end[1]) {
            Cardinal rightDirection = direction.getRightDirection();
            int[] rightPos = getNextPosition(rightDirection);

            if (mazeLocation.isValidLocation(rightPos[0], rightPos[1])) {
                direction = rightDirection;
                x = rightPos[0]; 
                y = rightPos[1];

                instruction.setInstruction('R');
                instruction.setInstruction('F');
            } else {
                int[] forwardPos = getNextPosition(direction);
                if (mazeLocation.isValidLocation(forwardPos[0], forwardPos[1])) {
                    x = forwardPos[0];
                    y = forwardPos[1];
                    instruction.setInstruction('F');
                } else {
                    direction = direction.getLeftDirection();
                    instruction.setInstruction('L');
                }
            }

            logger.info(String.format("Current position: [%d, %d], Facing: %s", x, y, direction));
        }

        if (isCanonical) {
            Path path = new Path(instruction);
            return path.getCanonicalPath();
        } else {
            Path path = new Path(instruction);
            return path.getFactorizedPath();
        }
    }
}