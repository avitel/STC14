package ru.inno.lec04.HW;
import java.io.*;
import java.util.*;

/**
 *  Text generator which makes random text from dictionary.
 *  Dictionary is loaded from text file
 *
 * @author Ilya Kruglov
 * @version 1.0
 */

public class TextGen {

    /**
     * number of words in sentence
     */
    static private int n1;

    /**
     * number of sentence in paragraph
     */
    static private int n3;


    public static void main(String[] args) {

        HashSet<String> arr = getDictionary("./Files/book");

        saveDictionary(arr);

        n1 = 10;
        n3 = 10;
        getFiles("./Files/testGenFiles",3000,10, arr, 1);
    }


    /**
     * Gets array with words from text file
     *
     * @param path plain text file from which you want to get dictionary
     * @return array HashSet<String> with unique words from the text file
     */
    private static HashSet<String> getDictionary(String path){

        HashSet<String> arr= new HashSet<>();

        try{
            FileInputStream fstream = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            int i = 0;
            while ((strLine = br.readLine()) != null){

                strLine = strLine.replaceAll(",|\\.|-|;|!|–|:|\\?","");
                String[] words = strLine.split(" ");
                for (String word : words) {
                    if (word.length() == 0 | word.length() == 1) continue;

                    arr.add(word);
                    i++;
                }

            }
        }catch (IOException e){
            System.out.println("Ошибка разбора файла");
        }

        return arr;
    }



    /**
     * Saves dictionary to plain text for further processing
     * @param arr
     */
    private static void saveDictionary(HashSet<String> arr){

        try(FileWriter writer = new FileWriter("./Files/dictionary.txt", false)){
            for (String s : arr) {
                writer.write(s);
                writer.append('\n');
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }


    /**
     * Makes text file from dictionary with random order
     *
     * @param path path to file. Index will be appended
     * @param numberOfFiles number of files
     * @param size number of paragraphes
     * @param dictionary  HashSet<String> dictionary (array of words)
     * @param probability not used
     */
    public  static void getFiles(String path, int numberOfFiles, int size, HashSet<String> dictionary, int probability){

        List<String > arr = new ArrayList<>(dictionary);
        String word;

        /**
         * files
         */
        for (int i = 0; i < numberOfFiles; i++) {

            try(FileWriter writer = new FileWriter(path + i, false))
            {
                /**
                 * paragpaph
                 */
                for (int j = 0; j < size; j++) {

                    /**
                     * sentences
                     */
                    for (int k = 0; k < n3; k++) {

                        boolean itIsFirstWord = true;

                        /**
                         * words
                         */
                        for (int l = 0; l < n1; l++) {

                            Random random = new Random();
                            word = arr.get((int)random.nextInt(arr.size()-1));

                            writer.append(' ');

                            if (itIsFirstWord) {
                                itIsFirstWord = false;
                                char fl = word.charAt(0);
                                fl = Character.toUpperCase(fl);
                                word = fl + word.substring(1);
                            }

                            writer.write(word);
                        }

                        writer.append('.');
                    }
                    writer.append('\n');
                }
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
    }
}




