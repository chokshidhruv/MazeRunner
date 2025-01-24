package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeSolver{

    private static final Logger logger = LogManager.getLogger();
    // public GenerateMaze generateMaze = new GenerateMaze();
    public ArrayList<ArrayList<Symbol>> maze = new ArrayList<>();
    private static String sequenceOfPath = "";

    public void setMaze(ArrayList<ArrayList<Symbol>> maze) {
        this.maze = maze;
    }

    public String getSequenceOfPath(){
        return sequenceOfPath;
    }

    public void setSequenceOfPath(String singlePath){
        sequenceOfPath += singlePath;
    }

    public ArrayList<Integer> findStart(){ //Starts always on the first column
        ArrayList<Integer> start = new ArrayList<Integer>();
        for(int i = 0; i < maze.size(); i++){
            ArrayList<Symbol> row = maze.get(i);
            if(row.get(0) == Symbol.EMPTY){
                //Give the starting value as [i, 0] because it will always start on the first column
                start.add(i);
                start.add(0);
                logger.info(String.format("Starting point found at: [%d, 0]", i));
                return start;
            }
        }
        throw new IllegalArgumentException("No starting point found");
    }

    
    public ArrayList<Integer> findEnd(){ //Ends always on the last column
        ArrayList<Integer> end = new ArrayList<Integer>();
        for(int i = 0; i < maze.size(); i++){
            ArrayList<Symbol> row = maze.get(i);
            if(row.get(row.size() - 1) == Symbol.EMPTY){
                //Give the ending value as [i, row.size() - 1] because it will always end on the last column
                end.add(i);
                end.add(row.size() - 1);
                logger.info(String.format("Ending point found at: [%d, %d]", i, row.size() - 1));
                return end;
            }
        }
        throw new IllegalArgumentException("No ending point found");
    }
}