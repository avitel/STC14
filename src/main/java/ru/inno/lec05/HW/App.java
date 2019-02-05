package ru.inno.lec05.HW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        String PATH = "./src/main/resources/Files/";
        int maxNumberOfThreads = 10;
        int limitDictionary = 100;

        long start = System.currentTimeMillis();

        ParserManager parserManager = new ParserManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(PATH + "dictionary.txt")));

        String[] dictionary = parserManager.getDictionaryFromFile(reader, limitDictionary);

        String[] arrFiles = parserManager.getFileArray(PATH , "testGenFiles");

        int numberOfThreads = Math.min(maxNumberOfThreads, arrFiles.length);

        parserManager.setNumberOfThreads(numberOfThreads);

        parserManager.getOccurencies(arrFiles, dictionary, PATH + "occurences.txt" );

        long finish2 = System.currentTimeMillis();
        long timeConsumedMillis2 = finish2 - start;
        System.out.println("total time : "+ timeConsumedMillis2 + " ms");
    }
}
