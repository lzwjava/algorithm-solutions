import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class MavenTest {

    private static final List<String> PROBLEMS =
            Arrays.asList("p138", "p140"); // Add more problems here as needed: "p141", "p152"

    @TestFactory
    Collection<DynamicTest> runMavenExecTests() {
        return PROBLEMS.stream()
                .map(problem -> DynamicTest.dynamicTest("Test problem: " + problem, () -> {
                    String command = String.format("mvn exec:exec -Dproblem=%s", problem);
                    System.out.println("Executing command: " + command);

                    Process process = Runtime.getRuntime().exec(command);

                    // Capture output and error streams
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                    StringBuilder output = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        output.append(line).append("\n");
                    }
                    reader.close();

                    StringBuilder errorOutput = new StringBuilder();
                    while ((line = errorReader.readLine()) != null) {
                        errorOutput.append(line).append("\n");
                    }
                    errorReader.close();

                    int exitCode = process.waitFor();

                    System.out.println("Command output for " + problem + ":\n" + output.toString());
                    if (errorOutput.length() > 0) {
                        System.err.println("Command error for " + problem + ":\n" + errorOutput.toString());
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
