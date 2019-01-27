package ru.inno.lec04.HW;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class App {

    public static void main(String[] args) {

        Properties property = new Properties();
        try (FileInputStream fs = new FileInputStream("./src/ru/inno/lec04/HW/conf.md")) {
            property.load(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int numberOfFiles = Integer.parseInt(property.getProperty("numberOfFiles"));
        int numberOfParagraphs = Integer.parseInt(property.getProperty("numberOfParagraphs"));
        int numberOfSentences = Integer.parseInt(property.getProperty("numberOfSentences"));
        int numberOfWords = Integer.parseInt(property.getProperty("numberOfWords"));

        TextGen tg = new TextGen(numberOfFiles, numberOfParagraphs, numberOfSentences, numberOfWords);

        tg.loadDictionaryFromBook("./Files/book");

        tg.saveDictionary();

        tg.makeFiles("./Files/testGenFiles");
    }
}
