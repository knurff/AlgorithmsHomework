package com.example.task;

import com.example.entity.Person;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThirdTaskTest {

    @Test
    public void sameWeightDifferentHeightEmptyTest() {
        Person[] people = {};

        assertEquals(ThirdTask.sameWeightDifferentHeight(people), 0);
    }

    @Test
    public void sameWeightDifferentHeightSamePersonsTest() {
        Person[] people = {
                new Person(150, 35, 15),
                new Person(150, 35, 15),
                new Person(150, 35, 15),
                new Person(150, 35, 15),
                new Person(150, 35, 15),
                new Person(150, 35, 15),
                new Person(150, 35, 15)
        };

        assertEquals(ThirdTask.sameWeightDifferentHeight(people), 0);
    }

    @Test
    public void sameWeightDifferentHeightAllDifferentHeightTest() {
        Person[] people = {
                new Person(156, 35, 15),
                new Person(155, 35, 15),
                new Person(154, 35, 15),
                new Person(153, 35, 15),
                new Person(152, 35, 15),
                new Person(151, 35, 15),
                new Person(150, 35, 15)
        };

        assertEquals(ThirdTask.sameWeightDifferentHeight(people), 7);
    }

    @Test
    public void sameWeightDifferentHeightMixedValuesTest() {
        Person[] people = {
                new Person(150, 30, 15),
                new Person(156, 40, 15),
                new Person(154, 35, 15),
                new Person(150, 30, 15),
                new Person(153, 35, 15),
                new Person(154, 35, 15),
                new Person(152, 30, 15)
        };

        assertEquals(ThirdTask.sameWeightDifferentHeight(people), 4);
    }

}