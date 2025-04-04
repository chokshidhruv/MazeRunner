package ca.mcmaster.se2aa4.mazerunner.Observer_Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A simple MazeObserver that logs each new instruction received.
 */
public class ObserverLogger implements MazeObserver {
    private static final Logger logger = LogManager.getLogger(ObserverLogger.class);

    @Override
    public void update(String instruction) {
        logger.info("ObserverLogger received instruction: " + instruction);
    }
}