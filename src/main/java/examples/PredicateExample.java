package examples;

import model.Person;

import java.util.function.Predicate;

public class PredicateExample {

    Predicate<Person> predicateJdk7 = new Predicate<Person>() {
        @Override
        public boolean test(Person person) {
            return false;
        }
    };

    Predicate<Person> predicateJdk8 = person -> true;
    Predicate<Person> predicateAge = person -> person.getAge() > 15;
    Predicate<String> stringPredicate = s -> s.length() > 10;

    void printData() {
        System.out.println(stringPredicate.test("word"));
    }
}

