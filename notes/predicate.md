## Predicate
  * test()
  ```java
  public interface Predicate<T> {
      public boolean test(T t);
  }
  Predicate<Person> test = person -> person.getAge() > 20;
  Predicate<String> p = s -> s.length() > 20;
  ``` 
 * default Predicate<? super T> and(Predicate<? super T> other)
    *  chaining predicates - 2 or more conditions should be fulfilled   
  ```java
  Predicate<String> p1 = s -> s.length() > 5;
  Predicate<String> p2 = s -> s.length() < 20;
  Predicate<String> p3 = s -> s.length() > 20;

  Predicate<Person> and1 = p1.and(p2);
  Predicate<Person> and2 = p1.and(p2).and(p3);

  boolean b = and.test("stringExample");
 ```
 * default Predicate<T> or(Predicate<? super T> other)
    * chaining predicates - only one condition should be fulfilled  
  ```java
  Predicate<String> p1 = s -> s.length() > 5;
  Predicate<String> p2 = s -> s.length() = 20;

  Predicate<Person> or = p1.or(p2);

  boolean b = and.or("stringExample");
 ```
 * default Predicate<T> not()
    * logical negation of this predicate
  ```java
  Predicate<String> p1 = s -> s.length() > 5;

  Predicate<Person> not = p1.not(); //length() <= 5

  boolean b = and.or("stringExample");
 ```
 * static <T> Predicate<T> isEqual(Object targetRef)
    * Returns a predicate that tests if two arguments are equal according to
  ```java
  Predicate<String> yes = Predicate.isEqualTo("Yes");

  boolean resTrue = yes,test("Yes"); //return true "Yes".equalsTo("Yes");
  boolean resFalse = yes.test("No"); //return false "Yes".equalsTo("No");
 ```
