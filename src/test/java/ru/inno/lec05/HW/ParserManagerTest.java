package ru.inno.lec05.HW;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParserManagerTest {

    private ParserManager parserManager;


    @BeforeEach
    void setUp() {
        parserManager = new ParserManager();
        parserManager.setNumberOfThreads(1);

    }

    @Test
    void createReaders() {
    }


    @Test
    void getDictionaryFromFile() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("line1 \n line2 \n line3".getBytes())));

        String[] res1 = null;
        try {
            res1 = parserManager.getDictionaryFromFile(reader,1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(3, res1.length, "0 readers, denominator  = 0");
    }



    @Test
    void getFileArray() {

        File fileObject = mock(File.class);

        when(fileObject.list(any())).thenReturn(new String[] {"file1","file2"});
        when(fileObject.getPath()).thenReturn("MyPath");

        String[] arrFiles = parserManager.getFileArray(fileObject , "testGenFiles");
        assertEquals(2, arrFiles.length, "file array with 2 items");
        assertEquals("MyPath/file2", arrFiles[1], "test 2 item of file array");
    }


    @Test
    void divideArray() {

        ArrayList<BufferedReader> readers = new ArrayList<>();
        ArrayList<?> res1 = parserManager.divideArray(readers,0);
        assertEquals(0, res1.size(), "0 readers, denominator  = 0");

        readers.add(new BufferedReader(new InputStreamReader(new ByteArrayInputStream("test".getBytes()))));
        ArrayList<?> res2 = parserManager.divideArray(readers,1);
        assertEquals(1, res2.size(), "1 readers, denominator = 1");

        readers.add(new BufferedReader(new InputStreamReader(new ByteArrayInputStream("occurency".getBytes()))));
        ArrayList<?> res3 = parserManager.divideArray(readers,2);
        assertEquals(2, res3.size(), "2 readers, denominator = 2");
    }


    @Test
    void startThreads() {
    }
}