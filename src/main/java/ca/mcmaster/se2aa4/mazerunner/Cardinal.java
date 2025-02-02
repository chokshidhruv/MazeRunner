package ca.mcmaster.se2aa4.mazerunner;

/**
 * Enum representing the four cardinal directions: NORTH, EAST, SOUTH, and WEST.
 * Basically what each direction is, and what the next direction is.
 * If the direction is NORTH, and the user wants the right direction, it will add 1 to the ordinal of NORTH (preset int value given by Java), and then mod it by 4. This will give the next direction, which is EAST.
 * Likewise, if the direction is NORTH, and the user wants the left direction, it will add 3 to the ordinal of NORTH, and then mod it by 4. This will give the previous direction, which is WEST.
 */
public enum Cardinal {
    NORTH, EAST, SOUTH, WEST; 

    /**
     * Gets the direction to the right of the current direction.
     * 
     * @return The direction to the right. Values() is a method that returns an array of the values in the enum given by Java. Then using the ordinal, it gives the int value of the array.
     */

    public Cardinal getRightDirection() {
        return values()[(this.ordinal() + 1) % 4]; 
    }

    /**
     * Gets the direction to the left of the current direction.
     * 
     * @return The direction to the left. Values() is a method that returns an array of the values in the enum given by Java.
     */

    public Cardinal getLeftDirection() {
        return values()[(this.ordinal() + 3) % 4]; 
    }
}