package ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Specification;

/**
 * Enum representing the symbols used in the maze.
 * WALL represents a wall in the maze, and EMPTY represents an empty space.
 */

public enum Symbol{
    WALL('#'), EMPTY(' ');

    private char symbol;

    Symbol(char symbol){
        this.symbol = symbol;
    }

    public String toString(){
        return String.valueOf(symbol);
    }
}