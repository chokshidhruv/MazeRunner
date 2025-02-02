package ca.mcmaster.se2aa4.mazerunner;

public interface MazeSolver{
    public int[] getNextPosition(Cardinal dir);
    public void solveMazePath();
}