package map;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    Map<String, Integer> map = Map.of("1", 1, "2", 2);
    Map<String, Integer> mutableMap = new HashMap<>(map);
    Map<String, List<Integer>> mapWithList = Map.of("1", List.of(1), "2", List.of(2));

    @Test
    void testMapForEach_shouldPrintValues() {
        //default void forEach(BiConsumer<? super K, ? super V> action)

        BiConsumer<String, Integer> biConsumer = (k, v) -> System.out.println("key: " + k + ",value: " + v);
        map.forEach(biConsumer);
    }

    @Test
    void testMapForEach_shouldPrintPeopleAndCitiesValues() {
        //default void forEach(BiConsumer<? super K, ? super V> action)

        BiConsumer<String, List<Integer>> biConsumer = (k, v) -> System.out.println("key: " + k + ",value: " + v);
        mapWithList.forEach(biConsumer);
    }

    @Test
    void testMapGetOrDefault_shouldGetDefault() {
        //default V getOrDefault(Object key, V defaultValue)
        List<Integer> defaultValue = List.of(1, 2, 3);

        List<Integer> result = mapWithList.getOrDefault("3", defaultValue);
        assertEquals(defaultValue, result);
    }

    @Test
    void testMapPutIfAbsent_shouldNotPutNewValue() {
        //default V putIfAbsent(K key, V value)
        //check if the key already exists if yes then to not modify it
        Integer oldNotChanged = mutableMap.putIfAbsent("2", 3);
        assertEquals(2, mutableMap.get("2"));
        assertEquals(2, oldNotChanged);
    }

    @Test
    void testMapPutIfAbsent_shouldPutNewValue() {
        //default V putIfAbsent(K key, V value)
        //check if the key already exists if yes then to not modify it
        mutableMap.putIfAbsent("3", 3);
        assertEquals(3, mutableMap.get("3"));
    }

    @Test
    void testMapReplace_shouldReplaceValues() {
        //default boolean replace(K key, V oldValue, V newValue)
        //check key and value and then replace value

        //current value is correct
        boolean replaceTrue = mutableMap.replace("2", 2, 3);
        assertTrue(replaceTrue);

        //current value is incorrect
        boolean replaceFalse = mutableMap.replace("2", 2, 3);
        assertFalse(replaceFalse);
    }

    @Test
    void testMapReplaceAll_shouldReplaceAllValues() {
        //default void replaceAll(BiFunction<? super K, ? super V, ? extends V> function)

        BiFunction<String, Integer, Integer> biFunction = (k, v) -> 5;
        mutableMap.replaceAll(biFunction);
        assertEquals(5, mutableMap.get("1"));
        assertEquals(5, mutableMap.get("2"));
    }

    @Test
    void testMapCompute_shouldEffectOnlyFistValue() {
        //default V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction

        BiFunction<String, Integer, Integer> biFunction = (k, v) -> 5;
        mutableMap.compute("1", biFunction);
        assertEquals(5, mutableMap.get("1"));
        assertNotEquals(5, mutableMap.get("2"));
    }

    @Test
    void testMapComputeIfPresent_shouldEffectOnlyFistValue_whenValueFor3IsNull() {
        //default V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
        //value cannot be null

        mutableMap.put("3", null);

        BiFunction<String, Integer, Integer> biFunction = (k, v) -> 5;
        mutableMap.computeIfPresent("1", biFunction);
        mutableMap.computeIfPresent("3", biFunction);
        assertEquals(5, mutableMap.get("1"));
        assertNotEquals(5, mutableMap.get("2"));
        assertNotEquals(5, mutableMap.get("3"));
    }

    @Test
    void testMapComputeIfAbsent_shouldEffectOnlyNullValue_whenValueFor3IsNull() {
        //default V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
        //value cannot be null

        mutableMap.put("3", null);

        Function<String, Integer> function = k -> 5;
        mutableMap.computeIfAbsent("1", function);
        mutableMap.computeIfAbsent("3", function);
        assertNotEquals(5, mutableMap.get("1"));
        assertNotEquals(5, mutableMap.get("2"));
        assertEquals(5, mutableMap.get("3"));
    }

    @Test
    void testMapMerge_shouldModifyMap() {
        //default V merge(K key, V newValue, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
        //takes new value if current is null, if current value is not null use biFunction
        mutableMap.put("3", null);

        BiFunction<Integer, Integer, Integer> biFunction = (v1, v2) -> 5;
        mutableMap.merge("1", 2, biFunction);
        mutableMap.merge("3", 2, biFunction);

        assertEquals(5, mutableMap.get("1"));
        assertEquals(2, mutableMap.get("2"));
    }

    @Test
    void testMapMerge_shouldModifyMap_whenKeyIsNotExists() {
        BiFunction<Integer, Integer, Integer> biFunction = (v1, v2) -> 5;
        mutableMap.merge("3", 2, biFunction);
        assertEquals(2, mutableMap.get("3"));
    }

    @Test
    void testMapMerge_shouldMerge2Maps() {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        map2.forEach(
                (key, value) -> map1.merge(key, value, (v1, v2) -> 5)
        );
    }
}
