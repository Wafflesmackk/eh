import java.lang.reflect.Field;

public class OwnReflection {

    public static boolean visibilityCheck(Class<?> classType){

        Field[] fields = classType.getDeclaredFields();

        for (Field field : fields) {
            if (!java.lang.reflect.Modifier.isPrivate(field.getModifiers())) {
                return false;
            }
        }
        return true;
    }


}
