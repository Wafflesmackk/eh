import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Coordinates {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("coordinates.txt"));

            // Készíts egy folyamot a Descartes-koordinátákból minden egyes művelethez külön-külön
            // Az egyes folyamokat külön kell kezelni, mivel a Stream egyszer használatos
            Stream<Coordinate> coordinateStream1 = lines.stream()
                    .map(line -> {
                        String[] parts = line.split(" ");
                        if (parts.length == 2) {
                            int x = Integer.parseInt(parts[0]);
                            int y = Integer.parseInt(parts[1]);
                            return new Coordinate(x, y);
                        }
                        return null;
                    });

            Stream<Coordinate> coordinateStream2 = lines.stream()
                    .map(line -> {
                        String[] parts = line.split(" ");
                        if (parts.length == 2) {
                            int x = Integer.parseInt(parts[0]);
                            int y = Integer.parseInt(parts[1]);
                            return new Coordinate(x, y);
                        }
                        return null;
                    });

            Stream<Coordinate> coordinateStream3 = lines.stream()
                    .map(line -> {
                        String[] parts = line.split(" ");
                        if (parts.length == 2) {
                            int x = Integer.parseInt(parts[0]);
                            int y = Integer.parseInt(parts[1]);
                            return new Coordinate(x, y);
                        }
                        return null;
                    });

            // Add meg, melyik pont van legközelebb az origótól
            Coordinate closestToOrigin = coordinateStream1
                    .min((c1, c2) -> Double.compare(c1.distanceToOrigin(), c2.distanceToOrigin()))
                    .orElse(null);

            if (closestToOrigin != null) {
                System.out.println("Legközelebb az origótól: " + closestToOrigin);
            } else {
                System.out.println("Nincs koordináta a fájlban.");
            }

            // Add meg a legelső olyan pontot, amelynek mindkét koordinátája prímszám
            Coordinate firstPrimeCoordinate = coordinateStream2
                    .filter(coordinate -> coordinate != null && isPrime(coordinate.getX()) && isPrime(coordinate.getY()))
                    .findFirst()
                    .orElse(null);

            if (firstPrimeCoordinate != null) {
                System.out.println("Az első olyan pont, amelynek mindkét koordinátája prímszám: " + firstPrimeCoordinate);
            } else {
                System.out.println("Nincs ilyen pont a fájlban.");
            }

            // Összesen hány ilyen pont van
            long primeCoordinateCount = coordinateStream3
                    .filter(coordinate -> coordinate != null && isPrime(coordinate.getX()) && isPrime(coordinate.getY()))
                    .count();

            System.out.println("Összesen " + primeCoordinateCount + " olyan pont van, amelynek mindkét koordinátája prímszám.");

            // Van-e ilyen pont?
            boolean hasPrimeCoordinate = primeCoordinateCount > 0;
            System.out.println("Van-e ilyen pont? " + hasPrimeCoordinate);

            // Mindegyik pont ilyen-e?
            boolean allPrimeCoordinates = primeCoordinateCount == lines.size();
            System.out.println("Mindegyik pont ilyen-e? " + allPrimeCoordinates);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    long sum = Stream.iterate(0, n -> n + 1)
                .limit(Integer.MAX_VALUE)
                .parallel() // Párhuzamos végrehajtás
                .mapToLong(Long::valueOf)
                .sum();
System.out.println("Összeg (referencia): " + sum);
     */
    // Segédfüggvény a prímszámok ellenőrzéséhez
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distanceToOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
