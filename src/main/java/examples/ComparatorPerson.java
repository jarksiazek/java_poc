package examples;

import model.Person;

import java.util.Comparator;
import java.util.function.Function;

public class ComparatorPerson {
    Comparator<Person> personComparatorByAge = (p1, p2) -> p2.getAge() - p1.getAge();
    Comparator<Person> personComparatorByFirstName = (p1, p2) -> p2.getFirstName().compareTo(p1.getFirstName());

    //Function<returnObject, argObject>
    Function<Person, Integer> fAge1 = p -> p.getAge();
    Function<Person, Integer> fAge2 = Person::getAge;

    Function<Person, String> fFirstName = Person::getFirstName;

    Comparator<Person> compPer = Comparator.comparing(fAge2);
    Comparator<Person> compPerName = Comparator.comparing(fFirstName);

    Comparator<Person> cmp = compPer.thenComparing(compPerName);
}
