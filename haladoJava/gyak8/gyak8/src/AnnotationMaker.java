import java.lang.annotation.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

@Repeatable(Authors.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface Author {
    String value();
}
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface Authors{
    Author[] value();
}

public class AnnotationMaker {

    public static ArrayList<String> printAuthor(String typeName){
        Class<?> classType = null;
        try {
            classType = Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Author[] authors = classType.getAnnotationsByType(Author.class);
            Stream<Author> authorStream = Arrays.stream(authors);
            ArrayList<String> names = new ArrayList<>();
            authorStream.forEach(author -> {
                names.add(author.value());
            });
            return names;
    }

    public static ArrayList<String> printDifferentAuthors(String typeName){
        Class<?> classType = null;
        try {
            classType = Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> names = new ArrayList<>();
        for (java.lang.reflect.Method method : classType.getDeclaredMethods()) {
            Author[] authors = method.getAnnotationsByType(Author.class);

            for (Author author : authors) {
                if (!author.value().equals(classType.getAnnotation(Author.class).value())) {
                    names.add(author.value());
                }
            }
        }
        return names;
    }

}
