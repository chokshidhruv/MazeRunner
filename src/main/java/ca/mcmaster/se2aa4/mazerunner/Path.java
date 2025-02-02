package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    private final Instruction instruction;

    public Path(Instruction instruction) {
        this.instruction = instruction;
    }

    public String getCanonicalPath() {
        String path = instruction.getInstruction();
        if (path == null || path.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        char current = path.charAt(0);

        result.append(current);

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

    public String getFactorizedPath() {
        String path = instruction.getInstruction();
        if (path == null || path.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        int count = 1;
        char current = path.charAt(0);

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
        if (count > 1) {
            result.append(count);
        }
        result.append(current);

        return result.toString().trim();
    }
}
