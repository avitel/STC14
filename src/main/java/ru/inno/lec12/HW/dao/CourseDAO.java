package ru.inno.lec12.HW.dao;

import ru.inno.lec12.HW.entity.Person;
import ru.inno.lec12.HW.entity.Subject;

import java.sql.SQLException;
import java.util.Collection;

public interface CourseDAO {
    
    Collection<Subject> getAllSubjects();

    Collection<Person> getAllPersons();
    
    Collection<Person> getPersonsBySubject(Subject subject);
    
    Collection<Subject> getSubjectsByPerson(Person person);
    
    void linkToCourse(Person person, Subject subject);

    // TODO: 29.01.2019 Реализовать! 
    //void linkToCourse(Person person, Subject... subject);
    //void linkToCourse(Subject subject, Person... person);

    // TODO: 29.01.2019
    void createPerson(Person person) throws SQLException;

    void updatePerson(Person person);

    void deletePerson(Person person);

    void createSubject(Subject subject);
}
