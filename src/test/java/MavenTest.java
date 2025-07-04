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
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class MavenTest {

    // Define the number of threads for the ExecutorService
    private static final int MAX_THREADS = 10;
    // Define the timeout for each test
    private static final Duration TEST_TIMEOUT = Duration.ofSeconds(30);

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
            .sorted()
            .collect(Collectors.toList());

        System.out.println("Discovered problems: " + problems);
        return problems;
    }

    // Call discoverProblemNames once when the class is loaded
    private static final List<String> PROBLEMS = discoverProblemNames();

    @TestFactory
    Collection<DynamicTest> runMavenExecTests() {
        final ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

        List<Future<TestResult>> futures = PROBLEMS.stream()
            .map(problem -> {
                Callable<TestResult> task = () -> {
                    Thread.currentThread().setName("Problem-Runner-" + problem);
                    String command = String.format("mvn exec:exec -Dproblem=%s", problem);
                    System.out.println(Thread.currentThread().getName() + ": Executing command for " + problem
                        + ": " + command);

                    Process process;
                    try {
                        process = Runtime.getRuntime().exec(command);
                    } catch (Exception e) {
                        return new TestResult(
                            problem, false, "", "Failed to execute command: " + e.getMessage(), e);
                    }

                    StringBuilder output = new StringBuilder();
                    StringBuilder errorOutput = new StringBuilder();

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

                    boolean completed;
                    try {
                        completed = process.waitFor(TEST_TIMEOUT.toSeconds(), TimeUnit.SECONDS);
                        outputGobbler.join(100);
                        errorGobbler.join(100);
                    } catch (InterruptedException e) {
                        process.destroyForcibly();
                        outputGobbler.join(100);
                        errorGobbler.join(100);
                        Thread.currentThread().interrupt();
                        return new TestResult(
                            problem, false, output.toString(), "Test interrupted", e);
                    }

                    if (!completed) {
                        process.destroy();
                        if (process.isAlive()) {
                            process.destroyForcibly();
                        }
                        outputGobbler.join(100);
                        errorGobbler.join(100);
                        return new TestResult(
                            problem, false, output.toString(), "Process timed out after " + TEST_TIMEOUT.toSeconds() + " seconds", null);
                    }

                    int exitCode;
                    try {
                        exitCode = process.exitValue();
                    } catch (IllegalThreadStateException e) {
                        process.destroyForcibly();
                        return new TestResult(
                            problem, false, output.toString(), "Process did not terminate properly", e);
                    }

                    boolean success = (exitCode == 0);
                    return new TestResult(problem, success, output.toString(), errorOutput.toString(), null);
                };
                return executor.submit(task);
            })
            .collect(Collectors.toList());

        Collection<DynamicTest> dynamicTests = futures.stream()
            .map(future -> DynamicTest.dynamicTest("Test problem: " + future.toString(), () -> {
                TestResult result = null;
                try {
                    result = future.get();

                    System.out.println("Test " + result.problemName + " completed. Output:\n" + result.output);
                    if (!result.errorOutput.isEmpty()) {
                        System.err.println("Test " + result.problemName + " error output:\n" + result.errorOutput);
                    }

                    Assertions.assertTrue(
                        result.success,
                        "Maven command failed for problem: " + result.problemName + "\nError output:\n"
                            + result.errorOutput);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    Assertions.fail(
                        "Test for problem " + (result != null ? result.problemName : "unknown")
                            + " was interrupted.",
                        e);
                } catch (ExecutionException e) {
                    Throwable cause = e.getCause();
                    Assertions.fail(
                        "An error occurred during execution for problem "
                            + (result != null ? result.problemName : "unknown") + ": " + cause.getMessage(),
                        cause);
                }
            }))
            .collect(Collectors.toList());

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        return dynamicTests;
    }

    private static class TestResult {
        String problemName;
        boolean success;
        String output;
        String errorOutput;
        Throwable exception;

        public TestResult(String problemName, boolean success, String output, String errorOutput, Throwable exception) {
            this.problemName = problemName;
            this.success = success;
            this.output = output;
            this.errorOutput = errorOutput;
            this.exception = exception;
        }

        @Override
        public String toString() {
            return problemName;
        }
    }
}