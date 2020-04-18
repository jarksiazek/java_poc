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
    Predicate<Person> and = predicateJdk8.and(predicateAge);
    Predicate<Person> or = predicateJdk8.or(predicateAge);

    Predicate<String> s1 = s -> s.length() > 10;
    Predicate<String> s2 = s -> s.length() > 10;

    void printData() {
        Predicate<Object> yes = Predicate.isEqual("Yes");
        Predicate<Person> and = predicateJdk8.and(predicateAge);
        System.out.println(stringPredicate.test("word"));
    }
}

