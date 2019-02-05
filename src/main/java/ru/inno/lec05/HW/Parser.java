package ru.inno.lec05.HW;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * class for parsing data from readers using dictionary
 * Puts result to finalresult
 */
public class Parser implements Runnable {

    private List<BufferedReader> readers;
    private HashSet<String> dictionary;
    private static List<String> finalresult = new CopyOnWriteArrayList<>();


    public Parser(List<BufferedReader> readers, HashSet<String> dictionary) {
        this.readers = readers;
        this.dictionary = dictionary;
    }


    public static List<String> getFinalresult() {
        return finalresult;
    }



    @Override
    public void run() {

        long start = System.currentTimeMillis();

        long averageParsingTime = 0L;

        ArrayList<String> result = new ArrayList<>();

        for (BufferedReader reader : readers) {
            long startP = System.currentTimeMillis();

            try {
                parseFile(reader, result);
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        if (e != null) {
                            e.addSuppressed(e);
                        }
                        e.printStackTrace();
                    }
                }
            }

            long finishP = System.currentTimeMillis();
            long timeConsumedMillis = finishP - startP;
            averageParsingTime = averageParsingTime + timeConsumedMillis;
        }

        finalresult.addAll(result);

        String threadName = Thread.currentThread().getName();
        averageParsingTime = averageParsingTime / readers.size();
        System.out.println("thread " + threadName + " averge parsing one file time : " + averageParsingTime + " ms. (" + readers.size() + " files)");

        long finish1 = System.currentTimeMillis();
        long timeConsumedMillis = finish1 - start;
        System.out.println("thread " + threadName + " total processing time : " + timeConsumedMillis + " ms");
    }



    /**
     * Parses data in reader add put sentences to result
     * @param reader
     */
    public void parseFile(BufferedReader reader, ArrayList<String> result) throws IOException {

        StringBuilder builder = new StringBuilder();

        int symbol;
        while ((symbol = reader.read()) != -1){
            if (symbol == '\n' || symbol == '\r') {
                symbol = ' ';
            }

            builder.append((char)symbol);

            if (symbol == '.' || symbol == '!' || symbol == '?' ){
                if (builder.length() != 1) {
                    if (sentenceExistInDictionary(builder)) {
                        result.add(builder.toString());
                        builder.delete(0, builder.length());
                    }
                }
            }
        }
    }



    /**
     * Seeks intersection words of sentence and words of dictionary
     *
     * @param sentence
     * @return boolean
     */
    protected boolean sentenceExistInDictionary(StringBuilder sentence){

        String[] words = sentence.toString().replaceAll(",|\\.|-|;|!|–|:|\\?","").split(" ");
        for (String word : words) {
            if (dictionary.contains(word)) return true;
        }
        return false;
    }
}

