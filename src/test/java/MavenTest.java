import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class MavenTest {

    private static final List<String> PROBLEMS = Arrays.asList(
            // ... (Your existing long list of problems) ...
            "p100",
            "p102",
            "p104",
            "p105",
            "p106",
            "p108",
            "p118",
            "p119",
            "p122",
            "p124",
            "p128",
            "p129",
            "p136",
            "p138",
            "p140",
            "p147",
            "p151",
            "p160",
            "p165",
            "p190",
            "p191",
            "p195",
            "p10000",
            "p10006",
            "p10008",
            "p10009",
            "p10012",
            "p10013",
            "p10018",
            "p10019",
            "p10026",
            "p10033",
            "p10034",
            "p10035",
            "p10036",
            "p10037",
            "p10038",
            "p10041",
            "p10042",
            "p10048",
            "p10056",
            "p10062",
            "p10066",
            "p10067",
            "p10070",
            "p10074",
            "p10077",
            "p10079",
            "p10082",
            "p10099",
            "p10101",
            "p10102",
            "p10104",
            "p10105",
            "p10107",
            "p10114",
            "p10116",
            "p10125",
            "p10127",
            "p10130",
            "p10137",
            "p10139",
            "p10140",
            "p10141",
            "p10142",
            "p10168",
            "p10170",
            "p10179",
            "p10183",
            "p10189",
            "p10190",
            "p10191",
            "p10192",
            "p10193",
            "p10195",
            "p10196",
            "p10198",
            "p10199",
            "p10205",
            "p10209",
            "p10219",
            "p10220",
            "p10221",
            "p10222",
            "p10226",
            "p10229",
            "p10235",
            "p10242",
            "p10245",
            "p10252",
            "p10258",
            "p10260",
            "p10267",
            "p10276",
            "p10281",
            "p10282",
            "p10285",
            "p10286",
            "p10295",
            "p10298",
            "p10299",
            "p10302",
            "p10310",
            "p10323",
            "p10324",
            "p10327",
            "p10334",
            "p10336",
            "p10338",
            "p10340",
            "p10341",
            "p10344",
            "p10346",
            "p10347",
            "p10360",
            "p10369",
            "p10370",
            "p10391",
            "p10394",
            "p10397",
            "p10409",
            "p10424",
            "p10432",
            "p10450",
            "p10452",
            "p10465",
            "p10469",
            "p10473",
            "p10487",
            "p10491",
            "p10496",
            "p10515",
            "p10523",
            "p10530",
            "p10533",
            "p10534",
            "p10550",
            "p10579",
            "p10583",
            "p10591",
            "p10596",
            "p10600",
            "p10603",
            "p10608",
            "p10611",
            "p10616",
            "p10653",
            "p10656",
            "p10664",
            "p10673",
            "p10679",
            "p10684",
            "p10696",
            "p10699",
            "p10703",
            "p10714",
            "p10763",
            "p10773",
            "p10783",
            "p10784",
            "p10789",
            "p10810",
            "p10812",
            "p10905",
            "p10911",
            "p10921",
            "p10922",
            "p10924",
            "p10929",
            "p10931",
            "p10935",
            "p10940",
            "p10943",
            "p10945",
            "p10948",
            "p10954",
            "p10963",
            "p10976",
            "p10986",
            "p11000",
            "p1103",
            "p11054",
            "p11057",
            "p11059",
            "p11060",
            "p11085",
            "p11093",
            "p11094",
            "p11134",
            "p11136",
            "p11137",
            "p11150",
            "p11151",
            "p11152",
            "p11172",
            "p11185",
            "p11192",
            "p11212",
            "p11214",
            "p11219",
            "p11231",
            "p11235",
            "p1124",
            "p11244",
            "p11286",
            "p11292",
            "p11300",
            "p11332",
            "p11340",
            "p11349",
            "p11362",
            "p11364",
            "p11369",
            "p11388",
            "p11389",
            "p11417",
            "p11428",
            "p11450",
            "p11455",
            "p11461",
            "p11462",
            "p11479",
            "p1149",
            "p11491",
            "p11494",
            "p11498",
            "p11503",
            "p11504",
            "p11507",
            "p1152",
            "p11530",
            "p11541",
            "p11547",
            "p11559",
            "p11565",
            "p11572",
            "p11577",
            "p11586",
            "p11608",
            "p11614",
            "p11624",
            "p11631",
            "p11636",
            "p11650",
            "p11661",
            "p11677",
            "p11679",
            "p11689",
            "p11713",
            "p11715",
            "p11716",
            "p11723",
            "p11727",
            "p11729",
            "p11734",
            "p11743",
            "p11764",
            "p11777",
            "p11799",
            "p118",
            "p11805",
            "p11809",
            "p11827",
            "p11831",
            "p11849",
            "p11854",
            "p11875",
            "p11877",
            "p11879",
            "p119",
            "p11900",
            "p11933",
            "p11934",
            "p11936",
            "p11942",
            "p11953",
            "p11984",
            "p11988",
            "p12015",
            "p1203",
            "p12032",
            "p12096",
            "p12100",
            "p12107",
            "p12108",
            "p12113",
            "p12149",
            "p12157",
            "p12174",
            "p122",
            "p12219",
            "p12219_c",
            "p1225",
            "p12250",
            "p12265",
            "p12279",
            "p12289",
            "p1230",
            "p12325",
            "p12333",
            "p1237",
            "p12372",
            "p124",
            "p12403",
            "p12405",
            "p12412",
            "p12455",
            "p12468",
            "p12478",
            "p12502",
            "p12503",
            "p12504",
            "p12545",
            "p12554",
            "p12558",
            "p12569",
            "p12577",
            "p12578",
            "p1260",
            "p12627",
            "p12646",
            "p12657",
            "p128",
            "p129",
            "p1339",
            "p1343",
            "p1354",
            "p136",
            "p1368",
            "p1374",
            "p138",
            "p1395",
            "p140",
            "p1442",
            "p1451",
            "p147",
            "p1471",
            "p151",
            "p1572",
            "p1583",
            "p1584",
            "p1585",
            "p1586",
            "p1587",
            "p1588",
            "p1589",
            "p1590",
            "p1592",
            "p1592j",
            "p1593",
            "p1594",
            "p1595",
            "p1596",
            "p160",
            "p1600",
            "p1602",
            "p1603",
            "p1604",
            "p1605",
            "p1606",
            "p1607",
            "p1608",
            "p1609",
            "p1610",
            "p165",
            "p1673",
            "p190",
            "p191",
            "p195"
            // ... (End of your existing list) ...
            );

    // Define the number of threads for the ExecutorService
    private static final int MAX_THREADS = 10;
    // Define the timeout for each test
    private static final Duration TEST_TIMEOUT = Duration.ofSeconds(3);

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
        // For a @TestFactory, this is a bit tricky as the tests are returned, not run immediately.
        // The safest place to shut down is after collecting all dynamic tests, or in an @AfterAll method.
        // However, @AfterAll needs the ExecutorService to be static.
        // For simple test runs, `shutdownNow()` is often acceptable here.
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
