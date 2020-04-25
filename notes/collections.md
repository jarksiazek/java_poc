## Collection
* iterable 
  * forEach()
  ```java
  boolean forEach(Consumer<? super E> consumer)
  
  people.forEach(System.out::println);
  ``` 
* collection extends iterable
  * removeIf()
  ```java  
  boolean removeIf(lambda.PredicateTest<? super E> filter)  
  
  people.removeIf(person -> person.getAge()<20;
  ```
* list extends collection
  * removeAll()
  ```java  
  boolean replaceAll(UnaryOperator<? super E> operator)  
  names.replaceAll(name -> names.toUpperCase());
  ```
  * sort()
  ```java  
  boolean sort(Comperator<? super E> comperator)  
  
  ```