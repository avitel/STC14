package ru.inno.lec12.HW.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.lec12.HW.entity.Person;
import ru.inno.lec12.HW.entity.Subject;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class PersonDAOImpl implements PersonDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDAOImpl.class);


    public static final String CREAT_PERSON_SQL_TEMPLATE =
            "insert into person (name, birth_date) values (?, ?) returning id";

    public static final String GET_PERSON_SQL_TEMPLATE =
            "select * from person where id = ?";

    public static final String UPDATE_PERSON_SQL_TEMPLATE =
            "update person set name = ?, birth_date = ? where id = ?";

    public static final String DELETE_PERSON_SQL_TEMPLATE =
            "delete from person where id = ?";

    public static final String GET_ALL_PERSON_TEMPLATE =
            "select * from person";

    public static final String GET_ALL_PERSON_BY_SUBJECT_TEMPLATE =
            "select * from person p inner join course c on p.id = c.person_id where subject_id = ?";

    public static final String JOIN_TO_COURSE_TEMPLATE =
            "insert into course (person_id, subject_id) values ";



    private final Connection connection;


    public PersonDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void createPerson(Person person) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREAT_PERSON_SQL_TEMPLATE)) {
            statement.setString(1, person.getName());
            statement.setDate(2, new Date(person.getBirthDate()));
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                person.setId(rs.getInt("id"));
            }else {
                LOGGER.error("returning id fail");
                throw new SQLDataException("something went wrong");
            }

            LOGGER.info("person {} {} was created", person.getId(), person.getName());
        }
    }


    @Override
    public Person getPerson(int id) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(GET_PERSON_SQL_TEMPLATE)) {
            statement.setString(1, Integer.toString(id));
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Person person = new Person(rs.getInt("id"), rs.getString("name"), rs.getLong("birth_date"));
                return person;
            }else {
                throw new SQLDataException("no such person");
            }
        }
    }


    @Override
    public void updatePerson(Person person) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PERSON_SQL_TEMPLATE)) {
            statement.setString(1, Integer.toString(person.getId()));
            statement.execute();

            LOGGER.info("person {} {} was updated", person.getId(), person.getName());

        }
    }


    @Override
    public void deletePerson(Person person) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PERSON_SQL_TEMPLATE)) {
            statement.setString(1, Integer.toString(person.getId()));
            statement.execute();

            LOGGER.info("person {} {} was deleted", person.getId(), person.getName());
        }
    }


    @Override
    public ArrayList<Person> getAllPersons() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(GET_PERSON_SQL_TEMPLATE);
            ResultSet rs = statement.executeQuery()){

            ArrayList<Person> ls = new ArrayList<>();

            while (rs.next()) {
                ls.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getLong("birth_date")));
            }
            
            return ls;
        }
    }


    @Override
    public Collection<Person> getPersonsBySubject( Subject subject) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_PERSON_BY_SUBJECT_TEMPLATE)) {
            statement.setString(1, Integer.toString(subject.getId()));
            ResultSet rs = statement.executeQuery();

            ArrayList<Person> ls = new ArrayList<>();

            while (rs.next()) {
                ls.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getLong("birth_date")));
            }

            return ls;
        }
    }


    @Override
    public void joinToCourse(Person person, Subject... subjects) throws SQLException {

        StringBuilder sb = new StringBuilder();
        sb.append(JOIN_TO_COURSE_TEMPLATE );
        for (Subject subject : subjects) {
            sb.append( "("+person.getId()+", "+subject.getId()+"),");
        }
        sb.deleteCharAt(sb.length()-1);

        try (PreparedStatement statement = connection.prepareStatement(sb.toString())) {
             statement.execute();
        }

        LOGGER.info("person {} {} was joined to course {}", person.getId(), person.getName(), Arrays.toString(subjects));

    }
}
