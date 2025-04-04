package ca.mcmaster.se2aa4.mazerunner.Command_Pattern;


/**
 * Invoker class to set and execute commands related to the maze.
 * This class is responsible for invoking commands that can be executed to perform actions in the maze application.
 * It follows the Command design pattern, allowing for flexible command execution.
 */
public class MazeCommandInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * Executes the command set in the invoker.
     * 
     * @return The result of the command execution in string format.
     * @throws IllegalStateException if no command has been set before execution.
    */

    public String executeCommand() {
        if (command == null) {
            throw new IllegalStateException("Command not set.");
        }
        return command.execute();
    }
}

