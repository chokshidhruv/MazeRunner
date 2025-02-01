package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;

import java.io.IOException;

public class ConfigMaze {
    private static final Logger logger = LogManager.getLogger();
    private String inputFileName;
    private int mazeHeight;
    private int mazeWidth;

    public ConfigMaze(String inputFileName, int mazeHeight, int mazeWidth) {
        this.inputFileName = inputFileName;
        this.mazeHeight = mazeHeight;
        this.mazeWidth = mazeWidth;
    }

    public String getInputFileName() {
        return this.inputFileName;
    }

    public int getMazeHeight() {
        return this.mazeHeight;
    }

    public int getMazeWidth() {
        return this.mazeWidth;
    }

    public void setMazeHeight(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int height = 0;
            while ((line = reader.readLine()) != null) {
                height++;
            }
            reader.close();
            mazeHeight = height;
        } catch (IOException e) {
            logger.error("**** Error reading maze file: " + e.getMessage());
        }
    }

    public void setMazeWidth(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int width = 0;
            while ((line = reader.readLine()) != null) {
                width = line.length();
            }
            reader.close();
            mazeWidth = width;
        } catch (IOException e) {
            logger.error("**** Error reading maze file: " + e.getMessage());
        }
    }


    public void setInputFileName(String[] args) {
        Options options = new Options();
        options.addOption("i", "input", true, "Maze text file");
        
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if(!cmd.hasOption("i")){ //Check if the -i flag is present
                logger.error("**** No input file specified"); 
                System.exit(1);
            }

            inputFileName = cmd.getOptionValue("i");

            setMazeHeight(inputFileName);
            setMazeWidth(inputFileName);
            
        } catch (Exception e) {
            logger.error("**** Error reading maze file: " + e.getMessage());
        }
    }

}