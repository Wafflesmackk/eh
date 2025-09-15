import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try {
            // Olvass be egy fájlt soronként folyam segítségével
            try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
                // Írd ki a sorokat a sztenderd kimenetre
                lines.forEach(System.out::println);
            }

            try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
                // Mindegyik sorához fűzz hozzá egy rögzített szöveget, és írd ki a sorokat a sztenderd kimenetre
                lines.map(line -> "Fixed Text: " + line).forEach(System.out::println);
            }

            try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
                // Az eredményt egy másik fájlba írd ki
                List<String> modifiedLines = lines.map(line -> "Fixed Text: " + line)
                        .collect(Collectors.toList());
                Files.write(Paths.get("output.txt"), modifiedLines);
            }

            try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
                // Csak azokat a sorokat írd ki, amelyek hosszabbak 5 karakternél
                lines.filter(line -> line.length() > 5).forEach(System.out::println);
            }

            try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
                // Az első 3 sor kivételével
                lines.skip(3).forEach(System.out::println);
            }

            try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
                // Az első 10 sor adatait feldolgozva
                lines.limit(10).map(line -> "Fixed Text: " + line).forEach(System.out::println);
            }

            try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
                // Legfeljebb 10 kimeneti sort készítve
                lines.limit(10).forEach(System.out::println);
            }

            try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
                // Ábécés sorrendben
                lines.sorted().forEach(System.out::println);
            }

            try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
                // Aszerinti sorrendben, hány különböző betűt tartalmaznak
                lines.sorted((line1, line2) -> (int) (line1.chars().distinct().count() - line2.chars().distinct().count()))
                        .forEach(System.out::println);
            }

            try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
                // A palindrom szavakat úgy, ahogy vannak, a többieket "palindromosítva"
                lines.map(line -> {
                    if (line.equals(new StringBuilder(line).reverse().toString())) {
                        return line;
                    } else {
                        return line + new StringBuilder(line).reverse();
                    }
                }).forEach(System.out::println);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
