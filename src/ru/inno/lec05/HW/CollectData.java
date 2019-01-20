package ru.inno.lec05.HW;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class CollectData {

    /**
     * this variable for read only. there are no necessary to synchronize
     */
    public HashSet<String> dictionary = new HashSet<String>();


    /**
     *  this var for multi-threaded write. synchronized.
     */
    public volatile ArrayList<ArrayList<String>> arrRes = new ArrayList<ArrayList<String>>();


    /**
     * number of threads
     */
    public static int numbetOfThreads = 100;



    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        CollectData cd = new CollectData();
        cd.loadDictionary("./Files/dictionary.txt", 100);

        List<Thread> threads = cd.startParsing(numbetOfThreads, getInFilesNames("./Files", "testGenFiles"));

        long finish1 = System.currentTimeMillis();
        long timeConsumedMillis = finish1 - start;
        System.out.println("starting worker time : "+ timeConsumedMillis + " ms");

        /**
         * wait for finishing
         */
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        }catch (InterruptedException e){
            System.out.println("thread waiting was interrupted");
        }

        cd.saveFile("./Files/res.txt");

        long finish2 = System.currentTimeMillis();
        long timeConsumedMillis2 = finish2 - start;
        System.out.println("total time : "+ timeConsumedMillis2 + " ms");
    }


    /**
     *
     * @param dirname
     * @param inFileName
     * @return array of full filenames contained in 'dirname' with mask 'inFileName'
     */
    private static String[] getInFilesNames(String dirname, String inFileName){
        File dir = new File(dirname);

        String[] inFiles = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(inFileName);
            }
        });
        for (int i = 0; i < inFiles.length; i++) {
            inFiles[i] = dirname + "/" + inFiles[i];
        }
        return inFiles;
    }


    /**
     *
     * @param filename
     * @param limit - max number of words
     */
    public  void loadDictionary(String filename, int limit){
        try (FileInputStream fstream = new FileInputStream(filename)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null){
                dictionary.add(strLine);
                if (dictionary.size() == limit) break;
            }

        }catch (IOException e){
            System.out.println("load dictionary error");
        }
    }


    /**
     * Only variant when 1 file is processed by 1 thread
     *
     * @param numberOfThreads
     * @param inFiles
     */
    public ArrayList<Thread> startParsing(int numberOfThreads, String[] inFiles) {

        numberOfThreads = Math.min(numberOfThreads, inFiles.length);
        int numberOfFilesPerThread = (int)Math.round(inFiles.length / numberOfThreads);
        int index = 0;

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= numberOfThreads; i++) {

            List<String> arr = new ArrayList<String>();

            while (arr.size() < numberOfFilesPerThread) {
                if (index >= inFiles.length) break;
                arr.add(inFiles[index++]);
            }

            /**
             * the last thread must catch the rest
             */
            if (i == numberOfThreads) {
                while (index < inFiles.length) {
                    arr.add(inFiles[index++]);
                }
            }

            Thread thread = new Worker(arr, this);
            thread.start();
            threads.add(thread);
        }

        return threads;
    }


    /**
     *
     * @param filename
     */
    public void saveFile(String filename){
        try(FileWriter writer = new FileWriter(filename, false)) {
            for (ArrayList<String> ara : arrRes) {
                for (String sentence : ara) {
                    writer.write(sentence);
                    writer.write('\n');
                }
            }

        }catch (IOException e){
            System.out.println("save result error");
        }
    }
}