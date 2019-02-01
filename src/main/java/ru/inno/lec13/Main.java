package ru.inno.lec13;

//import org.apache.log4j.Logger;
//import org.apache.log4j.spi.LoggerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.slf4j.LoggerFactory.*;

public class Main {

    private static final Logger LOGGER = getLogger(Main.class);

    public static void main(String[] args) {

        LOGGER.info("Запуск программы с аргументами " + Arrays.toString(args));

        if (args.length<1){
            System.out.println("java... <login>");
            LOGGER.error("that's all. no parameters");
            return;
        }
    }
}
