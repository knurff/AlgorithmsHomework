package com.example.task;

import com.example.entity.Person;

import java.util.Comparator;

public final class ThirdTask {
    private ThirdTask() {
    }

    public static int sameWeightDifferentHeight(Person[] people) {
        int counter = 0;

        if (people.length > 0) {
            Sort.quickSort(people, 0, people.length - 1,
                    Comparator.comparingInt(Person::getWeight).thenComparingInt(Person::getHeight));

            int currentWeight = people[0].getWeight();
            int currentHeight = people[0].getHeight();
            int tempCounter = 1;

            for (int i = 0, peopleLength = people.length; i < peopleLength; i++) {
                Person person = people[i];

                if (i != 0 && person.getWeight() == currentWeight && person.getHeight() > currentHeight)
                    tempCounter++;

                else if (tempCounter > 1) {
                    counter += tempCounter;
                    tempCounter = 1;
                }

                currentWeight = person.getWeight();
                currentHeight = person.getHeight();
            }

            if (tempCounter > 1)
                counter += tempCounter;

        }

        return counter;
    }
}
