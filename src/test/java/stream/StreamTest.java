package stream;

import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StreamTest {

    @Test
    void testStreamFilter() {
        //Stream<T> filter(Predicate<? super T> var1)
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);

        List<Integer> collect = integerStream.filter(x -> x > 2).collect(Collectors.toList());
        assertEquals(2, collect.size());
    }

    @Test
    void testStreamMap() {
        //<R> Stream<R> map(Function<? super T, ? extends R> var1);
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);

        List<Integer> collect = integerStream.map(x -> x * 2).collect(Collectors.toList());
        assertEquals(8, collect.get(collect.size() - 1));
    }

    @Test
    void testStreamMapToInt() {
        //IntStream mapToInt(ToIntFunction<? super T> var1);
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);

        ToIntFunction<Integer> function = x -> x * 2;

        //boxed() IntStream to Stream
        List<Integer> collect = integerStream.mapToInt(x -> x * 2).boxed().collect(Collectors.toList());
        assertEquals(8, collect.get(collect.size() - 1));
    }

    @Test
    void testStreamMapToLong() {
        //LongStream mapToLong(ToLongFunction<? super T> var1);
        Stream<Long> integerStream = Stream.of(1L, 2L, 3L, 4L);

        ToIntFunction<Long> function = x -> (int) (x * 2);

        //boxed() LongStream to Stream
        List<Long> collect = integerStream.mapToLong(x -> x * 2).boxed().collect(Collectors.toList());
        assertEquals(8, collect.get(collect.size() - 1));
    }

    @Test
    void testStreamFlatMapChange2DimArrayToList() {
        //<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> var1);
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        //Stream<String[]>
        List<String> stringList = Arrays.stream(data).flatMap(Arrays::stream).collect(Collectors.toList());
        assertNotNull(stringList);
        assertEquals(6, stringList.size());
    }

    @Test
    void testStreamFlatMapAddLetterToAllItemsFrom2List() {
        //<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> var1);
        List<String> s1 = List.of("1", "2");
        List<String> s2 = List.of("3", "4");

        List<List<String>> listOfLists = Arrays.asList(s1, s2);

        //Stream<String[]>
        List<String> collect = listOfLists.stream().flatMap(Collection::stream).map(x -> x + "a").collect(Collectors.toList());
        assertNotNull(collect);
        assertEquals(4, collect.size());
        assertEquals("4a", collect.get(3));
    }

    @Test
    void testStreamFlatMapItems() {
        Item item1 = Item.builder().name("item1").build();
        Item item2 = Item.builder().name("item2").build();
        Item item3 = Item.builder().name("item3").build();
        Item item4 = Item.builder().name("item4").build();

        List<List<Item>> items = List.of(List.of(item1, item2), List.of(item3, item4));

        List<Item> collect = items.stream().flatMap(Collection::stream).collect(Collectors.toList());

        assertEquals(4, collect.size());
        assertEquals("item4", collect.get(3).getName());
    }

    @Test
    void testStreamFlatSorted() {
        //Stream<T> sorted(Comparator<? super T> var1);
        Item item1 = Item.builder().name("item1").batch(12).build();
        Item item2 = Item.builder().name("item2").batch(12).build();
        Item item3 = Item.builder().name("item3").batch(54).build();
        Item item4 = Item.builder().name("item4").batch(23).build();

        List<Item> items = List.of(item1, item2, item3, item4);

        Comparator<Item> byBatch = (o1, o2) -> o1.getBatch() - o2.getBatch();
        Comparator<Item> byBatchThenByName = byBatch.thenComparing((o1, o2) -> o2.getName().compareTo(o1.getName()));

        List<Item> collect = items.stream().sorted(byBatchThenByName).collect(Collectors.toList());

        assertEquals(4, collect.size());
        assertEquals("item2", collect.get(0).getName());
    }

    @Test
    void testStreamPeek() {
        //Stream<T> peek(Consumer<? super T> var1);
        //for debug purpose
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        list.stream()
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    @Test
    void testStreamLimit() {
        //Stream<T> limit(long var1);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> collect = list.stream().limit(2).collect(Collectors.toList());
        assertEquals(2, collect.size());
    }

    @Test
    void testStreamSkip() {
        //Stream<T> skip(long var1);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> collect = list.stream().skip(2).collect(Collectors.toList());
        assertEquals(3, collect.size());
        assertEquals(3, collect.get(0));
    }

    @Test
    void testStreamTakeWhile() {
        //default Stream<T> takeWhile(Predicate<? super T> predicate)
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Predicate<Integer> lessThan3 = x -> x < 3;

        List<Integer> collect = list.stream().takeWhile(lessThan3).collect(Collectors.toList());
        assertEquals(2, collect.size());
        assertEquals(1, collect.get(0));
        assertEquals(2, collect.get(1));
    }

    @Test
    void testStreamDropWhile() {
        //default Stream<T> takeWhile(Predicate<? super T> predicate)
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Predicate<Integer> lessThan3 = x -> x < 3;

        List<Integer> collect = list.stream().dropWhile(lessThan3).collect(Collectors.toList());
        assertEquals(3, collect.size());
        assertEquals(3, collect.get(0));
        assertEquals(4, collect.get(1));
        assertEquals(5, collect.get(2));
    }

    @Test
    void testStreamToArrayDropWhile() {
        //default Stream<T> takeWhile(Predicate<? super T> predicate)
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Object[] objects = list.toArray();
    }

    @Test
    void testStreamToArray() {
        //Object[] toArray
        Item item1 = Item.builder().name("item1").batch(12).build();
        Item item2 = Item.builder().name("item2").batch(12).build();
        Item item3 = Item.builder().name("item3").batch(54).build();
        Item item4 = Item.builder().name("item4").batch(23).build();

        List<Item> items = List.of(item1, item2, item3, item4);

        Object[] objects = items.toArray();

        assertEquals(4, objects.length);
    }

    @Test
    void testStreamToArray2ndApproach() {
        //<T> T[] toArray(T[] var1);
        Item item1 = Item.builder().name("item1").batch(12).build();
        Item item2 = Item.builder().name("item2").batch(12).build();
        Item item3 = Item.builder().name("item3").batch(54).build();
        Item item4 = Item.builder().name("item4").batch(23).build();

        List<Item> items = List.of(item1, item2, item3, item4);

        Object[] objects = items.toArray(Item[]::new);

        assertEquals(4, objects.length);
    }

    @Test
    void testStreamReduce() {
        //T reduce(T var1, BinaryOperator<T> var2);
        List<Integer> items = List.of(1, 2, 3, 4);

        BinaryOperator<Integer> bi = Integer::sum;

        Integer reduce = items.stream().reduce(0, bi);
        assertEquals(1 + 2 + 3 + 4, reduce);
    }

    @Test
    void testStreamReduceOptional() {
        //Optional<T> reduce(BinaryOperator<T> var1);
        List<Integer> items = List.of(1, 2, 3, 4);

        BinaryOperator<Integer> bi = Integer::sum;

        Integer reduce = items.stream().reduce(bi).orElse(0);

        assertEquals(1 + 2 + 3 + 4, reduce);
    }

    @Test
    void testStreamReduceBiFunctionOptional() {
        //<U > U reduce(U var1, BiFunction < U, ? super T, U > var2, BinaryOperator < U > var3);
        //Combiner is only used in parallel stream to combine different results from different threads.

        String[] array = {"one", "two", "three", "four"};
        String result = Arrays.stream(array)
                .parallel()
                .reduce("",
                        (s, s2) -> s + "-" + s2,
                        (s, s2) -> s + "|" + s2);

        assertEquals("-one|-two|-three|-four", result);
    }

    @Data
    @Builder
    private static class Item {
        private String name;
        private int batch;
    }
}
