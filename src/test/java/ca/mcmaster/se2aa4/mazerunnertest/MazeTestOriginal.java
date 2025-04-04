// package ca.mcmaster.se2aa4.mazerunnertest;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.fail;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;

// import java.io.IOException;

// import ca.mcmaster.se2aa4.mazerunner.Cardinal;
// import ca.mcmaster.se2aa4.mazerunner.ConfigMaze;
// import ca.mcmaster.se2aa4.mazerunner.GenerateMaze;
// import ca.mcmaster.se2aa4.mazerunner.Instruction;
// import ca.mcmaster.se2aa4.mazerunner.Location;
// import ca.mcmaster.se2aa4.mazerunner.MazeLocation;
// import ca.mcmaster.se2aa4.mazerunner.MazeSolver;
// import ca.mcmaster.se2aa4.mazerunner.Path;
// import ca.mcmaster.se2aa4.mazerunner.RightHandAlgorithm;
// import ca.mcmaster.se2aa4.mazerunner.Symbol;


// **********************************************************************//
// PLEASE NOTE: I HAD FORGOTTEN TO COMMIT THESE TESTS TO THE REPO
// THESE WERE THE ORIGINAL TESTS THAT WERE REPLACED BY THE ONES IN THE
// REFACTORING BRANCH. I HAVE LEFT THEM HERE FOR YOUR REFERENCE.
// 
// BASICALLY, I REALIZED WHEN TESTING THAT MY PREVIOUS CODE HAD A LOT OF
// ISSUES AND I HAD TO REFACTOR IT. 
// FOR EXAMPLE, THE ConfigMaze CLASS WAS TAKING IN FIELDS INSIDE THE 
// CONSTRUCTOR THAT MADE THE CODE COMPLICATED AND FAILED TO ADHERE TO 
// SOLID - IT ALSO HAD MANY CODE SMELLS. 
//
// THE TESTS HOWEVER, SHOULD STILL WORK AS INTENDED IN THE PREVIOUS
// VERSION OF THE CODE. 
// *********************************************************************//


// public class MazeTestOriginal {

//     // Test 1
//     @Test
//     @DisplayName("Generate Maze: Valid File")
//     public void testGenerateMazeValidFile() {
//         try {
//             // Use ConfigMaze to determine dimensions from the file
//             ConfigMaze config = new ConfigMaze("examples/small.maz.txt", 0, 0, null);
//             config.setInputFileName(new String[] { "-i", "examples/small.maz.txt" });
//             // Height and width are determined by reading the file
//             // (ConfigMaze sets them via setMazeHeight and setMazeWidth)
//             GenerateMaze maze = new GenerateMaze(
//                     config.getInputFileName(),
//                     config.getMazeHeight(),
//                     config.getMazeWidth()
//             );
//             assertTrue(maze.getMaze().length > 0, "Maze height should be greater than 0.");
//             assertTrue(maze.getMaze()[0].length > 0, "Maze width should be greater than 0.");
//         } catch (IOException e) {
//             fail("Failed to generate maze: " + e.getMessage());
//         }
//     }
//     // Test 2
//     @Test
//     @DisplayName("Maze Location: Find Start Coordinates")
//     public void testMazeLocationFindStartCoordinates() {
//         try {
//             ConfigMaze config = new ConfigMaze("examples/small.maz.txt", 0, 0, null);
//             config.setInputFileName(new String[] { "-i", "examples/small.maz.txt" });
//             GenerateMaze maze = new GenerateMaze(
//                     config.getInputFileName(),
//                     config.getMazeHeight(),
//                     config.getMazeWidth()
//             );
//             MazeLocation location = new MazeLocation(maze.getMaze());
//             int[] start = location.findStart();
//             assertNotNull(start, "Start position should not be null.");
//             // Replace the following expected values with those that match your small.maz.txt file.
//             assertEquals(8, start[0], "Expected start row is 8.");
//             assertEquals(0, start[1], "Expected start column is 0.");
//         } catch (IOException e) {
//             fail("Failed to find start position: " + e.getMessage());
//         }
//     }
//     // Test 3
//     @Test
//     @DisplayName("Maze Location: Find End Coordinates")
//     public void testMazeLocationFindEndCoordinates() {
//         try {
//             ConfigMaze config = new ConfigMaze("examples/small.maz.txt", 0, 0, null);
//             config.setInputFileName(new String[] { "-i", "examples/small.maz.txt" });
//             GenerateMaze maze = new GenerateMaze(
//                     config.getInputFileName(),
//                     config.getMazeHeight(),
//                     config.getMazeWidth()
//             );
//             MazeLocation location = new MazeLocation(maze.getMaze());
//             int[] end = location.findEnd();
//             assertNotNull(end, "End position should not be null.");
//             // Replace these expected values with the actual ones from your small.maz.txt file.
//             assertEquals(5, end[0], "Expected end row is 5.");
//             assertEquals(10, end[1], "Expected end column is 10.");
//         } catch (IOException e) {
//             fail("Failed to find end position: " + e.getMessage());
//         }
//     }
//     // Test 4
//     @Test
//     @DisplayName("Right Hand Algorithm: Solve Maze Path")
//     public void testRightHandAlgorithmSolveMazePath() {
//         try {
//             ConfigMaze config = new ConfigMaze("examples/straight.maz.txt", 0, 0, null);
//             config.setInputFileName(new String[] { "-i", "examples/straight.maz.txt" });
//             GenerateMaze maze = new GenerateMaze(
//                     config.getInputFileName(),
//                     config.getMazeHeight(),
//                     config.getMazeWidth()
//             );
//             MazeLocation location = new MazeLocation(maze.getMaze());
//             RightHandAlgorithm solver = new RightHandAlgorithm(location);
//             // Request canonical path with spaces
//             String path = solver.solveMazePath(true, true);
//             assertNotNull(path, "Path should not be null.");
//             // For a straight maze, if the solution is simply moving forward, the canonical path will be "FFFF"
//             // because the current implementation only adds spaces when direction changes.
//             assertEquals("FFFF", path, "Expected path is 'FFFF'.");
//         } catch (IOException e) {
//             fail("Failed to solve maze path: " + e.getMessage());
//         }
//     }
//     // Test 5
//     @Test
//     @DisplayName("Factorized Path: Solve Path")
//     public void testFactorizedPathSolvePath() {
//         try {
//             ConfigMaze config = new ConfigMaze("examples/tiny.maz.txt", 0, 0, null);
//             config.setInputFileName(new String[] { "-i", "examples/tiny.maz.txt" });
//             GenerateMaze maze = new GenerateMaze(
//                     config.getInputFileName(),
//                     config.getMazeHeight(),
//                     config.getMazeWidth()
//             );
//             MazeLocation location = new MazeLocation(maze.getMaze());
//             RightHandAlgorithm solver = new RightHandAlgorithm(location);
//             // Request factorized path with spaces (isCanonical = false, spaced = true)
//             String result = solver.solveMazePath(false, true);
//             assertNotNull(result, "Factorized path should not be null.");
//             // Expected output based on your tiny.maz.txt layout; adjust the expected string as needed.
//             assertEquals("5F 2L 2F R 2F R 2F 2L 2F R 2F R 3F", result, "Expected factorized path does not match.");
//         } catch (IOException e) {
//             fail("Failed to solve factorized path: " + e.getMessage());
//         }
//     }
//     // Test 6
//     @Test
//     @DisplayName("Canonical Path: Solve Path")
//     public void testCanonicalPathSolvePath() {
//         try {
//             ConfigMaze config = new ConfigMaze("examples/straight.maz.txt", 0, 0, null);
//             config.setInputFileName(new String[] { "-i", "examples/straight.maz.txt" });
//             GenerateMaze maze = new GenerateMaze(
//                     config.getInputFileName(),
//                     config.getMazeHeight(),
//                     config.getMazeWidth()
//             );
//             MazeLocation location = new MazeLocation(maze.getMaze());
//             RightHandAlgorithm solver = new RightHandAlgorithm(location);
//             // Request canonical path with spaces
//             String result = solver.solveMazePath(true, true);
//             assertNotNull(result, "Canonical path should not be null.");
//             // Since the algorithm adds spaces only when direction changes, a uniform "F" sequence remains "FFFF".
//             assertEquals("FFFF", result, "Expected canonical path is 'FFFF'.");
//         } catch (IOException e) {
//             fail("Failed to solve canonical path: " + e.getMessage());
//         }
//     }
//     // Test 7
//     @Test
//     @DisplayName("Solve Maze Command: Simulated")
//     public void testSolveMazeCommandSimulate() {
//         try {
//             ConfigMaze config = new ConfigMaze("examples/tiny.maz.txt", 0, 0, null);
//             config.setInputFileName(new String[] { "-i", "examples/tiny.maz.txt" });
//             GenerateMaze maze = new GenerateMaze(
//                     config.getInputFileName(),
//                     config.getMazeHeight(),
//                     config.getMazeWidth()
//             );
//             MazeLocation location = new MazeLocation(maze.getMaze());
//             RightHandAlgorithm solver = new RightHandAlgorithm(location);
//             // For tiny.maz.txt, expect a factorized path output.
//             String result = solver.solveMazePath(false, true);
//             assertNotNull(result, "Result should not be null.");
//             assertFalse(result.isEmpty(), "Result should not be empty.");
//             assertEquals("5F 2L 2F R 2F R 2F 2L 2F R 2F R 3F", result, "Expected result for simulated solve maze command does not match.");
//         } catch (IOException e) {
//             fail("Failed to execute simulated solve maze command: " + e.getMessage());
//         }
//     }
//     // Test 8
//     @Test
//     @DisplayName("Validate Path Command: Correct Path")
//     public void testValidatePathCommandCorrect() {
//         try {
//             // Simulate a user-provided path
//             String userPath = "FFFF";
//             ConfigMaze config = new ConfigMaze("examples/straight.maz.txt", 0, 0, userPath);
//             config.setInputFileName(new String[] { "-i", "examples/straight.maz.txt", "-p", userPath });
//             GenerateMaze maze = new GenerateMaze(
//                     config.getInputFileName(),
//                     config.getMazeHeight(),
//                     config.getMazeWidth()
//             );
//             MazeLocation location = new MazeLocation(maze.getMaze());
//             RightHandAlgorithm solver = new RightHandAlgorithm(location);
//             // Get the canonical path without spaces for comparison
//             String computed = solver.solveMazePath(true, false);
//             if (userPath.equals(computed) || userPath.equals(solver.solveMazePath(false, false))) {
//                 assertEquals(userPath, computed, "Path validation should succeed.");
//             } else {
//                 fail("Path validation failed: computed path does not match.");
//             }
//         } catch (IOException e) {
//             fail("Failed to validate correct path: " + e.getMessage());
//         }
//     }
//     // Test 9
//     @Test
//     @DisplayName("Validate Path Command: Incorrect Path")
//     public void testValidatePathCommandIncorrect() {
//         try {
//             // Simulate an incorrect user-provided path
//             String userPath = "2F2R";
//             ConfigMaze config = new ConfigMaze("examples/straight.maz.txt", 0, 0, userPath);
//             config.setInputFileName(new String[] { "-i", "examples/straight.maz.txt", "-p", userPath });
//             GenerateMaze maze = new GenerateMaze(
//                     config.getInputFileName(),
//                     config.getMazeHeight(),
//                     config.getMazeWidth()
//             );
//             MazeLocation location = new MazeLocation(maze.getMaze());
//             RightHandAlgorithm solver = new RightHandAlgorithm(location);
//             String computedCanonical = solver.solveMazePath(true, false);
//             String computedFactorized = solver.solveMazePath(false, false);
//             if (userPath.equals(computedCanonical) || userPath.equals(computedFactorized)) {
//                 fail("Path validation should not succeed for an incorrect path.");
//             } else {
//                 assertEquals("incorrect path", "incorrect path", "Validation result should be 'incorrect path'.");
//             }
//         } catch (IOException e) {
//             fail("Failed to validate incorrect path: " + e.getMessage());
//         }
//     }
//     // Test 10
//     @Test
//     @DisplayName("Validate Path Command: Invalid Characters")
//     public void testValidatePathCommandInvalidCharacters() {
//         try {
//             // Simulate a user-provided path with invalid characters
//             String userPath = "F@#R";
//             ConfigMaze config = new ConfigMaze("examples/straight.maz.txt", 0, 0, userPath);
//             config.setInputFileName(new String[] { "-i", "examples/straight.maz.txt", "-p", userPath });
//             GenerateMaze maze = new GenerateMaze(
//                     config.getInputFileName(),
//                     config.getMazeHeight(),
//                     config.getMazeWidth()
//             );
//             MazeLocation location = new MazeLocation(maze.getMaze());
//             RightHandAlgorithm solver = new RightHandAlgorithm(location);
//             String computedCanonical = solver.solveMazePath(true, false);
//             String computedFactorized = solver.solveMazePath(false, false);
//             if (userPath.equals(computedCanonical) || userPath.equals(computedFactorized)) {
//                 fail("Path validation should not succeed for a path with invalid characters.");
//             } else {
//                 assertEquals("incorrect path", "incorrect path", "Invalid characters should yield 'incorrect path'.");
//             }
//         } catch (IOException e) {
//             fail("Failed to validate path with invalid characters: " + e.getMessage());
//         }
//     }
// }
