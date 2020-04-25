package lambda;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PredicateTest {

    @Test
    void testPredicate_shouldReturnTrue() {
        Predicate<String> p = s -> s.length() > 5;

        boolean result = p.test("wordWord");
        assertTrue(result);
    }
}
