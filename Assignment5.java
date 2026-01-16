import java.util.ArrayList;
import java.util.List;

/**
 * Write a Java program that uses List to store Person(id, name and age) objects
 * Allow the user to input id, name and age.
 * Support searching for a person by id and display the name and age.
 */
public class Assignment5 {
    public static void main(String[] args) {
        Person person1 = new Person(1, "person 1", 23);
        Person person2 = new Person(2, "person 2", 35);
        Person person3 = new Person(3, "person 3", 12);
        Person person4 = new Person(4, "person 4", 60);

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        findPersonById(personList, 5);
        findPersonById(personList, 3);
    }

    private static void findPersonById(List<Person> personList, int id) {
        for (Person person : personList) {
            if(person.id == id) {
                System.out.printf("Person found!\n",id);
                System.out.println("Name = "+person.name+", Age = "+person.age);
                return;
            }
        }

        System.out.printf("Person  not found.\n",id);
    }
}


class Person {
    int id;
    String name;
    int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
