package ru.inno.lec12.HW.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.lec12.HW.entity.Subject;
import ru.inno.lec12.HW.entity.Person;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SubjectDAOImpl implements SubjectDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectDAOImpl.class);


    public static final String CREAT_SUBJECT_SQL_TEMPLATE =
            "insert into subject (description) values (?) returning id";

    public static final String GET_SUBJECT_SQL_TEMPLATE =
            "select * from subject where id = ?";

    public static final String UPDATE_SUBJECT_SQL_TEMPLATE =
            "update subject set description = ?  where id = ?";

    public static final String DELETE_SUBJECT_SQL_TEMPLATE =
            "delete from subject where id = ?";

    public static final String GET_ALL_SUBJECT_TEMPLATE =
            "select * from subject";

    public static final String GET_ALL_SUBJECT_BY_PERSON_TEMPLATE =
            "select * from subject p inner join course c on p.id = c.subject_id where person_id = ?";

    public static final String JOIN_TO_COURSE_TEMPLATE =
            "insert into course (subject_id, person_id) values ";



    private final Connection connection;


    public SubjectDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void createSubject(Subject subject) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREAT_SUBJECT_SQL_TEMPLATE)) {
            statement.setString(1, subject.getDescription());
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                subject.setId(rs.getInt("id"));
            }else {
                throw new SQLDataException("something went wrong");
            }
        }
        LOGGER.info("person {0} {1} was created", subject.getId(), subject.getDescription());

    }


    @Override
    public Subject getSubject(int id) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(GET_SUBJECT_SQL_TEMPLATE)) {
            statement.setString(1, Integer.toString(id));
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Subject subject = new Subject(rs.getInt("id"), rs.getString("description"));
                return subject;
            }else {
                throw new SQLDataException("no such subject");
            }
        }
    }


    @Override
    public void updateSubject(Subject subject) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT_SQL_TEMPLATE)) {
            statement.setString(1, Integer.toString(subject.getId()));
            statement.execute();
        }
        LOGGER.info("person {0} {1} was updated", subject.getId(), subject.getDescription());

    }


    @Override
    public void deleteSubject(Subject subject) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT_SQL_TEMPLATE)) {
            statement.setString(1, Integer.toString(subject.getId()));
            statement.execute();
        }
        LOGGER.info("person {0} {1} was deleted", subject.getId(), subject.getDescription());

    }


    @Override
    public ArrayList<Subject> getAllSubjects() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(GET_SUBJECT_SQL_TEMPLATE)) {
            ResultSet rs = statement.executeQuery();

            ArrayList<Subject> ls = new ArrayList<>();

            while (rs.next()) {
                ls.add(new Subject(rs.getInt("id"), rs.getString("description")));
            }

            return ls;
        }
    }


    @Override
    public Collection<Subject> getSubjectsByPerson(Person person) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_SUBJECT_BY_PERSON_TEMPLATE)) {
            statement.setString(1, Integer.toString(person.getId()));
            ResultSet rs = statement.executeQuery();

            ArrayList<Subject> ls = new ArrayList<>();

            while (rs.next()) {
                ls.add(new Subject(rs.getInt("id"), rs.getString("description")));
            }

            return ls;
        }
    }


    @Override
    public void joinToCourse(Subject subject, Person... persons) throws SQLException {

        StringBuilder sb = new StringBuilder();
        sb.append(JOIN_TO_COURSE_TEMPLATE );
        for (Person person : persons) {
            sb.append( "("+subject.getId()+", "+person.getId()+"),");
        }
        sb.deleteCharAt(sb.length()-1);

        try (PreparedStatement statement = connection.prepareStatement(sb.toString())) {
            statement.execute();
        }
        LOGGER.info("people {0} were joined to course {1}", Arrays.toString(persons), subject);

    }
}
