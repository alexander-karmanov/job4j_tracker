package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        ArrayList<Person> result = new ArrayList<>();
        Predicate<Person> predName = n -> n.getName().contains(key);
        Predicate<Person> predSurname = n -> n.getSurname().contains(key);
        Predicate<Person> predPhone = n -> n.getPhone().contains(key);
        Predicate<Person> predAddress = n -> n.getAddress().contains(key);
        Predicate<Person> combine = predName.or(predSurname.or(predPhone.or(predAddress)));
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
