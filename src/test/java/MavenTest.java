import java.io.BufferedReader;
import java.io.InputStreamReader;
// Import Duration
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.Timeout; // Import Timeout
import org.junit.jupiter.api.parallel.Execution; // Import Execution
import org.junit.jupiter.api.parallel.ExecutionMode; // Import ExecutionMode

// Enable concurrent execution for tests in this class
@Execution(ExecutionMode.CONCURRENT)
public class MavenTest {

    private static final List<String> PROBLEMS = Arrays.asList(
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
            "p195");

    @TestFactory
    @Timeout(value = 3, unit = java.util.concurrent.TimeUnit.SECONDS) // Apply a 3-second timeout to each dynamic test
    Collection<DynamicTest> runMavenExecTests() {
        return PROBLEMS.stream()
                .map(problem -> DynamicTest.dynamicTest("Test problem: " + problem, () -> {
                    // This command needs to directly execute the Java Main class,
                    // NOT "mvn exec:exec" if you want full parallelization
                    // and proper timeout handling by JUnit 5.
                    // The `exec-maven-plugin` creates its own process.

                    // You need to ensure the Main class can be run directly
                    // and can handle input redirection if needed.
                    // Example: /opt/homebrew/Cellar/openjdk/24.0.1/bin/java -cp ...
                    // If you compile your project, the classes will be in target/classes.
                    // String javaCommand = String.format("/opt/homebrew/Cellar/openjdk/24.0.1/bin/java -cp
                    // target/classes com.lzw.solutions.uva.%s.Main < src/main/resources/uva/%s/1.in", problem,
                    // problem);
                    // System.out.println("Executing command: " + javaCommand);

                    // For now, let's stick to your `mvn exec:exec` command, but be aware
                    // it might not be the most efficient for JUnit's parallel execution.
                    // The timeout here will apply to the *entire* 'mvn exec:exec' process.
                    String command = String.format("mvn exec:exec -Dproblem=%s", problem);
                    System.out.println(
                            Thread.currentThread().getName() + ": Executing command for " + problem + ": " + command);

                    Process process;
                    try {
                        process = Runtime.getRuntime().exec(command);
                    } catch (Exception e) {
                        Assertions.fail("Failed to execute command for problem " + problem + ": " + e.getMessage());
                        return; // Exit if process creation fails
                    }

                    // --- Start: Capture output and error streams (with a small buffer size for efficiency) ---
                    // Using try-with-resources for automatic closing of readers
                    StringBuilder output = new StringBuilder();
                    StringBuilder errorOutput = new StringBuilder();

                    // Using separate threads to consume streams to prevent deadlock
                    // if process produces a lot of output on both streams
                    Thread outputGobbler = new Thread(() -> {
                        try (BufferedReader reader =
                                new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                output.append(line).append("\n");
                            }
                        } catch (Exception e) {
                            System.err.println("Error reading output for " + problem + ": " + e.getMessage());
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
                            System.err.println("Error reading error output for " + problem + ": " + e.getMessage());
                        }
                    });

                    outputGobbler.start();
                    errorGobbler.start();

                    int exitCode;
                    try {
                        // Wait for the process to complete or timeout
                        // This timeout is managed by JUnit's @Timeout annotation
                        // and applies to the entire lambda body.
                        exitCode = process.waitFor();
                        outputGobbler.join(); // Ensure all output is consumed
                        errorGobbler.join(); // Ensure all error output is consumed
                    } catch (InterruptedException e) {
                        // This block is executed if the JUnit timeout is triggered
                        process.destroyForcibly(); // Terminate the process if interrupted
                        outputGobbler.join(100); // Give gobblers a moment to finish, but don't wait forever
                        errorGobbler.join(100);
                        System.err.println(Thread.currentThread().getName() + ": Process for " + problem
                                + " was interrupted/timed out. Output:\n" + output.toString() + "\nError:\n"
                                + errorOutput.toString());
                        throw new org.junit.platform.commons.JUnitException(
                                "Test for problem " + problem + " timed out after 3 seconds.", e);
                    } catch (Exception e) {
                        Assertions.fail("Error waiting for process for problem " + problem + ": " + e.getMessage());
                        return;
                    }
                    // --- End: Capture output and error streams ---

                    System.out.println(Thread.currentThread().getName() + ": Command output for " + problem + ":\n"
                            + output.toString());
                    if (errorOutput.length() > 0) {
                        System.err.println(Thread.currentThread().getName() + ": Command error for " + problem + ":\n"
                                + errorOutput.toString());
                    }

                    Assertions.assertEquals(
                            0,
                            exitCode,
                            "Maven command failed for problem: " + problem + "\nError output:\n"
                                    + errorOutput.toString());
                }))
                .collect(Collectors.toList());
    }
}
