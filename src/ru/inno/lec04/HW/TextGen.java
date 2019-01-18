package ru.inno.lec04.HW;
import java.io.*;

/**
 * @author Ilya Kruglov
 * @version 1.0
 */

public class TextGen {

    private int n1;
    private int n3;


    public TextGen(int n1, int n3) {

        /**
         * колво слов
         */
        this.n1 = n1;
        /**
         * колво предложений
         */
        this.n3 = n3;

    }

    public static void main(String[] args) {

        TextGen tg = new TextGen(10, 4 );

        String[] arr = getWordSet(15 , 100);

        tg.getFiles("./testGenFiles",2,4, arr, 1);
    }

    /**
     *
     * @param path путь к файлу. К имени будет добавлена цифра индекса
     * @param n количество файлов
     * @param size количество абзацев в файле
     * @param words массив слов
     * @param probability вероятность (не используется)
     */
    public  void getFiles(String path, int n, int size, String[] words, int probability){

        /**
         * по файлам
         */
        for (int i = 0; i < n; i++) {

            try(FileWriter writer = new FileWriter(path + i, false))
            {
                /**
                 * по абзацем
                 */
                for (int j = 0; j < size; j++) {

                    /**
                     * по предложениям
                     */
                    for (int k = 0; k < n3; k++) {

                        boolean itIsFirstWord = true;

                        /**
                         * по словам
                         */
                        for (int l = 0; l < n1; l++) {

                            writer.append(' ');

                            String word = getWord(words);

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
                    writer.append('\r');
                }
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     *
     * @param n2 максимальная длина слова
     * @param n4 кол-во слов в массиве
     * @return массив рандомных слов
     */
    private static String[] getWordSet(int n2 , int n4){

        String[] arr = new String[n4];

        for (int i = 0; i < arr.length; i++) {
            int curLenght = (int)(Math.random()*n2+1);
            char[] curLetters = new char[curLenght];
            for (int k = 0; k <  curLenght; k++) {
                curLetters[k] = (char)(Math.random()*('z'-'a') + 'a');
            }

            arr[i] = new String(curLetters);
        }

        return arr;
    }


    private String getWord(String[] arr){

        int rndInd = (int)(Math.random() * arr.length);

        return arr[rndInd];

    }
}
