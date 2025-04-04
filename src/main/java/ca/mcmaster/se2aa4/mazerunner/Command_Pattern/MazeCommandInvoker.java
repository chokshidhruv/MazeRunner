package ca.mcmaster.se2aa4.mazerunner.Command_Pattern;


/**
 * Invoker class to set and execute commands related to the maze.
 */
public class MazeCommandInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public String executeCommand() {
        if (command == null) {
            throw new IllegalStateException("Command not set.");
        }
        return command.execute();
    }
}

