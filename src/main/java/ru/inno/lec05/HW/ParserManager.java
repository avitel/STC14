package ru.inno.lec05.HW;

import ru.inno.lec04.HW.MyUtilities;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public class ParserManager implements Occurencies{

    private int numberOfThreads;

    public void setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }



    /**
     * Finds occurencies of words of dictionary to sentences of files
     * Puts result to file outputfile
     */
    public void getOccurencies(String[] arrFiles, String[] dictionaryInArray, String outputFile) throws InterruptedException, IOException {

        ArrayList<BufferedReader> readers = createReaders(arrFiles);

        ArrayList<ArrayList<BufferedReader>>readersArrays = divideArray(readers, numberOfThreads);

        List<Thread> threads = startThreads(readersArrays, new HashSet<>(Arrays.asList(dictionaryInArray)));

        for (Thread thread : threads) {
            thread.join();
        }

        List<String> result =  Parser.getFinalresult();

        MyUtilities.saveCollectionToFile(Parser.getFinalresult(), outputFile);
    }


    protected BufferedReader getBufferedReader(String filename) throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
    }


    /**
     * creates array of Buffered readers
     * @param arrFiles
     * @return
     * @throws FileNotFoundException
     */
    public ArrayList<BufferedReader> createReaders(String[] arrFiles) throws FileNotFoundException {

        ArrayList<BufferedReader> readers = new ArrayList<>();
        for (String filename : arrFiles) {
            readers.add(getBufferedReader(filename));
        }
        return readers;
    }



    /**
     * Loads dictionary from reader
     * @param reader
     * @param limit - max number of words when load stops
     * @return HashSet
     */
    public  String[] getDictionaryFromFile(BufferedReader reader, int limit) throws IOException {
        String strLine;
        HashSet<String> dictionary = new HashSet<>();
        while ((strLine = reader.readLine()) != null){
            dictionary.add(strLine);
            if (dictionary.size() == limit) {
                break;
            }
        }
        reader.close();

        return dictionary.toArray(new String[dictionary.size()]);
    }



    /**
     * Gets array of filenames on specified dirname and mask
     *
     * @param dir
     * @param mask
     * @return array of full filenames contained in 'dirname' with mask 'inFileName'
     */
    public String[] getFileArray(File dir, String mask){

        String[] arr = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir1, String name) {
                return name.startsWith(mask);
            }
        });

        for (int i = 0; i < arr.length; i++) {
            arr[i] = dir.getPath() + "/" + arr[i];
        }

        return arr;
    }



    /**
     * Divides input array to denominator
     *
     * @param readers Array
     * @param denominator
     * @return ArrayList of List
     */
    public ArrayList<ArrayList<BufferedReader>> divideArray(ArrayList<BufferedReader> readers, int denominator) {

        if (denominator == 0){
            denominator = 1;
        }

        int numberPerOne = (int)Math.round(readers.size() / denominator);

        int j = 0;

        ArrayList<ArrayList<BufferedReader>> outerArray = new ArrayList<>();

        for (int i = 1; i <= denominator; i++) {

            ArrayList<BufferedReader> innerArray = new ArrayList<>();

            while (innerArray.size() < numberPerOne) {
                if (j >= readers.size()) {
                    break;
                }
                innerArray.add(readers.get(j++));
            }

             //the last must take the rest
            if (i == denominator) {
                while (j < readers.size()) {
                    innerArray.add(readers.get(j++));
                }
            }
            if (innerArray.size() > 0) {
                outerArray.add(innerArray);
            }
        }
        return outerArray;
    }


    /**
     *
     * @param runnable
     * @return
     */
    protected Thread getThread(Runnable runnable){
        return new Thread(runnable);
    }



    /**
     * Starts threads for parsing array of files
     *
     * @param arrays ArrayList of arrays of files
     * @return ArrayList of started threads
     */
    protected ArrayList<Thread> startThreads(ArrayList<ArrayList<BufferedReader>> arrays, HashSet<String> dictionary) {

        ArrayList<Thread> threads = new ArrayList<>();

        for (ArrayList<BufferedReader> array : arrays) {
            Parser parser = new Parser(array, dictionary);
            Thread thread = getThread(parser);
            thread.start();
            threads.add(thread);
        }
        return threads;
    }
}