import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class UvaTestRunner {

    // Standard input for all tests (customize per problem if needed)
    private static final String TEST_INPUT = "5\n1 2 3 4 5\n";
    private static final String EXPECTED_OUTPUT = "15\n";

    @Test
    void testAllUvaSolutions() throws Exception {
        List<Class<?>> solutionClasses = discoverUvaSolutionClasses();
        assertFalse(solutionClasses.isEmpty(), "No UVA solution classes found");

        for (Class<?> clazz : solutionClasses) {
            testSolutionClass(clazz);
        }
    }

    private List<Class<?>> discoverUvaSolutionClasses() throws Exception {
        String basePackage = "com.lzw.solutions.uva";
        Path classLocation = Paths.get("target/classes", basePackage.replace('.', '/'));

        return Files.walk(classLocation)
                .filter(p -> p.toString().endsWith("Main.class"))
                .map(p -> toClassName(p, classLocation, basePackage))
                .map(this::loadClass)
                .filter(Objects::nonNull)
                .filter(this::hasMainMethod)
                .collect(Collectors.toList());
    }

    private String toClassName(Path path, Path basePath, String basePackage) {
        return basePackage + "."
                + basePath.relativize(path)
                        .toString()
                        .replace(".class", "")
                        .replace(FileSystems.getDefault().getSeparator(), ".");
    }

    private Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private boolean hasMainMethod(Class<?> clazz) {
        try {
            Method main = clazz.getMethod("main", String[].class);
            return main != null;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private void testSolutionClass(Class<?> clazz) {
        String className = clazz.getSimpleName();
        System.out.println("Testing: " + clazz.getName());

        // Redirect I/O
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            System.setIn(new ByteArrayInputStream(TEST_INPUT.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // Invoke main method
            Method main = clazz.getMethod("main", String[].class);
            main.invoke(null, (Object) new String[0]);

            // Verify output
            String actualOutput = outContent.toString();
            assertTrue(
                    actualOutput.contains(EXPECTED_OUTPUT),
                    "Failed for " + className + "\nExpected: " + EXPECTED_OUTPUT + "\nActual: " + actualOutput);

        } catch (Exception e) {
            fail("Exception in " + className + ": " + e.getMessage());
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
