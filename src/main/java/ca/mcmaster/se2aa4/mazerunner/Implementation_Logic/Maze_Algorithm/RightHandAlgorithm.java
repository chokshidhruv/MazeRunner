package ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Algorithm;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Locations.MazeLocation;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Specification.Cardinal;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Specification.Instruction;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Specification.Symbol;
import ca.mcmaster.se2aa4.mazerunner.Observer_Pattern.MazeObserver;
import ca.mcmaster.se2aa4.mazerunner.Observer_Pattern.MazeSubject;

/**
 * Class implementing the right-hand rule algorithm to solve the maze.
 * Implements the MazeSolver interface and MazeSubject (Observer pattern).
 */
public class RightHandAlgorithm implements MazeSolver, MazeSubject {
    private static final Logger logger = LogManager.getLogger(RightHandAlgorithm.class);

    private Cardinal direction;
    private int x, y;
    private Symbol[][] maze;
    private MazeLocation mazeLocation;
    private Instruction instruction = new Instruction();
    private String finalPath = null;

    private final List<MazeObserver> observers = new ArrayList<>();

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
     * @return The path as a string.
     */
    @Override
    public String solveMazePath() {
        if (finalPath != null) {
            logger.info("Returning final path: " + finalPath);
            return finalPath;
        }

        instruction = new Instruction();

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
                notifyObservers("R");
                instruction.setInstruction('F');
                notifyObservers("F");
            } else {
                int[] forwardPos = getNextPosition(direction);
                if (mazeLocation.isValidLocation(forwardPos[0], forwardPos[1])) {
                    x = forwardPos[0];
                    y = forwardPos[1];

                    instruction.setInstruction('F');
                    notifyObservers("F");
                } else {
                    direction = direction.getLeftDirection();
                    instruction.setInstruction('L');
                    notifyObservers("L");
                }
            }
        }

        finalPath = instruction.getInstruction();
        logger.info("Path found: " + finalPath);
        return finalPath;
    }

    @Override
    public void attach(MazeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(MazeObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String latestInstruction) {
        logger.info("Notifying observers of instruction: " + latestInstruction);
        for (MazeObserver obs : observers) {
            obs.update(latestInstruction);
        }
    }

    @Override
    public List<MazeObserver> getObservers() {
        return observers;
    }
}