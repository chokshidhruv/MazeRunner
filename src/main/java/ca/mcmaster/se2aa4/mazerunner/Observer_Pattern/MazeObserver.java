package ca.mcmaster.se2aa4.mazerunner.Observer_Pattern;

/**
 * Observer in the Observer design pattern for the Maze application.
 */
public interface MazeObserver {
    /**
     * Called by MazeSubject to notify that a new instruction has been added.
     * @param instruction The latest instruction added (e.g., 'F', 'R', 'L').
     */
    void update(String instruction);
}