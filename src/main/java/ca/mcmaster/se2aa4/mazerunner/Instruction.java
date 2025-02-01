package ca.mcmaster.se2aa4.mazerunner;

public class Instruction {
    private StringBuilder path = new StringBuilder();
    
    public void setInstruction(char instruction) {
        path.append(instruction);
    }

    public String getInstruction() {
        return path.toString();
    }
}
