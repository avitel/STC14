package ru.inno.lec12.HW.dao;

import ru.inno.lec12.HW.entity.Person;
import ru.inno.lec12.HW.entity.Subject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class CourseDAOImpl implements CourseDAO {

    public static final String INSERT_PERSON_SQL_TEMPLATE =
            "insert into person (name, birth_date) values (?, ?)";






    private final Connection connection;

    public CourseDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Subject> getAllSubjects() {
        return null;
    }

    @Override
    public Collection<Person> getAllPersons() {
        return null;
    }

    @Override
    public Collection<Person> getPersonsBySubject(Subject subject) {
        return null;
    }

    @Override
    public Collection<Subject> getSubjectsByPerson(Person person) {
        return null;
    }

    @Override
    public void linkToCourse(Person person, Subject subject) {

    }

    @Override
    public void createPerson(Person person) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_PERSON_SQL_TEMPLATE)) {
            statement.setString(1, person.getName());
            statement.setDate(2, new Date(person.getBirthDate()));
            statement.execute();
        }

    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public void deletePerson(Person person) {

    }

    @Override
    public void createSubject(Subject subject) {

    }
}
