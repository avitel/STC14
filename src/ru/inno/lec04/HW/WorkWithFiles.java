package ru.inno.lec04.HW;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

public class WorkWithFiles {


    /**
     *
     * @param out
     * @param filename
     */
    public static void saveFile(StringBuilder out, String filename) {

        try {
            Files.write(Paths.get(filename), out.toString().getBytes());
        } catch (IOException e) {
            System.out.println("saving file error");
        }
    }


    /**
     *
     * @param arr
     * @param filename
     */
    public static void saveCollectionToFile(Collection arr, String filename){
        try(FileWriter writer = new FileWriter(filename, false)) {
                for (Object elem : arr) {
                    writer.write(elem.toString());
                    writer.write('\n');
                }

        }catch (IOException e){
            System.out.println("save result to collection error");
        }
    }

}
