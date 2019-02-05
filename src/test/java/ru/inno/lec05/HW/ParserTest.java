package ru.inno.lec05.HW;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private Parser parser;



    @BeforeEach
    void setUp() {

        HashSet<String> dictionary = new HashSet<>(Arrays.asList("qwer", "asdf", "test", "yui", "hjk"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("sadfsdfsdf".getBytes())));
        ArrayList<BufferedReader> array = new ArrayList<>();
        array.add(reader);

        parser = new Parser(array, dictionary);
    }




    @Test
    void parseFile() {

        ArrayList<String> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("This is test sentence".getBytes())));

        try {
            parser.parseFile(reader, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("This is test sentence", result.get(0), "parse file test 1 passed");



        BufferedReader reader1 = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("This is another sentence".getBytes())));
        try {
            parser.parseFile(reader, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(1, result.size(), "parse file test 2 passed");
    }


    @Test
    void sentenceExistInDictionary() {

        assertEquals(true, parser.sentenceExistInDictionary(new StringBuilder("fjfjfj yijrigjr ccvcf qwer vndsi sdkjvdsk osdcj")), "occurency test 1 passed");
        assertEquals(false, parser.sentenceExistInDictionary(new StringBuilder("fjfjfj yijrigjr ccvcf njubhuvgy vndsi sdkjvdsk osdcj")), "occurency test 2 passed");
    }
}