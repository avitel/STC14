package ru.inno.lec05.HW;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;


public class CollectData {

    private HashSet<String> dictionary = new HashSet<>();

    private List<String> arrRes = new CopyOnWriteArrayList<>();

    private int maxNumberOfThreads;

    public void setDictionary(HashSet<String> dictionary) {
        this.dictionary = dictionary;
    }

    public void setMaxNumberOfThreads(int maxNumberOfThreads) {
        this.maxNumberOfThreads = maxNumberOfThreads;
    }

    public HashSet<String> getDictionary() {
        return dictionary;
    }

    public List<String> getArrRes() {
        return arrRes;
    }

    public int getMaxNumberOfThreads() {
        return maxNumberOfThreads;
    }

    public void addToArrRes(String str){
        arrRes.add(str);
    }

    public void addAllToArrRes(ArrayList<String> arr){
        arrRes.addAll(arr);
    }


    /**
     * Gets array of filenames on specified dirname and mask
     *
     * @param dirname
     * @param mask
     * @return array of full filenames contained in 'dirname' with mask 'inFileName'
     */
    public static String[] getInFilesNames(String dirname, String mask){
        File dir = new File(dirname);

        String[] inFiles = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(mask);
            }
        });
        for (int i = 0; i < inFiles.length; i++) {
            inFiles[i] = dirname + "/" + inFiles[i];
        }
        return inFiles;
    }


    /**
     * Loads dictionary from file
     * @param filename
     * @param limit - max number of words when load stops
     */
    public  void loadDictionary(String filename, int limit){
        try (FileInputStream fstream = new FileInputStream(filename)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null){
                dictionary.add(strLine);
                if (dictionary.size() == limit) {
                    break;
                }
            }

        }catch (IOException e){
            System.out.println("load dictionary error");
        }
    }


    /**
     * Starts threads for parsing array of files
     * Only variant when 1 file is processed by 1 thread
     *
     * @param inFiles
     * @return List  List of started threads
     */
    public ArrayList<Thread> startParsing(String[] inFiles) {

        int numberOfThreads = Math.min(maxNumberOfThreads, inFiles.length);
        int numberOfFilesPerThread = (int)Math.round(inFiles.length / numberOfThreads);
        int index = 0;

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= numberOfThreads; i++) {

            List<String> arr = new ArrayList<>();

            while (arr.size() < numberOfFilesPerThread) {
                if (index >= inFiles.length) {
                    break;
                }
                arr.add(inFiles[index++]);
            }

             //the last thread must take the rest
            if (i == numberOfThreads) {
                while (index < inFiles.length) {
                    arr.add(inFiles[index++]);
                }
            }

            Worker worker = new Worker(arr, this);
            Thread thread = new Thread(worker);
            thread.start();
            threads.add(thread);
        }
        return threads;
    }
}