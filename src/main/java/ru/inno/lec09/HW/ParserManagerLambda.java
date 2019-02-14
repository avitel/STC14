package ru.inno.lec09.HW;

import ru.inno.lec05.HW.ParserManager;
import ru.inno.lec05.HW.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.IntStream;

public class ParserManagerLambda extends ParserManager {


    public ParserManagerLambda(int numberOfThreads) {
        super(numberOfThreads);
    }

    @Override
    public String[] getFileArray(File dir, String mask) {

        String[] arr = dir.list((dir1, name) -> name.startsWith(mask));
        IntStream.range(0, arr.length).forEach(i -> arr[i] = dir.getPath() + "/" + arr[i]);
        return arr;
    }


    @Override
    protected ArrayList<Thread> startThreads(ArrayList<ArrayList<BufferedReader>> arrays, HashSet<String> dictionary) {
        ArrayList<Thread> threads = new ArrayList<>();

        for (ArrayList<BufferedReader> readers : arrays) {
            Parser parser = new ParserLambda(readers, dictionary);
            Thread thread = new Thread(parser);
            thread.start();
            threads.add(thread);
        }
        return threads;
    }
}
