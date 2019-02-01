package ru.inno.lec09.HW;

import ru.inno.lec04.HW.MyUtilities;

import java.util.List;


public class App {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        CollectData cd = new CollectData();
        cd.loadDictionary("./src/main/resources/Files/dictionary.txt", 100);
        cd.setMaxNumberOfThreads(10);

        List<Thread> threads = cd.startParsing(cd.getInFilesNames("./src/main/resources/Files", "testGenFiles"));

        long finish1 = System.currentTimeMillis();
        long timeConsumedMillis = finish1 - start;
        System.out.println("starting worker time : "+ timeConsumedMillis + " ms");

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        }catch (InterruptedException e){
            System.out.println("thread waiting was interrupted");
        }

        MyUtilities.saveCollectionToFile(cd.getArrRes(),"./src/main/resources/Files/res.txt");

        long finish2 = System.currentTimeMillis();
        long timeConsumedMillis2 = finish2 - start;
        System.out.println("total time : "+ timeConsumedMillis2 + " ms");
    }
}
