package ru.inno.lec07.HW;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class App {

    public static void main(String[] args) {

        Properties property = new Properties();
        try (FileInputStream fs = new FileInputStream("./src/main/java/ru/inno/lec07/HW/conf.md")) {
            property.load(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CompilerOnFly c = new CompilerOnFly(property.getProperty("className"),property.getProperty("packagePath"));
        c.doCompileOnFly();
    }


}
