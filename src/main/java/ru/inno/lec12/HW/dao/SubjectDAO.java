package ru.inno.lec12.HW.dao;

import ru.inno.lec12.HW.entity.Person;
import ru.inno.lec12.HW.entity.Subject;

import java.sql.SQLException;
import java.util.Collection;

public interface SubjectDAO {

    int createSubject(Subject subject);

    void updateSubject(Subject subject);

    void deleteSubject(Subject subject);

    Collection<Subject> getAllSubjects();

    Collection<Subject> getSubjectsByPerson(Person person);

    void linkToCourse(Person person, Subject subject);

    void linkToCourse(Subject subject, Person... person);

}

