package ru.inno.lec09.HW;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Worker implements Runnable {

    private List<String> inFiles;
    private HashSet<String> dictionary;
    private CollectData c;
    private ArrayList<String> localRes = new ArrayList<>();


    public Worker(List<String> inFiles, CollectData c) {
        this.inFiles = inFiles;
        this.dictionary = c.getDictionary();
        this.c = c;
    }



    @Override
    public void run() {

        long start = System.currentTimeMillis();

        long averageParsingTime = 0l;
        for (String filename : inFiles) {
            long startP = System.currentTimeMillis();

            parseFile(filename);

            long finishP = System.currentTimeMillis();
            long timeConsumedMillis = finishP - startP;
            averageParsingTime = averageParsingTime + timeConsumedMillis;
        }

        c.addAllToArrRes(localRes);

        String threadName = Thread.currentThread().getName();
        averageParsingTime = averageParsingTime / inFiles.size();
        System.out.println("thread "+ threadName+" averge parsing one file time : "+ averageParsingTime + " ms. ("+inFiles.size()+" files)");

        long finish1 = System.currentTimeMillis();
        long timeConsumedMillis = finish1 - start;
        System.out.println("thread "+ threadName+" total processing time : "+ timeConsumedMillis + " ms");

        long finish2 = System.currentTimeMillis();
        long timeConsumedMillis2 = finish2 - finish1;
        System.out.println("thread "+ threadName+" transfer data time : "+ timeConsumedMillis2 + " ms");
    }



    /**
     * Parses file and add result to CollectData object
     * @param filename
     */
    public void parseFile(String filename){

        try {
            String ls = Files.lines(Paths.get(filename)).reduce((s1, s2) -> s1 + s2).orElse("");

            int letter;
            StringBuilder builder = new StringBuilder();

            for (int i = 0 ; i < ls.length(); i++){
                letter = ls.charAt(i);
                if (letter == '\n' | letter == '\r') {
                    letter = ' ';
                }

                builder.append((char) letter);

                if (letter == '.' || letter == '!' || letter == '?') {
                    if (builder.length() != 1) {
                        if (existInSentence(builder)) {
                            localRes.add(builder.toString());
                        }
                    }
                    builder = new StringBuilder();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


//        try   (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))){
//            int letter;
//            StringBuilder builder = new StringBuilder();
//            while ((letter = br.read()) != -1){
//                if (letter == '\n' | letter == '\r') {
//                    letter = ' ';
//                }
//
//                builder.append((char)letter);
//
//                if (letter == '.' || letter == '!' || letter == '?' ){
//                    if (builder.length() != 1) {
//                        if (existInSentence(builder)) {
//                            //c.addToArrRes(builder.toString());
//                            localRes.add(builder.toString());
//                        }
//                    }
//                    builder = new StringBuilder();
//                }
//            }
//        }catch (IOException e){
//            System.out.println("file parsing error");
//        }
    }



    /**
     * Seeks intersection words of sentence and words of dictionary
     *
     * @param sentence
     * @return boolean
     */
    private boolean existInSentence(StringBuilder sentence){

        String[] words = sentence.toString().replaceAll(",|\\.|-|;|!|–|:|\\?","").split(" ");
        for (String word : words) {
            if (dictionary.contains(word)) return true;
        }
        return false;
    }
}

