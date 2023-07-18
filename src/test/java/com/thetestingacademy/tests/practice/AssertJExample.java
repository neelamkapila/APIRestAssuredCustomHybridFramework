package com.thetestingacademy.tests.practice;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import  java.util.Arrays;
import java.util.HashMap;
import  java.util.List;
import java.util.Map;

public class AssertJExample {
    public static void main(String[] args) {
        String response_name = "Neelam";
        assertThat(response_name).isNotNull().isNotBlank().isEqualTo("Neelam");

        List<String> names = Arrays.asList("John", "Jane","Doe");
        assertThat(names).hasSize(3).contains("John").doesNotContain("Neelam");

        Person person = new Person("John",31);
        assertThat(person)
                .hasFieldOrPropertyWithValue("name","John")
                .hasFieldOrProperty("age");

        Integer bookingId=12345;
        assertThat(bookingId)
                .isNotNegative()
                .isGreaterThan(0);

        LocalDate date = LocalDate.now();
        System.out.println(date);

        assertThat(date)
                .isAfterOrEqualTo(LocalDate.of(2023,1,1))
                .isBeforeOrEqualTo(LocalDate.of(2023,12,31))
                .isBetween(
                        LocalDate.of(2023,1,1),
                        LocalDate.of(2023,12,31)
                );

        Map<String,Integer> ages = new HashMap<>();
        ages.put("John",25);
        ages.put("Jane",22);
        assertThat(ages)
                .hasSize(2)
                .containsEntry("John",25)
                .doesNotContainValue(23);

        File file = new File("TestData1.json");
        assertThat(file).exists().isFile().canRead();
    }
    static class Person{
        private String name;
        private int age;

        public Person(String name, int age)
        {
            this.name = name;
            this.age=age;
        }
    }
}
