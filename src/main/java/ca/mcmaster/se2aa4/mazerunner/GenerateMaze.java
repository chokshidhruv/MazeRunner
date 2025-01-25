package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

public class GenerateMaze{
    private ArrayList<ArrayList<Symbol>> maze = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger();
    
    public ArrayList<ArrayList<Symbol>> generateMaze(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        // Temporary maze structure - for MVP
        // Allows empty line (straight maze) to be added
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                ArrayList<Symbol> emptyRow = new ArrayList<>();
                for (int i = 0; i < maze.get(0).size(); i++) { 
                    emptyRow.add(Symbol.EMPTY);
                }
                maze.add(emptyRow);
                continue;
            }

            ArrayList<Symbol> row = new ArrayList<>();
            for (int idx = 0; idx < line.length(); idx++) {
                row.add(line.charAt(idx) == ' ' ? Symbol.EMPTY : Symbol.WALL);
            }
            maze.add(row);
        }
        reader.close();
        
        //Print maze for debugging
        logger.trace("Generated maze structure:");
        for (ArrayList<Symbol> row : maze) {
            logger.trace(row.toString());
        }

        return maze;
        }
}