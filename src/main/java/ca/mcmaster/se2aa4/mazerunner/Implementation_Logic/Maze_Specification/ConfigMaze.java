package ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Specification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ConfigMaze is a class that reads the input file, the maze height, the maze width, and the string path from the command-line arguments.
 * It also sets the height and width of the maze by reading the input file.
 * THIS CLASS IS RESPONSIBLE FOR READING INPUTS FROM THE USER - THUS, IT HAS MULTIPLE PURPOSES - STILL ADHERING TO SRP. 
 */

public class ConfigMaze {
    private static final Logger logger = LogManager.getLogger();
    private String inputFileName;
    private int mazeHeight;
    private int mazeWidth;
    private String stringPath;

    /**
     * Constructor for ConfigMaze.
     * The constructor receives the input file name, the maze height, the maze width, and the string path.
     * 
     * @param inputFileName The name of the input file passed by the user.
     * @param mazeHeight The height of the maze to be generated.
     * @param mazeWidth The width of the maze to be generated.
     * @param stringPath The string path to be compared with the generated path - inputted by the user to identify if the path is correct.
     */

    public ConfigMaze(String inputFileName, int mazeHeight, int mazeWidth, String stringPath) {
        this.inputFileName = inputFileName;
        this.mazeHeight = mazeHeight;
        this.mazeWidth = mazeWidth;
        this.stringPath = stringPath;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public int getMazeHeight() {
        return mazeHeight;
    }

    public int getMazeWidth() {
        return mazeWidth;
    }

    public String getStringPath() {
        return stringPath;
    }

    /**
     * Sets the height of the maze by reading the input file.
     * 
     * @param fileName The name of the input file.
     */
    
    public void setMazeHeight(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int height = 0;
            while (reader.readLine() != null) {
                height++;
            }
            mazeHeight = height;
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading maze file: " + e.getMessage());
        }
    }

    /**
     * Sets the width of the maze by reading the input file.
     * 
     * @param fileName The name of the input file.
     */

    public void setMazeWidth(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int width = 0;
            while ((line = reader.readLine()) != null) {
                width = line.length();
            }
            mazeWidth = width;
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading maze file: " + e.getMessage());
        }
    }

    /**
     * Parses the command-line arguments to set the user input file name (from -i flag) and the user inputted string path (from -p flag).
     * 
     * @param args The command-line arguments.
     * @throws Exception If an error occurs while parsing the command-line arguments.
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
                System.exit(1);
            }

            inputFileName = cmd.getOptionValue("i");
            if (cmd.hasOption("p")) {
                stringPath = cmd.getOptionValue("p");
            }

            setMazeHeight(inputFileName);
            setMazeWidth(inputFileName);

        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing command-line arguments: " + e.getMessage());
        }
    }
}