package ru.inno.lec12.HW.dao;

import ru.inno.lec12.HW.entity.Person;
import ru.inno.lec12.HW.entity.Subject;

import java.sql.SQLException;
import java.util.Collection;

public interface PersonDAO {

    int createPerson(Person person) throws SQLException;

    void updatePerson(Person person);

    void deletePerson(Person person);

    Collection<Person> getAllPersons();

    Collection<Person> getPersonsBySubject(Subject subject);

    void linkToCourse(Person person, Subject... subject);

}
