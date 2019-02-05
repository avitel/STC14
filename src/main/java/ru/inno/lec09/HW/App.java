package ru.inno.lec09.HW;

import ru.inno.lec05.HW.ParserManager;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        String PATH = "./src/main/resources/Files/";
        int maxNumberOfThreads = 10;
        int limitDictionary = 100;

        long start = System.currentTimeMillis();

        ParserManager parserManager = new ParserManagerLambda();

        String[] dictionary = parserManager.getDictionaryFromFile(PATH + "dictionary.txt", limitDictionary);

        String[] arrFiles = parserManager.getFileArray(PATH , "testGenFiles");

        int numberOfThreads = Math.min(maxNumberOfThreads, arrFiles.length);

        parserManager.setNumberOfThreads(numberOfThreads);

        parserManager.getOccurencies(arrFiles, dictionary, PATH + "occurences.txt" );

        long finish2 = System.currentTimeMillis();
        long timeConsumedMillis2 = finish2 - start;
        System.out.println("total time : "+ timeConsumedMillis2 + " ms");
    }
}
