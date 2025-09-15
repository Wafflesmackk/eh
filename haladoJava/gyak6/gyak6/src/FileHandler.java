import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandler {
    private String path = "";
    private  String fixedText = "";

    public FileHandler(String path, String fixedText){
        this.path = path;
        this.fixedText = fixedText;
    }

    public void writeOutLines(){
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.forEach(System.out::println);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void concatToEachLine(String fixedText){
        this.fixedText = fixedText;
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.map(line -> fixedText + line).forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile(){
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            List<String> modifiedLines = lines.map(line -> fixedText + line)
                    .collect(Collectors.toList());
            Files.write(Paths.get("output.txt"), modifiedLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onlyLongerThan5(){
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            List<String> modifiedLines = lines.map(line -> fixedText + line).filter(line -> line.length() > 5).collect(Collectors.toList());
            Files.write(Paths.get("output.txt"), modifiedLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void skip3(){
        try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
            List<String> modifiedLines = lines.map(line -> fixedText + line).skip(3).filter(line -> line.length() > 5).collect(Collectors.toList());
            Files.write(Paths.get("output.txt"), modifiedLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void first10(){
        try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
            List<String> modifiedLines = lines.map(line -> fixedText + line).skip(3).filter(line -> line.length() > 5).limit(10).collect(Collectors.toList());
            Files.write(Paths.get("output.txt"), modifiedLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void abc(){
        try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
            List<String> modifiedLines = lines.map(line -> fixedText + line)
                    .skip(3)
                    .filter(line -> line.length() > 5)
                    .limit(10)
                    .sorted()
                    .collect(Collectors.toList());
            Files.write(Paths.get("output.txt"), modifiedLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void charCount(){
        try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
            List<String> modifiedLines = lines.map(line -> fixedText + line)
                    .skip(3)
                    .filter(line -> line.length() > 5)
                    .limit(10)
                    .sorted()
                    .sorted((line1, line2) -> (int) (line1.chars().distinct().count() - line2.chars().distinct().count()))
                    .collect(Collectors.toList());
            Files.write(Paths.get("output.txt"), modifiedLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void palindrome(){
        try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
            List<String> modifiedLines = lines
                    .map(line -> {
                        if ((fixedText + line).equals(new StringBuilder(fixedText + line).reverse().toString())) {
                            return (fixedText + line);
                        } else {
                            return (fixedText + line) + new StringBuilder((fixedText + line)).reverse();
                        }
                     })
                    .skip(3)
                    .filter(line -> line.length() > 5)
                    .limit(10)
                    .sorted()
                    .sorted((line1, line2) -> (int) (line1.chars().distinct().count() - line2.chars().distinct().count()))
                    .collect(Collectors.toList());
            Files.write(Paths.get("output.txt"), modifiedLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
