package ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Locations;

public interface Location{
    public int[] findStart();
    public int[] findEnd();
    public boolean isValidLocation(int row, int col);
}
