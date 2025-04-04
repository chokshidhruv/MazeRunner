package ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Algorithm;

import java.util.List;

import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Specification.Cardinal;
import ca.mcmaster.se2aa4.mazerunner.Observer_Pattern.MazeObserver;

public interface MazeSolver{
    public int[] getNextPosition(Cardinal dir);
    public String solveMazePath();
    public void attach(MazeObserver observer);
    public void detach(MazeObserver observer);
    public void notifyObservers(String latestInstruction);
    public List<MazeObserver> getObservers();
}