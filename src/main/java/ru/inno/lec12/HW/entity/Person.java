package ru.inno.lec12.HW.entity;

import java.util.Date;
import java.util.Objects;

public class Person {
    private int id;
    private String name;
    private long birthDate;

    public Person(int id, String name, long birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Person(String name, long birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + new Date(birthDate) +
                '}';
    }
}
