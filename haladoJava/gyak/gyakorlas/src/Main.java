import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        /*Supplier<String> stringSupplier = generateInfiniteStrings();
        Supplier<Integer> integerSupplier = generateInfiniteNumbers();
        Supplier<String> stringResultSupplier = generateSupplier(stringSupplier);
        Supplier<Integer> integerResultSupplier = generateSupplier(integerSupplier);

        for (int i = 0; i < 10; i++) {
            System.out.println(stringResultSupplier.get());
            System.out.println(integerResultSupplier.get());
        }*/




       /* BiFunction<String, Integer, List<Integer>> originalFunction = (str, num) -> {
            return List.of(str.length(), num);
        };

        BiFunction<Integer, String, List<Integer>> reversedFunction = reverseBiFunction(originalFunction);

        List<Integer> result = reversedFunction.apply(5, "Helloaaa");
        System.out.println(result); // Az eredmény: [8, 5]*/





        /*IntUnaryOperator addOne = x -> x + 1;
        IntUnaryOperator doubleIt = x -> x * 2;

        // A kompozíció létrehozása
        IntUnaryOperator composedOperator = composeIntUnaryOperators(addOne, doubleIt);

        // Tesztelés
        int result = composedOperator.applyAsInt(5);
        System.out.println(result); // Az eredmény: (5 + 1) * 2 = 12*/



        /*
        // Két példa függvény
        BiFunction<String, Integer, Integer> function1 = (str, x) -> str.length() + x;
        BiFunction<String, Integer, Integer> function2 = (str, x) -> str.length() - x;

        // A predikátum létrehozása
        Predicate<String> isFunction1GreaterThanFunction2 = compareFunctions(function1, function2);

        // Tesztelés
        String inputString = "Hello";
        boolean result = isFunction1GreaterThanFunction2.test(inputString);
        System.out.println(result); // Az eredmény: true */

        String[] sampleArgs = {"apple", "5", "banana", "cherry", "3", "date", "fig", "10"};

        // Rendezzük a parancssori argumentumokat a megadott szabályok szerint
        Arrays.sort(sampleArgs, (a, b) -> {
            if (a.length() != b.length()) {
                // Elsődleges rendezés: hossz szerint növekvő sorrend
                return a.length() - b.length();
            } else {
                // Másodlagos rendezés: számok előre (számérték szerint), majd nem-szám szövegek ábécésorrendben
                if (isNumeric(a) && isNumeric(b)) {
                    // Mindkét argumentum szám
                    return Integer.parseInt(a) - Integer.parseInt(b);
                } else if (isNumeric(a)) {
                    // Csak 'a' szám, előre
                    return -1;
                } else if (isNumeric(b)) {
                    // Csak 'b' szám, hátra
                    return 1;
                } else {
                    // Mindkét argumentum nem-szám, ábécésorrendben
                    return a.compareTo(b);
                }
            }
        });

        // Kiírjuk az eredményt
        for (String arg : sampleArgs) {
            System.out.println(arg);
        }

    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static <T> Predicate<T> compareFunctions(BiFunction<T, Integer, Integer> function1, BiFunction<T, Integer, Integer> function2) {
        return param -> function1.apply(param, 1) > function2.apply(param, 1);
    }

    public static IntUnaryOperator composeIntUnaryOperators(IntUnaryOperator op1, IntUnaryOperator op2) {
        return x -> op2.applyAsInt(op1.applyAsInt(x));
    }
    public static <A, B, R> BiFunction<B, A, R> reverseBiFunction(BiFunction<A, B, R> original) {
        return (b, a) -> original.apply(a, b);
    }

    public static Supplier<String> generateInfiniteStrings() {
        return new Supplier<String>() {
            private char nextChar = 'a';

            @Override
            public String get() {
                return String.valueOf(nextChar++);
            }
        };
    }

    public static Supplier<Integer> generateInfiniteNumbers() {
        return new Supplier<Integer>() {
            private int nextNumber = 1;

            @Override
            public Integer get() {
                return nextNumber++;
            }
        };
    }

    public static <T> Supplier<T> generateSupplier(Supplier<T> inputSupplier) {
        return new Supplier<T>() {
            private StringBuilder result = new StringBuilder();
            //private int currentIndex = 0;

            @Override
            public T get() {
                if (result.length() > 0) {
                    result.append(", ");
                }
                result.append(inputSupplier.get());
                return (T) result.toString();
            }
        };
    }
}
