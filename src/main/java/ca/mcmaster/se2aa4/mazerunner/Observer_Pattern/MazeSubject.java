package ca.mcmaster.se2aa4.mazerunner.Observer_Pattern;

import java.util.List;

/**
 * Subject (Observable) in the Observer design pattern for the Maze application.
 */
public interface MazeSubject {
    void attach(MazeObserver observer);
    void detach(MazeObserver observer);
    void notifyObservers(String latestInstruction);
    List<MazeObserver> getObservers();
}