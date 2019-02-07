package ru.inno.lec12.HW;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.lec12.HW.dao.PersonDAO;
import ru.inno.lec12.HW.dao.PersonDAOImpl;
import ru.inno.lec12.HW.dao.SubjectDAOImpl;
import ru.inno.lec12.HW.entity.Person;
import ru.inno.lec12.HW.entity.Subject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/ilya";

        try (Connection connection = DriverManager.getConnection(url)) {
            System.out.println(connection);

            PersonDAO dao = new PersonDAOImpl(connection);

            Person person = new Person("John Smith", System.currentTimeMillis());
            dao.createPerson(person);
            System.out.println(person);

            dao.joinToCourse(person, new Subject(1, "Phisic"), new Subject(2, "Literature"));

        } catch (SQLException e) {
            LOGGER.error(e.toString());
            e.printStackTrace();
        }

    }
}
