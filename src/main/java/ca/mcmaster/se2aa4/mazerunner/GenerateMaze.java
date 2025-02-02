package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class responsible for generating a maze from a given input file.
 */

public class GenerateMaze{
    private Symbol[][] maze;

    /**
     * Constructor for GenerateMaze.
     * Initializes the maze with the given height and width, and fills it with symbols from the input file.
     * 
     * @param fileName The name of the input file containing the maze.
     * @param height The height of the maze.
     * @param width The width of the maze.
     * @throws IOException If an I/O error occurs while reading the file.
     */

    public GenerateMaze(String fileName, int height, int width) throws IOException {
        maze = new Symbol[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = Symbol.EMPTY; 
            }
        }

        try{

        FileReader reader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        int row = 0;

        while ((line = bufferedReader.readLine()) != null) {
            for (int col = 0; col < line.length(); col++) {
                maze[row][col] = (line.charAt(col) == '#') ? Symbol.WALL : Symbol.EMPTY;
            }
            row++;
        }
        bufferedReader.close();

        } catch (IOException e) {
            throw new IOException("Error reading maze file: " + e.getMessage());
        }
    }
    
    public Symbol[][] getMaze() {
        return maze;
    }
}
