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

import java.util.ArrayList;


public class Main {
    
    private static final Logger logger = LogManager.getLogger();
    private static Options options = new Options();
    private static CommandLineParser parser = new DefaultParser();

    public static void main(String[] args) {

        logger.info("** Starting Maze Runner"); //Log the start of the MazeRunner
        options.addOption("i", true, "Display Maze"); //Add -i flag option to display the maze

        GenerateMaze newMaze = new GenerateMaze();
        MazeSolver mazeSolver = new MazeSolver();

        try{
            CommandLine cmd = parser.parse(options, args); //Parse the command line arguments

            if(!cmd.hasOption("i")){ //Check if the -i flag is present
                logger.error("**** No input file specified"); 
                System.exit(1);
            }

            String inputFileName = cmd.getOptionValue("i"); //Get the input file name

            // System.out.println(newMaze.generateMaze(inputFileName));

            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));

            logger.info("**** Reading the maze from file " + inputFileName);

            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.trace("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.trace("PASS ");
                    }
                }
                logger.trace(System.lineSeparator());
            }
            reader.close();
            
            ArrayList<ArrayList<Symbol>> maze = newMaze.generateMaze(inputFileName);
            mazeSolver.setMaze(maze);

            logger.info("**** Computing path");
            mazeSolver.solveMaze();

        } catch(Exception e){
            logger.error("/!\\ An error has occured /!\\");
            logger.error(e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }
        logger.info("** End of MazeRunner");
    }
}
