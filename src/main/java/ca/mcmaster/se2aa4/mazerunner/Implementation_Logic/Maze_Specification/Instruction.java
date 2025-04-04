package ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Specification;

/**
 * Class responsible for storing the path instructions.
 */

public class Instruction {
    private StringBuilder path = new StringBuilder();

    /**
     * Adds an instruction to the path.
     * 
     * @param instruction The instruction to be added to the path.
     */
    
    public void setInstruction(char instruction) {
        path.append(instruction);
    }

    /**
     * Gets the complete path as a string.
     * 
     * @return The complete path as a string.
     */
    
    public String getInstruction() {
        return path.toString();
    }
}
