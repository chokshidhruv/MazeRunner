package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

public class GenerateMaze{
    private ArrayList<ArrayList<Symbol>> maze = new ArrayList<>();
     
    public ArrayList<ArrayList<Symbol>> generateMaze(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while((line = reader.readLine()) != null){
            // If you think of array like this: 
            // [[]
            //  []
            //  []]
            // This basically adds a new row (ie. ArrayList Object) to the ArrayList 
            maze.add(new ArrayList<Symbol>()); 
            for (int idx = 0; idx <= line.length() - 1; idx++){
                maze.get(maze.size() - 1).add(line.charAt(idx) == ' ' ? Symbol.EMPTY : Symbol.WALL); // Iterates through the newly created Array (ie. the last array) and adds character symbol from txt file
            }
        }

        // Copy of the maze
        ArrayList<ArrayList<Symbol>> arrayMaze = new ArrayList<>();
        for (ArrayList<Symbol> row : maze) {
            arrayMaze.add(new ArrayList<>(row));
        }

        return arrayMaze; 
    }
    

}