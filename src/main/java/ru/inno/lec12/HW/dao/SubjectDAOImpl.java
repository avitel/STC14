package ru.inno.lec12.HW.dao;

import ru.inno.lec12.HW.entity.Person;
import ru.inno.lec12.HW.entity.Subject;

import java.sql.Connection;
import java.util.Collection;

public class SubjectDAOImpl implements SubjectDAO {

    public static final String INSERT_SUBJECT_SQL_TEMPLATE =
            "insert into subject (description) values (?)";

    public static final String UPDATE_SUBJECT_SQL_TEMPLATE =
            "update subject set description = ?, where id = ?";

    public static final String DELETE_SUBJECT_SQL_TEMPLATE =
            "delete from subject where id = ?";

    public static final String GET_ALL_SUBJECT =
            "select * from subject";

    public static final String GET_ALL_SUBJECT_BY_SUBJECT =
            "select * from subject p inner join course c on p.id = c.subject_id where id = ?";
    
    
    private final Connection connection;

    public SubjectDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int createSubject(Subject subject) {
        return 0;
    }

    @Override
    public void updateSubject(Subject subject) {

    }

    @Override
    public void deleteSubject(Subject subject) {

    }

    @Override
    public Collection<Subject> getAllSubjects() {
        return null;
    }

    @Override
    public Collection<Subject> getSubjectsByPerson(Person subject) {
        return null;
    }

    @Override
    public void linkToCourse(Person person, Subject subject) {

    }

    @Override
    public void linkToCourse(Subject subject, Person... person) {

    }
}
