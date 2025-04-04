package ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Paths;

import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Algorithm.MazeSolver;

public class FactorizedPath extends Pathway {
    
    /**
     * Gets the factorized path with spaces.
     * Please note: Spaces are removed when comparing with the input; but for aesthetics, I have left them in the output. 
     *
     * @return The factorized path as a string with spaces.
     */    
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
