package ru.inno.lec12.HW.dao;

import ru.inno.lec12.HW.entity.Person;
import ru.inno.lec12.HW.entity.Subject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class PersonDAOImpl implements PersonDAO {

    public static final String INSERT_PERSON_SQL_TEMPLATE =
            "insert into person (name, birth_date) values (?, ?) returning id";

    public static final String UPDATE_PERSON_SQL_TEMPLATE = 
            "update person set name = ?, birth_date = ? where id = ?";

    public static final String DELETE_PERSON_SQL_TEMPLATE =
            "delete from person where id = ?";

    public static final String GET_ALL_PERSON =
            "select * from person";

    public static final String GET_ALL_PERSON_BY_SUBJECT =
            "select * from person p inner join course c on p.id = c.person_id where id = ?";



    private final Connection connection;

    public PersonDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int createPerson(Person person) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_PERSON_SQL_TEMPLATE)) {
            statement.setString(1, person.getName());
            statement.setDate(2, new Date(person.getBirthDate()));
            statement.execute();
        }

        return 0;
    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public void deletePerson(Person person) {

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
    public void linkToCourse(Person person, Subject... subject) {

    }
}
