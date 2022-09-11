package com.example.task;


import com.example.entity.Person;
import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FirstAndSecondTasksTest {

    @Test
    @Order(1)
    public void ageSortTest() {
        Person[] people = new Person[1000];
        fillInPeopleArray(people);

        assertArrayEquals(
                Sort.countingSort(people, Person::getAge),
                Arrays.stream(people)
                        .sorted(Comparator.comparingInt(Person::getAge))
                        .toArray(Person[]::new));
    }

    @Test
    @Order(5)
    public void ageSortTimeTest() {
        Person[] people = new Person[25_000_000];
        fillInPeopleArray(people);

        Stopwatch stopwatch = Stopwatch.createStarted();
        Sort.countingSort(people, Person::getAge);
        stopwatch.stop();

        System.out.println("Custom counting sort time for 25 millions: " + stopwatch);
    }

    @Test
    @Order(6)
    public void ageSortDefaultMethodTimeTest() {
        Person[] people = new Person[25_000_000];
        fillInPeopleArray(people);

        Stopwatch stopwatch = Stopwatch.createStarted();
        Arrays.sort(people, Comparator.comparingInt(Person::getAge));
        stopwatch.stop();

        System.out.println("Using Arrays.sort() time for 25 millions: " + stopwatch);
    }

    @Test
    @Order(2)
    public void weightSortTest() {
        Person[] people = new Person[1000];
        fillInPeopleArray(people);

        assertArrayEquals(
                Sort.countingSort(people, Person::getWeight),
                Arrays.stream(people)
                        .sorted(Comparator.comparingInt(Person::getWeight))
                        .toArray(Person[]::new));
    }

    @Test
    @Order(3)
    public void heightSortTest() {
        Person[] people = new Person[1000];
        fillInPeopleArray(people);

        assertArrayEquals(
                Sort.countingSort(people, Person::getHeight),
                Arrays.stream(people)
                        .sorted(Comparator.comparingInt(Person::getHeight))
                        .toArray(Person[]::new));
    }

    @Test
    @Order(4)
    public void quickSortWeightAndHeightTest() {
        Person[] people = new Person[1000];
        fillInPeopleArray(people);

        assertArrayEquals(
                Sort.quickSort(people, 0, people.length - 1,
                        Comparator.comparingInt(Person::getWeight)
                                .thenComparingInt(Person::getHeight)),
                Arrays.stream(people)
                        .sorted(Comparator.comparingInt(Person::getWeight)
                                .thenComparingInt(Person::getHeight))
                        .toArray(Person[]::new));
    }

    private void fillInPeopleArray(Person[] people) {
        for (int i = 0; i < people.length; i++) {
            people[i] = new Person(
                    new Random().nextInt(100, 250),
                    new Random().nextInt(5, 150),
                    new Random().nextInt(0, 120));
        }
    }

}