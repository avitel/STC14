package ru.inno.lec12.HW.dao;

import ru.inno.lec12.HW.entity.Person;
import ru.inno.lec12.HW.entity.Subject;

import java.sql.SQLException;
import java.util.Collection;

public interface SubjectDAO {

    void createSubject(Subject subject) throws SQLException;

    Subject getSubject(int id) throws SQLException;

    void updateSubject(Subject subject) throws SQLException;

    void deleteSubject(Subject subject) throws SQLException;

    Collection<Subject> getAllSubjects() throws SQLException;

    Collection<Subject> getSubjectsByPerson(Person person) throws SQLException;

    void joinToCourse(Subject subject, Person... person) throws SQLException;

}

