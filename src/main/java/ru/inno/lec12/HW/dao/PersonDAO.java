package ru.inno.lec12.HW.dao;

import ru.inno.lec12.HW.entity.Person;
import ru.inno.lec12.HW.entity.Subject;

import java.sql.SQLException;
import java.util.Collection;

public interface PersonDAO {

    void createPerson(Person person) throws SQLException;

    Person getPerson(int id) throws SQLException;

    void updatePerson(Person person) throws SQLException;

    void deletePerson(Person person) throws SQLException;

    Collection<Person> getAllPersons() throws SQLException;

    Collection<Person> getPersonsBySubject(Subject subject) throws SQLException;

    void joinToCourse(Person person, Subject... subject) throws SQLException;

}
