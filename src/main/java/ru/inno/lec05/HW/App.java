package ru.inno.lec05.HW;


import java.io.*;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        String PATH = "./src/main/resources/Files/";
        int maxNumberOfThreads = 10;
        int limitDictionary = 100;

        ParserManager parserManager = new ParserManager(maxNumberOfThreads);

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(PATH + "dictionary.txt")));

        parserManager.getOccurencies(parserManager.getFileArray(new File(PATH) , "testGenFiles"), parserManager.getDictionaryFromFile(reader, limitDictionary), PATH + "occurences.txt" );

    }
}
