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


public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        // System.out.println("** Starting Maze Runner");
        // try {
        //     System.out.println("**** Reading the maze from file " + args[0]);
        //     BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        //     String line;
        //     while ((line = reader.readLine()) != null) {
        //         for (int idx = 0; idx < line.length(); idx++) {
        //             if (line.charAt(idx) == '#') {
        //                 System.out.print("WALL ");
        //             } else if (line.charAt(idx) == ' ') {
        //                 System.out.print("PASS ");
        //             }
        //         }
        //         System.out.print(System.lineSeparator());
        //     }
        // } catch(Exception e) {
        //     System.err.println("/!\\ An error has occured /!\\");
        // }
        // System.out.println("**** Computing path");
        // System.out.println("PATH NOT COMPUTED");
        // System.out.println("** End of MazeRunner");
    
    //Rewriting the main to use logger and apache cLI
        logger.info("** Starting Maze Runner"); //Log the start of the MazeRunner

        Options options = new Options(); 
        options.addOption("i", true, "Display Maze"); //Add -i flag option to display the maze

        CommandLineParser parser = new DefaultParser();
        // String inputFileName;

        try{
            CommandLine cmd = parser.parse(options, args); //Parse the command line arguments

            if(!cmd.hasOption("i")){ //Check if the -i flag is present
                logger.error("**** No input file specified"); 
                // System.exit(1);
            }

            String inputFileName = cmd.getOptionValue("i"); //Get the input file name

            logger.info("**** Reading the maze from file " + inputFileName);
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.info("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.info("PASS ");
                    }
                }
                logger.info(System.lineSeparator());
            }

        } catch(Exception e){
            logger.error("/!\\ An error has occured /!\\");
            // System.exit(1);
        }
        logger.info("**** Computing path");
        logger.error("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
