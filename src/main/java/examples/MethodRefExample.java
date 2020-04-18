package examples;

import model.Person;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

public class MethodRefExample {

    //Method reference example
    Function<Person, Integer> f = person -> person.getAge();
    Function<Person, Integer> methodRefPer = Person::getAge;

    //Method reference BinaryOperator
    BinaryOperator<Integer> sum = (i1, i2) -> i1 + i2;
    BinaryOperator<Integer> sumRef1 = Integer::sum;
    BinaryOperator<Integer> sumRef2 = (i1, i2) -> Integer.sum(i1, i2);
    BinaryOperator<Integer> maxRef = Integer::max;

    //Method reference Consumer
    Consumer<String> printer = s-> System.out.println(s);
    Consumer<String> printerRef = System.out::println;
}
