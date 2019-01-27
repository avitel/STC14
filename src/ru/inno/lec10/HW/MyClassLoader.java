package ru.inno.lec10.HW;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String pattern = "ru.inno.lec10.HW.SomeClass";

        if (name.startsWith(pattern)) {
            try {
                String ind = name.replaceAll(pattern,"");
                byte[] bytes = Files.readAllBytes(Paths.get("./Files/Class/SomeClass" + ind + ".class"));
                return  defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return super.findClass(name);
    }
}
