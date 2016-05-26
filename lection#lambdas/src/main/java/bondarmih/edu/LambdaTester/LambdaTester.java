package bondarmih.edu.LambdaTester;

import bondarmih.edu.person.Person;
import bondarmih.edu.resourceHandler.NamesResourceHandler;
import bondarmih.edu.util.RandomInt;
import bondarmih.edu.util.Sex;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bondarm on 25.05.16.
 */
public class LambdaTester {

    private static double getAverageAge(List<Person> personList) {
        double result = personList.stream()
                .limit(10)
                .mapToInt(Person::getAge)
                .average().getAsDouble();
        return result;
    }

    private static List<Person> sortByAge(List<Person> personList) {
        List<Person> result = personList.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .collect(Collectors.toList());
        return result;
    }

    private static Map<String,Long> getNamesCount(List<Person> personList) {
        Map <String, Long> result = personList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()));
        return result;
    }

    private static void iterateWithModificator (List<Person> personList, Consumer<Person> modificator) {
        personList.stream()
                .forEach(modificator);
    }

    private static List<Person> filterOutInfants(List<Person> personList) {
        List<Person> result = personList.stream()
                .filter(person -> person.getAge()>18)
                .collect(Collectors.toList());
        return result;
    }

    public static void test() {
        NamesResourceHandler namesResourceHandler = new NamesResourceHandler();

        Supplier<Person> personSupplier = () -> {
            int sexMapping = RandomInt.randInt(0, Sex.values().length-1);
            Sex instanceSex = Sex.values()[sexMapping];
            int instanceAge = RandomInt.randInt(0,80);
            String instanceName = namesResourceHandler.getRandomName(Sex.values()[sexMapping]);
            return new Person(instanceName, instanceAge, instanceSex);
        };

        List<Person> personList = Stream
                .generate(personSupplier)
                .limit(200)
                .collect(Collectors.toList());

        Consumer<Person> reduceWomanAge = person -> {
            if (person.getSex() == Sex.FEMALE &&  person.getAge() >= 10) {
                int newAge = person.getAge()-10;
            person.setAge(newAge);
            }
        };

        System.out.println("Passing function to method test. Function decreases every women's age by 10.");
        System.out.print("Initial list of persons   : " + personList.toString());
        iterateWithModificator(personList, reduceWomanAge);
        System.out.print("Processed list of persons : " + personList.toString());

        System.out.println("Sort by age");
        System.out.println("Initial list of persons   : " + personList.toString());
        List<Person> sortedByAgePersonList = sortByAge(personList);
        System.out.println("Processed list of persons : " + sortedByAgePersonList.toString());

        System.out.println("Average age");
        System.out.println("Initial list of persons   : " + personList.toString());
        System.out.println("AverAge : "+ getAverageAge(personList));

        System.out.println("Names count");
        System.out.println("Initial list of persons   : " + personList.toString());
        System.out.println("Names count : "+ getNamesCount(personList).toString());

        System.out.println("Filter out infants");
        System.out.println("Initial list of persons   : " + personList.toString());
        List<Person> filteredOutInfantsList = filterOutInfants(personList);
        System.out.println("Filtered list             : "+ (filteredOutInfantsList.toString()));
    }
}
