package ca.mcmaster.se2aa4.mazerunner;

/**
 * Class responsible for managing and formatting the path instructions.
 */

public class Path {
    private final Instruction instruction;

    public Path(Instruction instruction) {
        this.instruction = instruction;
    }

    /**
     * Gets the canonical path with spaces.
     * 
     * @return The canonical path as a string with spaces.
     */

    public String getCanonicalPath() {
        String path = instruction.getInstruction();
        if (path == null || path.isEmpty()) { 
            return "";
        }
        StringBuilder result = new StringBuilder();
        char current = path.charAt(0);

        result.append(current);

        // Add a space before each new direction by checking if the current character is different from the previous one
        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == current) {
                result.append(path.charAt(i));
            } else {
                result.append(" ").append(path.charAt(i));
                current = path.charAt(i);
            }
        }

        return result.toString();
    }

    /**
     * Gets the factorized path with spaces.
     * 
     * @return The factorized path as a string with spaces.
     */

    public String getFactorizedPath() {
        String path = instruction.getInstruction();
        if (path == null || path.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        int count = 1;
        char current = path.charAt(0);

        // Add the count before each direction that appears more than once
        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == current) {
                count++;
            } else {
                if (count > 1) {
                    result.append(count);
                }
                result.append(current).append(" "); 
                current = path.charAt(i);
                count = 1;
            }
        }
        // Only add the count if the direction appears more than once
        if (count > 1) {
            result.append(count);
        }
        result.append(current);

        return result.toString();
    }

    /**
     * Gets the canonical path without spaces.
     * 
     * @return The canonical path as a string without spaces.
     */

    public String getCanonicalPathWithoutSpaces() {
        return getCanonicalPath().replace(" ", "");
    }

    /**
     * Gets the factorized path without spaces.
     * 
     * @return The factorized path as a string without spaces.
     */

    public String getFactorizedPathWithoutSpaces() {
        return getFactorizedPath().replace(" ", "");
    }
}
