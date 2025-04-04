package ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Specification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for generating a maze from a given input file.
 */
public class GenerateMaze {
    private Symbol[][] maze;

    /**
     * Constructor for GenerateMaze.
     * Reads the maze file and determines its dimensions encapsulated in type Symbol. 
     * Blank lines are added with spaces to match the size of the maze.
     * 
     * @param fileName The name of the input file containing the maze.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public GenerateMaze(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new IOException("Error reading maze file: " + e.getMessage());
        }

        int height = lines.size();

        int width = 0;
        // Determine the maximum width among all lines
        for (String l : lines) {
            if (l.length() > width) {
                width = l.length();
            }
        }
        maze = new Symbol[height][width];
        for (int i = 0; i < height; i++) {
            // If a line is blank, pad it with spaces to match the width.
            String currentLine = lines.get(i);
            if (currentLine.length() < width) {
                currentLine = String.format("%-" + width + "s", currentLine);
            }
            for (int j = 0; j < width; j++) {
                maze[i][j] = (currentLine.charAt(j) == '#') ? Symbol.WALL : Symbol.EMPTY;
            }
        }
    }

    /**
     * Returns the generated maze as a 2D array of symbols.
     * 
     * @return The maze.
     */
    public Symbol[][] getMaze() {
        return maze;
    }

    /**
     * Returns the height of the maze.
     * 
     * @return The height of the maze.
     */
    public int getHeight() {
        return maze.length;
    }

    /**
     * Returns the width of the maze.
     * 
     * @return The width of the maze.
     */
    public int getWidth() {
        return maze[0].length;
    }
}
