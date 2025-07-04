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
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern; // Import Pattern
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class MavenTest {

    // Define the number of threads for the ExecutorService
    private static final int MAX_THREADS = 10;
    // Define the timeout for each test
    private static final Duration TEST_TIMEOUT = Duration.ofSeconds(300);

    // Regex pattern to match "p" followed by only digits
    private static final Pattern PROBLEM_DIR_PATTERN = Pattern.compile("p\\d+");

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

            // Handle JAR paths correctly. If it's in a JAR, resource.toURI() will be "jar:file:/..."
            // We need to resolve the actual file system path if running from an unzipped structure,
            // or return an empty list if it's purely in a JAR and cannot be listed as a directory.
            if ("jar".equals(resource.getProtocol())) {
                System.err.println(
                        "Cannot discover problems dynamically from within a JAR file. Please ensure 'src/main/java' is accessible on the file system during testing.");
                // In a real scenario, you might have a pre-defined list for JAR runs,
                // or expect tests to be run against expanded directories.
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

        // List directories within the uvaSolutionsPath that start with 'p'
        File uvaDir = uvaSolutionsPath.toFile();
        File[] problemDirs = uvaDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                // Ensure it's a directory AND matches the "p" + digits pattern
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
                .sorted() // Optional: Sort for consistent order
                .collect(Collectors.toList());

        System.out.println("Discovered problems: " + problems);
        return problems;
    }

    // Call discoverProblemNames once when the class is loaded
    private static final List<String> PROBLEMS = discoverProblemNames();

    @TestFactory
    Collection<DynamicTest> runMavenExecTests() {
        // Create a fixed-size thread pool
        final ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

        // A list to hold the Futures of each submitted task
        List<Future<TestResult>> futures = PROBLEMS.stream()
                .map(problem -> {
                    // Create a Callable for each problem
                    Callable<TestResult> task = () -> {
                        Thread.currentThread().setName("Problem-Runner-" + problem); // Name thread for better logging
                        String command = String.format("mvn exec:exec -Dproblem=%s", problem);
                        System.out.println(Thread.currentThread().getName() + ": Executing command for " + problem
                                + ": " + command);

                        Process process;
                        try {
                            process = Runtime.getRuntime().exec(command);
                        } catch (Exception e) {
                            // If process execution itself fails
                            return new TestResult(
                                    problem, false, "", "Failed to execute command: " + e.getMessage(), e);
                        }

                        StringBuilder output = new StringBuilder();
                        StringBuilder errorOutput = new StringBuilder();

                        // Use separate threads to consume streams to prevent deadlock
                        Thread outputGobbler = new Thread(() -> {
                            try (BufferedReader reader =
                                    new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    output.append(line).append("\n");
                                }
                            } catch (Exception e) {
                                System.err.println(Thread.currentThread().getName() + ": Error reading output for "
                                        + problem + ": " + e.getMessage());
                            }
                        });

                        Thread errorGobbler = new Thread(() -> {
                            try (BufferedReader errorReader =
                                    new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                                String line;
                                while ((line = errorReader.readLine()) != null) {
                                    errorOutput.append(line).append("\n");
                                }
                            } catch (Exception e) {
                                System.err.println(Thread.currentThread().getName()
                                        + ": Error reading error output for " + problem + ": " + e.getMessage());
                            }
                        });

                        outputGobbler.start();
                        errorGobbler.start();

                        int exitCode;
                        try {
                            exitCode = process.waitFor();
                            outputGobbler.join(); // Ensure all output is consumed
                            errorGobbler.join(); // Ensure all error output is consumed
                        } catch (InterruptedException e) {
                            process.destroyForcibly(); // Ensure subprocess is terminated if interrupted
                            outputGobbler.join(100); // Give gobblers a moment, but don't hang
                            errorGobbler.join(100);
                            Thread.currentThread().interrupt(); // Restore interrupted status
                            return new TestResult(
                                    problem, false, output.toString(), "Test interrupted (likely timed out)", e);
                        } catch (Exception e) {
                            return new TestResult(
                                    problem,
                                    false,
                                    output.toString(),
                                    "Error waiting for process: " + e.getMessage(),
                                    e);
                        }

                        boolean success = (exitCode == 0);
                        return new TestResult(problem, success, output.toString(), errorOutput.toString(), null);
                    };
                    return executor.submit(task); // Submit the task to the executor
                })
                .collect(Collectors.toList());

        // Create DynamicTests to check the results of the submitted tasks
        Collection<DynamicTest> dynamicTests = futures.stream()
                .map(future -> DynamicTest.dynamicTest("Test problem: " + future.toString(), () -> {
                    TestResult result = null;
                    try {
                        // Wait for each task to complete with the defined timeout
                        result = future.get(TEST_TIMEOUT.toSeconds(), TimeUnit.SECONDS);

                        System.out.println("Test " + result.problemName + " completed. Output:\n" + result.output);
                        if (!result.errorOutput.isEmpty()) {
                            System.err.println("Test " + result.problemName + " error output:\n" + result.errorOutput);
                        }

                        Assertions.assertTrue(
                                result.success,
                                "Maven command failed for problem: " + result.problemName + "\nError output:\n"
                                        + result.errorOutput);

                    } catch (TimeoutException e) {
                        // This handles the case where the Callable itself exceeds the timeout
                        future.cancel(true); // Attempt to interrupt the running task
                        Assertions.fail(
                                "Test for problem " + (result != null ? result.problemName : "unknown")
                                        + " timed out after " + TEST_TIMEOUT.toSeconds() + " seconds.",
                                e);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore interrupt status
                        Assertions.fail(
                                "Test for problem " + (result != null ? result.problemName : "unknown")
                                        + " was interrupted.",
                                e);
                    } catch (ExecutionException e) {
                        // The actual exception thrown by the Callable is wrapped here
                        Throwable cause = e.getCause();
                        Assertions.fail(
                                "An error occurred during execution for problem "
                                        + (result != null ? result.problemName : "unknown") + ": " + cause.getMessage(),
                                cause);
                    }
                }))
                .collect(Collectors.toList());

        // Crucial: Shut down the executor after all tests have been processed
        executor.shutdown();
        try {
            // Wait for existing tasks to terminate
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Forcefully terminate if not done in time
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt(); // Restore interrupt status
        }

        return dynamicTests;
    }

    // A simple record/class to encapsulate test results
    private static class TestResult {
        String problemName;
        boolean success;
        String output;
        String errorOutput;
        Throwable exception; // To store any exception from the callable

        // Primary constructor
        public TestResult(String problemName, boolean success, String output, String errorOutput, Throwable exception) {
            this.problemName = problemName;
            this.success = success;
            this.output = output;
            this.errorOutput = errorOutput;
            this.exception = exception;
        }

        @Override
        public String toString() {
            // Used by DynamicTest.dynamicTest() to name the test
            return problemName;
        }
    }
}
