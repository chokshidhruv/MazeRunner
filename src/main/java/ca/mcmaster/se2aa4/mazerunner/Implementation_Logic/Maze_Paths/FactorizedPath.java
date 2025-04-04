package ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Paths;

import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Algorithm.MazeSolver;

public class FactorizedPath extends Pathway {
    /**
     * Gets the canonical path with spaces.
     * 
     * @return The canonical path as a string with spaces.
     */

    // public FactorizedPath(Instruction instruction) {
    //     this.instruction = instruction;
    // }
    
     @Override
     public String solvePath(MazeSolver solver) {
        String path = solver.solveMazePath();
        if (path == null || path.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        int count = 1;
        char current = path.charAt(0);

        // Add the count before each direction that appears more than once
        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == current) {
                count++;
            } else {
                if (count > 1) {
                    result.append(count);
                }
                result.append(current).append(" "); 
                current = path.charAt(i);
                count = 1;
            }
        }
        // Only add the count if the direction appears more than once
        if (count > 1) {
            result.append(count);
        }
        result.append(current);

        return result.toString();
    }
}
