import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class MavenTest {

    // Define the timeout for each test
    private static final Duration TEST_TIMEOUT = Duration.ofSeconds(3);

    // Regex pattern to match "p" followed by only digits
    private static final Pattern PROBLEM_DIR_PATTERN = Pattern.compile("p\\d+");

    // Define a skip list for problems to exclude
    private static final Set<String> SKIP_PROBLEMS = new HashSet<>(Arrays.asList(
            "p10009" // Add problems to skip here
            ));

    // Method to dynamically discover problem names
    private static List<String> discoverProblemNames() {
        String basePackagePath = "com/lzw/solutions/uva/";
        Path uvaSolutionsPath = null;

        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource(basePackagePath);
            if (resource == null) {
                System.err.println("Could not find resource path: " + basePackagePath);
                return Collections.emptyList();
            }

            if ("jar".equals(resource.getProtocol())) {
                System.err.println(
                        "Cannot discover problems dynamically from within a JAR file. Please ensure 'src/main/java' is accessible on the file system during testing.");
                return Collections.emptyList();
            }

            uvaSolutionsPath = Paths.get(resource.toURI());

        } catch (URISyntaxException e) {
            System.err.println("Error converting resource URL to URI: " + e.getMessage());
            return Collections.emptyList();
        }

        if (uvaSolutionsPath == null || !Files.exists(uvaSolutionsPath)) {
            System.err.println("UVA Solutions directory not found or not a directory: " + uvaSolutionsPath);
            return Collections.emptyList();
        }

        File uvaDir = uvaSolutionsPath.toFile();
        File[] problemDirs = uvaDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory()
                        && PROBLEM_DIR_PATTERN.matcher(name).matches();
            }
        });

        if (problemDirs == null || problemDirs.length == 0) {
            System.err.println("No problem directories found under: " + uvaSolutionsPath);
            return Collections.emptyList();
        }

        List<String> problems = Arrays.stream(problemDirs)
                .map(File::getName)
                .filter(name -> !SKIP_PROBLEMS.contains(name)) // Filter out skipped problems
                .sorted()
                .collect(Collectors.toList());

        // Read maxproblems system property
        String maxProblemsStr = System.getProperty("maxproblems");
        if (maxProblemsStr != null) {
            try {
                int maxProblems = Integer.parseInt(maxProblemsStr);
                if (maxProblems > 0 && maxProblems < problems.size()) {
                    problems = problems.subList(0, maxProblems);
                    System.out.println("Limiting to " + maxProblems + " problems: " + problems);
                } else if (maxProblems <= 0) {
                    System.err.println("Invalid maxproblems value: " + maxProblems + ". Using all problems.");
                }
                // If maxProblems >= problems.size(), use all problems
            } catch (NumberFormatException e) {
                System.err.println("Invalid maxproblems format: " + maxProblemsStr + ". Using all problems.");
            }
        }

        System.out.println("Discovered problems (after skipping): " + problems);
        return problems;
    }

    // Call discoverProblemNames once when the class is loaded
    private static final List<String> PROBLEMS = discoverProblemNames();

    @TestFactory
    Collection<DynamicTest> runMavenExecTests() {
        return PROBLEMS.stream()
                .map(problem -> DynamicTest.dynamicTest("Test problem: " + problem, () -> {
                    String command = String.format("mvn exec:exec -Dproblem=%s", problem);
                    System.out.println("Executing command for " + problem + ": " + command);

                    Process process;
                    try {
                        process = Runtime.getRuntime().exec(command);
                    } catch (Exception e) {
                        Assertions.fail("Failed to execute command for " + problem + ": " + e.getMessage(), e);
                        return;
                    }

                    StringBuilder output = new StringBuilder();
                    StringBuilder errorOutput = new StringBuilder();

                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            output.append(line).append("\n");
                        }
                    } catch (Exception e) {
                        System.err.println("Error reading output for " + problem + ": " + e.getMessage());
                    }

                    try (BufferedReader errorReader =
                            new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                        String line;
                        while ((line = errorReader.readLine()) != null) {
                            errorOutput.append(line).append("\n");
                        }
                    } catch (Exception e) {
                        System.err.println("Error reading error output for " + problem + ": " + e.getMessage());
                    }

                    boolean completed;
                    try {
                        completed = process.waitFor(TEST_TIMEOUT.toSeconds(), TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        process.destroyForcibly();
                        Thread.currentThread().interrupt();
                        Assertions.fail("Test for problem " + problem + " was interrupted.", e);
                        return;
                    }

                    if (!completed) {
                        process.destroy();
                        if (process.isAlive()) {
                            process.destroyForcibly();
                        }
                        Assertions.fail(
                                "Process timed out for " + problem + " after " + TEST_TIMEOUT.toSeconds() + " seconds");
                        return;
                    }

                    int exitCode;
                    try {
                        exitCode = process.exitValue();
                    } catch (IllegalThreadStateException e) {
                        process.destroyForcibly();
                        Assertions.fail("Process did not terminate properly for " + problem, e);
                        return;
                    }

                    System.out.println("Test " + problem + " completed. Output:\n" + output);
                    if (!errorOutput.isEmpty()) {
                        System.err.println("Test " + problem + " error output:\n" + errorOutput);
                    }

                    Assertions.assertTrue(
                            exitCode == 0,
                            "Maven command failed for problem: " + problem + "\nError output:\n" + errorOutput);
                }))
                .collect(Collectors.toList());
    }
}
