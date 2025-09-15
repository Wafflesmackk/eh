import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReflectionTester {
    @Test
    public void testVisibilityCheckHasPublic(){
        DummyClassPublic trial = new DummyClassPublic();
        assertFalse(OwnReflection.visibilityCheck(trial.getClass()));
    }
    @Test
    public void testVisibilityCheckHasPrivate(){
        DummyClassPrivate trial = new DummyClassPrivate();
        assertTrue(OwnReflection.visibilityCheck(trial.getClass()));
    }

}
