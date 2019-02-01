package ru.inno.lec12.HW;

import ru.inno.lec12.HW.dao.CourseDAO;
import ru.inno.lec12.HW.dao.CourseDAOImpl;
import ru.inno.lec12.HW.entity.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://localhost:5432/ilya";

        Connection connection = DriverManager.getConnection(url);
        System.out.println(connection);

        CourseDAO dao = new CourseDAOImpl(connection);

        Person person = new Person();
        person.setName("John Smith");
        person.setBirthDate(System.currentTimeMillis());
        dao.createPerson(person);

        // в конце не забыть закрыть подключение
    }
}
