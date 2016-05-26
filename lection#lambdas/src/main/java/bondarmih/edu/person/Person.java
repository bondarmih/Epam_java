package bondarmih.edu.person;

import bondarmih.edu.resourceHandler.NamesResourceHandler;
import bondarmih.edu.util.RandomInt;
import bondarmih.edu.util.Sex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;
import java.util.function.Supplier;

/**
 * Created by bondarm on 25.05.16.
 */
public class Person {
    private String name;
    private int age;
    private Sex sex;

/*
    public Person () {
        int sexMapping = RandomInt.randInt(0, sex.values().length-1);
        this.sex = Sex.values()[sexMapping];

        this.age = RandomInt.randInt(0,80);

        this.name = NamesResourceHandler.getRandomName(sex);
    }
*/
    public Person (String name, int age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return this.sex;
    }

    @Override
    public String toString() {
        return (String.format("Name:" + this.getName() + ",sex:" + this.getSex() + ",age:" + "%02d" , this.getAge()));
    }
}
