package ca.mcmaster.se2aa4.mazerunner;

public enum Cardinal {
    NORTH, EAST, SOUTH, WEST;

    public Cardinal getRightDirection() {
        return values()[(this.ordinal() + 1) % 4];
    }

    public Cardinal getLeftDirection() {
        return values()[(this.ordinal() + 3) % 4];
    }

    public Cardinal getBackwardDirection() {
        return values()[(this.ordinal() + 2) % 4];
    }
}