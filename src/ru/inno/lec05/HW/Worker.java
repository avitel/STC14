package ru.inno.lec05.HW;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Worker extends Thread {

    private List<String> inFiles;
    private HashSet<String> dictionary;
    private CollectData c;
    private ArrayList<String> res = new ArrayList<String>();

    public Worker(List<String> inFiles, CollectData c) {
        this.inFiles = inFiles;
        this.dictionary = c.dictionary;
        this.c = c;
    }

    @Override
    public void run() {

        long start = System.currentTimeMillis();

        long averageParsingTime = 0l;
        for (String filename : inFiles) {
            long startP = System.currentTimeMillis();

            res.add("----------------------------------------------------------------");
            res.add("THREAD " + getName() + " got " + filename);
            res.add("----------------------------------------------------------------");

            //System.out.println("tread " +getName() + " got "+ filename);
            parseFile(filename);

            long finishP = System.currentTimeMillis();
            long timeConsumedMillis = finishP - startP;
            averageParsingTime = averageParsingTime + timeConsumedMillis;
        }

        averageParsingTime = averageParsingTime / inFiles.size();
        System.out.println("thread "+ getName()+" averge parsing one file time : "+ averageParsingTime + " ms. ("+inFiles.size()+" files)");

        long finish1 = System.currentTimeMillis();
        long timeConsumedMillis = finish1 - start;
        System.out.println("thread "+ getName()+" total processing time : "+ timeConsumedMillis + " ms");

        synchronized (c){
            c.arrRes.add(res);
        }

        long finish2 = System.currentTimeMillis();
        long timeConsumedMillis2 = finish2 - finish1;
        System.out.println("thread "+ getName()+" transfer data time : "+ timeConsumedMillis2 + " ms");
    }

    public void parseFile(String filename){
        try (FileInputStream fstream = new FileInputStream(filename)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            int letter;
            StringBuilder builder = new StringBuilder();
            while ((letter = br.read()) != -1){
                if (letter == '\n') letter = ' ';

                builder.append((char)letter);

                if (letter == '.' || letter == '!' || letter == '?' ){
                    if (builder.length() != 1) {
                        if (existInSentence(dictionary, builder)) addSentenceToResult(builder);
                    }
                    builder = new StringBuilder();
                }
            }

        }catch (IOException e){
            System.out.println("file parsing error");
        }
    }


    private boolean existInSentence(HashSet<String> dictionary, StringBuilder builder){

        String[] words = builder.toString().replaceAll(",|\\.|-|;|!|–|:|\\?","").split(" ");

        for (String word : words) {
            if (dictionary.contains(word)) return true;
        }

        return false;
    }


    private void addSentenceToResult(StringBuilder builder){
        res.add(builder.toString());
    }

}

