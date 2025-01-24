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

        public boolean isPath(int row, int col) {
        if (row < 0 || col < 0 || row >= maze.size() || col >= maze.get(row).size()) {
            return false;
        }
        return maze.get(row).get(col) == Symbol.EMPTY;
    }

    public ArrayList<Integer> checkPath(int row, int col){
        ArrayList<Integer> path = new ArrayList<Integer>();
        if (isPath(row, col+1)){ //Check forward direction
            setSequenceOfPath("F");
            path.add(row);
            path.add(col+1);
        }
        else if (isPath(row+1, col)){ //Check right direction
            setSequenceOfPath("R");
            path.add(row+1);
            path.add(col);
        }
        else if (isPath(row-1, col)){ //Check left direction
            setSequenceOfPath("L");
            path.add(row-1);
            path.add(col);
        } else {
            throw new IllegalArgumentException("No path found");
        }

        return path;
    }

    public void solveMaze(){
        ArrayList<Integer> start = findStart();
        ArrayList<Integer> end = findEnd();
        ArrayList<Integer> path = new ArrayList<>();
        int row = start.get(0);
        int col = start.get(1);

        while(row != end.get(0) || col != end.get(1)){
            path = checkPath(row, col);
            logger.info(String.format("Current position: [%d, %d]", row, col));
            row = path.get(0);
            col = path.get(1);
        }

        logger.info("Path found: " + getSequenceOfPath());  
    }

}