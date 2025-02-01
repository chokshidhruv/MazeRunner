package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GenerateMaze{
    private Symbol[][] maze;

    public GenerateMaze(String fileName, int height, int width) throws IOException {
        maze = new Symbol[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = Symbol.EMPTY; 
            }
        }

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
    }

    public Symbol[][] getMaze() {
        return maze;
    }

    public void printMaze() {
        for (Symbol[] row : maze) {
            for (Symbol cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
