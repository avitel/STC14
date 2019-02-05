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

        HashSet<String> dictionary = new HashSet<>(Arrays.asList("test", "asdf", "test", "right", "hjk"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("sadfsdfsdf".getBytes())));
        ArrayList<BufferedReader> array = new ArrayList<>();
        array.add(reader);

        parser = new Parser(array, dictionary);
    }



    @Test
    void parseFile() {

        ArrayList<String> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("This is right sentence.".getBytes())));

        try {
            parser.parseFile(reader, result);
        } catch (IOException e) {
            e.printStackTrace();
        }

       assertEquals("This is right sentence.", (result.size()>0) ? result.get(0) : "", "This is right sentence.");


        int initSize = result.size();
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("This is bad sentence.".getBytes())));
        try {
            parser.parseFile(reader, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(0, result.size()-initSize, "This is bad sentence");
    }



    @Test
    void sentenceExistInDictionary() {

        assertEquals(true, parser.sentenceExistInDictionary(new StringBuilder("fjfjfj yijrigjr ccvcf test vndsi sdkjvdsk osdcj")), "exist in dictionary positive test");
        assertEquals(false, parser.sentenceExistInDictionary(new StringBuilder("fjfjfj yijrigjr ccvcf njubhuvgy vndsi sdkjvdsk osdcj")), "exist in dictionary negative test");
    }
}