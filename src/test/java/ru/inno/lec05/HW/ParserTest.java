package ru.inno.lec05.HW;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        try {
            parser.parseFile(new BufferedReader(new StringReader("This is right sentence.")), result);
        } catch (IOException e) {
            e.printStackTrace();
        }

       assertEquals("This is right sentence.", (result.size()>0) ? result.get(0) : "", "This is right sentence.");


        int initSize = result.size();
        try {
            parser.parseFile(new BufferedReader(new StringReader("This is bad sentence.")), result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(0, result.size()-initSize, "This is bad sentence");


        initSize = result.size();
        try {
            parser.parseFile(new BufferedReader(new StringReader("test string")), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(0, result.size()-initSize, "");


        //negative
        assertThrows(NullPointerException.class, () -> parser.parseFile(null, new ArrayList<>()));

    }


    @Test
    void sentenceExistInDictionary() {

        assertEquals(true, parser.sentenceExistInDictionary(new StringBuilder("fjfjfj yijrigjr ccvcf test vndsi sdkjvdsk osdcj")), "exist in dictionary positive test");
        assertEquals(false, parser.sentenceExistInDictionary(new StringBuilder("fjfjfj yijrigjr ccvcf njubhuvgy vndsi sdkjvdsk osdcj")), "exist in dictionary negative test");
        assertEquals(false, parser.sentenceExistInDictionary(new StringBuilder("")), "");

        //negative
        assertThrows(NullPointerException.class, () -> parser.sentenceExistInDictionary(null));
    }



    @Test
    void run() {

        Parser mockParser = mock(Parser.class);

        doCallRealMethod().when(mockParser).run();
        doCallRealMethod().when(mockParser).setReaders(isA(List.class));
        try {
            doNothing().when(mockParser).parseFile(isA(BufferedReader.class), isA(ArrayList.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader testBr = new BufferedReader(new StringReader("testString"));
        ArrayList<BufferedReader> testAr = new ArrayList<>();
        testAr.add(testBr);

        mockParser.setReaders(testAr);
        mockParser.run();


        try {
            verify(mockParser, times(parser.getReaders().size())).parseFile(testBr, new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}