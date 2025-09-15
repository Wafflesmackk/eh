import java.util.Scanner;
import java.util.stream.Stream;

public class ScannerLambdaExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner("Első szó\nMásodik szó\nHarmadik szó\nNegyedik szó\n");

        Stream<String> wordStream = generateWordStream(scanner);

        wordStream
                .filter(word -> word != null)
                .forEach(System.out::println);
    }

    public static Stream<String> generateWordStream(Scanner scanner) {
        return Stream.generate(() -> {
            if (scanner.hasNext()) {
                return scanner.next();
            } else {
                return null;
            }
        });
    }
}