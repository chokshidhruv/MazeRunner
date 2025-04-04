package ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Specification;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ConfigMaze is a class that reads the input file and the string path from the command-line arguments.
 */
public class ConfigMaze {
    private static final Logger logger = LogManager.getLogger();
    private String inputFileName;
    private String stringPath;

    /**
     * Parses the command-line arguments to set the user input file name (from -i flag) and the user inputted string path (from -p flag).
     * 
     * @param args The command-line arguments.
     * @throws IllegalArgumentException If an error occurs while parsing the command-line arguments.
     */
    public void setInputFileName(String[] args) {
        Options options = new Options();
        options.addOption("i", "input", true, "Maze text file");
        options.addOption("p", "path", true, "String path");
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
            if (!cmd.hasOption("i")) {
                logger.error("**** No input file specified");
                throw new IllegalArgumentException("No input file specified.");
            }

            inputFileName = cmd.getOptionValue("i");
            if (cmd.hasOption("p")) {
                stringPath = cmd.getOptionValue("p");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing command-line arguments: " + e.getMessage());
        }
    }

    /**
     * Returns the input file name.
     * 
     * @return The input file name.
     */
    public String getInputFileName() {
        return inputFileName;
    }

    /**
     * Returns the string path provided by the user.
     * 
     * @return The string path.
     */
    public String getStringPath() {
        return stringPath;
    }
}