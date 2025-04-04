package ca.mcmaster.se2aa4.mazerunnertest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Command_Pattern.MazeCommandInvoker;
import ca.mcmaster.se2aa4.mazerunner.Command_Pattern.SolveMazeCommand;
import ca.mcmaster.se2aa4.mazerunner.Command_Pattern.ValidatePathCommand;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Algorithm.RightHandAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Locations.MazeLocation;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Paths.CanonicalPath;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Paths.FactorizedPath;
import ca.mcmaster.se2aa4.mazerunner.Implementation_Logic.Maze_Specification.GenerateMaze;

public class MazeTestAfterRefactor {
    // Test 1
    @Test
    @DisplayName("Generate Maze: Valid File")
    public void testGenerateMazeValidFile() {
        try {
            GenerateMaze maze = new GenerateMaze("examples/small.maz.txt");
            assertTrue(maze.getHeight() > 0, "Maze height should be greater than 0.");
            assertTrue(maze.getWidth() > 0, "Maze width should be greater than 0.");
        } catch (IOException e) {
            fail("Failed to generate maze: " + e.getMessage());
        }
    }
    // Test 2
    @Test
    @DisplayName("Maze Location: Find Start Coordinates")
    public void testMazeLocationFindStartCoordinates() {
        try {
            GenerateMaze maze = new GenerateMaze("examples/small.maz.txt");
            MazeLocation location = new MazeLocation(maze.getMaze());

            int[] start = location.findStart();

            assertNotNull(start, "Start position should not be null.");
            assertEquals(8, start[0], "Start row should be 0."); 
            assertEquals(0, start[1], "Start column should be 1.");
        } catch (IOException e) {
            fail("Failed to find start position: " + e.getMessage());
        }
    }
    // Test 3
    @Test
    @DisplayName("Maze Location: Find End Coordinates")
    public void testMazeLocationFindEndCoordinates() {
        try {
            GenerateMaze maze = new GenerateMaze("examples/small.maz.txt");
            MazeLocation location = new MazeLocation(maze.getMaze());

            int[] end = location.findEnd();

            assertNotNull(end, "End position should not be null.");
            assertEquals(5, end[0], "End row should be 4.");
            assertEquals(10, end[1], "End column should be 5."); 
        } catch (IOException e) {
            fail("Failed to find end position: " + e.getMessage());
        }
    }
    // Test 4
    @Test
    @DisplayName("Right Hand Algorithm: Solve Maze Path")
    public void testRightHandAlgorithmSolveMazePath() {
        try {
            GenerateMaze maze = new GenerateMaze("examples/straight.maz.txt");
            MazeLocation location = new MazeLocation(maze.getMaze());
            RightHandAlgorithm algorithm = new RightHandAlgorithm(location);
            String path = algorithm.solveMazePath();

            assertNotNull(path, "Path should not be null.");
            assertEquals("FFFF", path, "Path should be 'FFFF'.");
        } catch (IOException e) {
            fail("Failed to solve maze path: " + e.getMessage());
        }
    }
    // Test 5
    @Test
    @DisplayName("Factorized Path: Solve Path")
    public void testFactorizedPathSolvePath() {
        try {
            GenerateMaze maze = new GenerateMaze("examples/tiny.maz.txt");
            MazeLocation location = new MazeLocation(maze.getMaze());
            RightHandAlgorithm solver = new RightHandAlgorithm(location);
            FactorizedPath factorizedPath = new FactorizedPath();
            String result = factorizedPath.solvePath(solver);

            assertNotNull(result, "Factorized path should not be null.");
            assertEquals("5F 2L 2F R 2F R 2F 2L 2F R 2F R 3F", result, "Factorized path should be '4F'.");
        } catch (IOException e) {
            fail("Failed to solve factorized path: " + e.getMessage());
        }
    }
    // Test 6
    @Test
    @DisplayName("Canonical Path: Solve Path")
    public void testCanonicalPathSolvePath() {
        try {
            GenerateMaze maze = new GenerateMaze("examples/straight.maz.txt");
            MazeLocation location = new MazeLocation(maze.getMaze());
            RightHandAlgorithm solver = new RightHandAlgorithm(location);
            CanonicalPath canonicalPath = new CanonicalPath(); 

            String result = canonicalPath.solvePath(solver);

            assertNotNull(result, "Canonical path should not be null.");
            assertEquals("F F F F", result, "Canonical path should be 'F F F F'.");
        } catch (IOException e) {
            fail("Failed to solve canonical path: " + e.getMessage());
        }
    }
    // Test 7
    @Test
    @DisplayName("Maze Command Invoker: Solve Maze Command")
    public void testMazeCommandInvokerSolveMazeCommand() {
        try {
            GenerateMaze maze = new GenerateMaze("examples/tiny.maz.txt");
            MazeLocation location = new MazeLocation(maze.getMaze());
            RightHandAlgorithm solver = new RightHandAlgorithm(location);

            MazeCommandInvoker invoker = new MazeCommandInvoker();
            invoker.setCommand(new SolveMazeCommand(new FactorizedPath(), solver));
            String result = invoker.executeCommand();

            assertNotNull(result, "Command result should not be null.");
            assertFalse(result.isEmpty(), "Command result should not be empty.");
            assertEquals("5F 2L 2F R 2F R 2F 2L 2F R 2F R 3F", result, "Command result should match the expected output.");
        } catch (IOException e) {
            fail("Failed to execute solve maze command: " + e.getMessage());
        }
    }
    // Test 8
    @Test
    @DisplayName("Maze Command Invoker: Validate Path Command (Correct Path)")
    public void testMazeCommandInvokerValidatePathCorrect() {
        try {
            GenerateMaze maze = new GenerateMaze("examples/straight.maz.txt");
            MazeLocation location = new MazeLocation(maze.getMaze());
            RightHandAlgorithm solver = new RightHandAlgorithm(location);

            MazeCommandInvoker invoker = new MazeCommandInvoker();
            invoker.setCommand(new ValidatePathCommand("FFFF", solver));
            String result = invoker.executeCommand();

            assertEquals("correct path", result, "Path validation should return 'correct path'.");
        } catch (IOException e) {
            fail("Failed to validate correct path: " + e.getMessage());
        }
    }
    // Test 9
    @Test
    @DisplayName("Maze Command Invoker: Validate Path Command (Incorrect Path)")
    public void testMazeCommandInvokerValidatePathIncorrect() {
        try {
            GenerateMaze maze = new GenerateMaze("examples/straight.maz.txt");
            MazeLocation location = new MazeLocation(maze.getMaze());
            RightHandAlgorithm solver = new RightHandAlgorithm(location);

            MazeCommandInvoker invoker = new MazeCommandInvoker();
            invoker.setCommand(new ValidatePathCommand("2F 2R", solver));
            String result = invoker.executeCommand();

            assertEquals("incorrect path", result, "Path validation should return 'incorrect path'.");
        } catch (IOException e) {
            fail("Failed to validate incorrect path: " + e.getMessage());
        }
    }
    // Test 10
    @Test
    @DisplayName("Maze Command Invoker: Validate Path Command (Invalid Characters)")
    public void testMazeCommandInvokerValidatePathInvalidCharacters() {
        try {
            GenerateMaze maze = new GenerateMaze("examples/straight.maz.txt");
            MazeLocation location = new MazeLocation(maze.getMaze());
            RightHandAlgorithm solver = new RightHandAlgorithm(location);

            MazeCommandInvoker invoker = new MazeCommandInvoker();
            invoker.setCommand(new ValidatePathCommand("F@#R", solver));
            String result = invoker.executeCommand();

            assertEquals("incorrect path", result, "Path with invalid characters should return 'incorrect path'.");
        } catch (IOException e) {
            fail("Failed to validate path with invalid characters: " + e.getMessage());
        }
    }
}
