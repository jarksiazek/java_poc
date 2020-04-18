## java.util.function

### Four categories: 
* Consumer
    ```java
  public interface Consumer<T> {
      public void accept (T t);
  };
  Consumer<String> print = s -> System.out.println(s);
    ```
     ```java
  public interface BiConsumer<T, V> {
      public void accept (T t, V v);
  };
    BiConsumer<String, String > print = s -> System.out.println(s);
   ```
* Supplier
    ```java
  public interface Supplier<T> {
      public T get ();
  }
  Supplier<Person> person = () -> new Person();
  Supplier<Person> person = Person::new;
    ```
* Function
    ```java
  public interface Function<T, R> {
      public R apply(T t);
  }
  Function<Person, String> print = person -> person.getName();
  Function<Person, String> print = Person::getName;
   ```
   ```java
  public interface BiFunction<T, V, R> {
      public R apply(T t, V v);
  }
  BiFunction<Person, Integer, String> print = (person, int)  -> person.getName(int);
   ```
   ```java
  public interface UnaryOperator<T> extends Function<T, R> {
  }
   ```
   ```java
  public interface BinaryOperator<T> extends BiFunction<T, V, R> {
  }
  BinaryOperator<Long> addLongs = (x, y) -> x + y;
   ```
* Predicate
   ```java
  public interface Predicate<T> {
      public boolean test(T t);
  }
  Predicate<Person> test = person -> person.getAge() > 20;

  ``` 
 ### Interfaces for primitive types:
 * IntPredicate
 * IntFunction
 * IntToDoubleFunction 