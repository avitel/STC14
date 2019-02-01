package ru.inno.lec07.HW;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        if ("ru.inno.lec07.HW.SomeClass".equals(name)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get("./SomeClass.class"));
                return  defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return super.findClass(name);
    }
}
