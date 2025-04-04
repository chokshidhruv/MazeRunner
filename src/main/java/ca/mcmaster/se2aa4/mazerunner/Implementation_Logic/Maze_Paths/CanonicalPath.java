package ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Paths;

import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Algorithm.MazeSolver;

public class CanonicalPath extends Pathway {
    /**
     * Gets the canonical path with spaces.
     * 
     * @return The canonical path as a string with spaces.
     */

    // public CanonicalPath(Instruction instruction) {
    //     this.instruction = instruction;
    // }
    
     @Override
     public String solvePath(MazeSolver solver) {
        String path = solver.solveMazePath();
        if (path == null || path.isEmpty()) { 
            return "";
        }
        StringBuilder result = new StringBuilder();
        char current = path.charAt(0);

        result.append(current);

        // Add a space before each new direction by checking if the current character is different from the previous one
        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == current) {
                result.append(path.charAt(i));
            } else {
                result.append(" ").append(path.charAt(i));
                current = path.charAt(i);
            }
        }

        return result.toString();
    }
}
