package ru.inno.lec12.HW;

import ru.inno.lec12.HW.dao.PersonDAO;
import ru.inno.lec12.HW.dao.PersonDAOImpl;
import ru.inno.lec12.HW.entity.Person;
import ru.inno.lec12.HW.entity.Subject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://localhost:5432/ilya";

        Connection connection = DriverManager.getConnection(url);
        System.out.println(connection);

        PersonDAO dao = new PersonDAOImpl(connection);

        Person person = new Person("John Smith", System.currentTimeMillis());
        dao.createPerson(person);
        System.out.println(person);

        dao.joinToCourse(person, new Subject(1, "Phisic"), new Subject(2, "Literature"));


        // в конце не забыть закрыть подключение
    }
}
