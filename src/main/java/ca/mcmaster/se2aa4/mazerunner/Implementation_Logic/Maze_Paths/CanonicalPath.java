package ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Paths;

import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Algorithm.MazeSolver;

public class CanonicalPath extends Pathway {
    
    /**
     * Gets the canonical path with spaces. 
     * Please note: Spaces are removed when comparing with the input; but for aesthetics, I have left them in the output. 
     * 
     * @return The canonical path as a string with spaces.
     */
     @Override
     public String solvePath(MazeSolver solver) {
        String path = solver.solveMazePath();
        if (path == null || path.isEmpty()) { 
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        // Iterate through each character in the path
        for (int i = 0; i < path.length(); i++) {
            // Append a space before each instruction after the first one.
            if (i > 0) {
                result.append(" ");
            }
            result.append(path.charAt(i));
        }
        return result.toString();
    }
}
