import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileHandlerTest {

    private ByteArrayOutputStream systemOutContent;

    @Test
    public void linesOutTest() throws IOException {
        systemOutContent = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(systemOutContent);
        System.setOut(ps);

        FileHandler fileHandler = new FileHandler("file.txt","test");
        fileHandler.writeOutLines();

        List<String> printedLines = systemOutContent.toString().lines().collect(Collectors.toList());

        List<String> expectedLines = Files.readAllLines(Paths.get("file.txt"));
        assertEquals(expectedLines, printedLines);
    }

    @Test
    public void concatToEachLineTest() throws IOException {
        systemOutContent = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(systemOutContent);
        System.setOut(ps);

        FileHandler fileHandler = new FileHandler("file.txt","test");
        fileHandler.concatToEachLine("test");

        List<String> printedLines = systemOutContent.toString().lines().collect(Collectors.toList());

        List<String> expectedLines = Files.readAllLines(Paths.get("file.txt"));

        expectedLines.replaceAll(s -> "test" + s);

        assertEquals(expectedLines, printedLines);
    }
    @Test
    public void fileOutputTester() throws IOException {
        FileHandler fileHandler = new FileHandler("file.txt","test");
        fileHandler.writeToFile();
        List<String> printedLines = Files.readAllLines(Paths.get("output.txt"));

        List<String> expectedLines = Files.readAllLines(Paths.get("file.txt"));

        expectedLines.replaceAll(s -> "test" + s);

        assertEquals(expectedLines, printedLines);
    }

    @Test
    public void longerThan5Tester() throws IOException {
        FileHandler fileHandler = new FileHandler("file.txt","test");
        fileHandler.onlyLongerThan5();
        List<String> printedLines = Files.readAllLines(Paths.get("output.txt"));
        List<String> expectedLines = Files.readAllLines(Paths.get("file.txt"));
        expectedLines.replaceAll(s -> "test" + s);
        assertEquals(printedLines,expectedLines.stream().filter(line -> line.length() > 5).collect(Collectors.toList()));
    }

    @Test
    public void skip3Test() throws IOException {
        FileHandler fileHandler = new FileHandler("file.txt","test");
        fileHandler.skip3();
        List<String> printedLines = Files.readAllLines(Paths.get("output.txt"));
        List<String> expectedLines = Files.readAllLines(Paths.get("file.txt"));
        expectedLines.replaceAll(s -> "test" + s);
        assertEquals(printedLines,expectedLines.stream().filter(line -> line.length() > 5).skip(3).collect(Collectors.toList()));
    }

    @Test
    public void first10LinesTest() throws IOException {
        FileHandler fileHandler = new FileHandler("file.txt","test");
        fileHandler.first10();
        List<String> printedLines = Files.readAllLines(Paths.get("output.txt"));
        List<String> expectedLines = Files.readAllLines(Paths.get("file.txt"));
        expectedLines.replaceAll(s -> "test" + s);
        expectedLines = expectedLines.stream().filter(line -> line.length() > 5)
                                        .skip(3)
                                        .limit(10)
                                        .collect(Collectors.toList());
        assertEquals(printedLines,expectedLines);
    }
    @Test
    public void abcTest() throws IOException {
        FileHandler fileHandler = new FileHandler("file.txt","test");
        fileHandler.abc();
        List<String> printedLines = Files.readAllLines(Paths.get("output.txt"));
        List<String> expectedLines = Files.readAllLines(Paths.get("file.txt"));
        expectedLines.replaceAll(s -> "test" + s);
        expectedLines = expectedLines.stream().filter(line -> line.length() > 5)
                .skip(3)
                .limit(10)
                .sorted()
                .collect(Collectors.toList());
        assertEquals(printedLines,expectedLines);
    }

    @Test
    public void sortCountTest() throws IOException {
        FileHandler fileHandler = new FileHandler("file.txt","test");
        fileHandler.charCount();
        List<String> printedLines = Files.readAllLines(Paths.get("output.txt"));
        List<String> expectedLines = Files.readAllLines(Paths.get("file.txt"));
        expectedLines.replaceAll(s -> "test" + s);
        expectedLines = expectedLines.stream().filter(line -> line.length() > 5)
                .skip(3)
                .limit(10)
                .sorted()
                .sorted((line1, line2) -> (int) (line1.chars().distinct().count() - line2.chars().distinct().count()))
                .collect(Collectors.toList());
        assertEquals(printedLines,expectedLines);
    }
    @Test
    public void palindromeTest() throws IOException {
        FileHandler fileHandler = new FileHandler("file.txt","test");
        fileHandler.palindrome();
        List<String> printedLines = Files.readAllLines(Paths.get("output.txt"));
        List<String> expectedLines = Files.readAllLines(Paths.get("file.txt"));
        expectedLines.replaceAll(s -> "test" + s);
        expectedLines = expectedLines.stream().map(line -> {
                    if (line.contentEquals(new StringBuilder(line).reverse())) {
                        return line;
                    } else {
                        return line + new StringBuilder(line).reverse();
                    }
                })
                .filter(line -> line.length() > 5)
                .skip(3)
                .limit(10)
                .sorted()
                .sorted((line1, line2) -> (int) (line1.chars().distinct().count() - line2.chars().distinct().count()))
                .collect(Collectors.toList());
        assertEquals(printedLines,expectedLines);
    }

}
