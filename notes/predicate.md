## lambda.PredicateTest
  * test()
  ```java
  public interface lambda.PredicateTest<T> {
      public boolean test(T t);
  }
  lambda.PredicateTest<Person> test = person -> person.getAge() > 20;
  lambda.PredicateTest<String> p = s -> s.length() > 20;
  ``` 
* default lambda.PredicateTest<? super T> and(lambda.PredicateTest<? super T> other)
    *  chaining predicates - 2 or more conditions should be fulfilled   
  ```java
  lambda.PredicateTest<String> p1 = s -> s.length() > 5;
  lambda.PredicateTest<String> p2 = s -> s.length() < 20;
  lambda.PredicateTest<String> p3 = s -> s.length() > 20;

  lambda.PredicateTest<Person> and1 = p1.and(p2);
  lambda.PredicateTest<Person> and2 = p1.and(p2).and(p3);

  boolean b = and.test("stringExample");
 ```
 * default lambda.PredicateTest<T> or(lambda.PredicateTest<? super T> other)
    * chaining predicates - only one condition should be fulfilled  
  ```java
  lambda.PredicateTest<String> p1 = s -> s.length() > 5;
  lambda.PredicateTest<String> p2 = s -> s.length() = 20;

  lambda.PredicateTest<Person> or = p1.or(p2);

  boolean b = and.or("stringExample");
 ```
 * default lambda.PredicateTest<T> not()
    * logical negation of this predicate
  ```java
  lambda.PredicateTest<String> p1 = s -> s.length() > 5;

  lambda.PredicateTest<Person> not = p1.not(); //length() <= 5

  boolean b = and.or("stringExample");
 ```
 * static <T> lambda.PredicateTest<T> isEqual(Object targetRef)
    * Returns a predicate that tests if two arguments are equal according to
  ```java
  lambda.PredicateTest<String> yes = lambda.PredicateTest.isEqualTo("Yes");

  boolean resTrue = yes,test("Yes"); //return true "Yes".equalsTo("Yes");
  boolean resFalse = yes.test("No"); //return false "Yes".equalsTo("No");
 ```