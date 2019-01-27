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

    private int numberOfFiles;
    private int numberOfParagraphs;
    private int numberOfSentences;
    private int numberOfWords;
    private HashSet<String> dictionary;


    
    public static void main(String[] args) {
        
        TextGen tg = new TextGen(3000, 10, 10,10);

        tg.loadDictionaryFromBook("./Files/book");

        tg.saveDictionary();

        tg.makeFiles("./Files/testGenFiles");
    }

    
    
    public TextGen(int numberOfFiles, int numberOfParagraphs, int numberOfSentences, int numberOfWords) {
        this.numberOfFiles = numberOfFiles;
        this.numberOfParagraphs = numberOfParagraphs;
        this.numberOfSentences = numberOfSentences;
        this.numberOfWords = numberOfWords;
        dictionary= new HashSet<>();

    }

    /**
     * Gets dictionaryay with words from text file
     *
     * @param path plain text file from which you want to get dictionary
     * @return dictionaryay HashSet<String> with unique words from the text file
     */
    private void loadDictionaryFromBook(String path){
        
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

                    dictionary.add(word);
                    i++;
                }

            }
        }catch (IOException e){
            System.out.println("Ошибка разбора файла");
        }

    }



    /**
     * Saves dictionary to plain text for further processing
     */
    private void saveDictionary(){

        try(FileWriter writer = new FileWriter("./Files/dictionary.txt", false)){
            for (String s : dictionary) {
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
     */
    public void makeFiles(String path){

        List<String > dictionaryList = new ArrayList<>(dictionary);
        String word;

        for (int i = 0; i < numberOfFiles; i++) {

            try(FileWriter writer = new FileWriter(path + i, false)) {
                for (int j = 0; j < numberOfParagraphs; j++) {

                    for (int k = 0; k < numberOfSentences; k++) {

                        boolean itIsFirstWord = true;

                        for (int l = 0; l < numberOfWords; l++) {

                            Random random = new Random();
                            word = dictionaryList.get((int)random.nextInt(dictionaryList.size()-1));

                            writer.append(' ');

                            if (itIsFirstWord) {
                                itIsFirstWord = false;
                                word = makeFirstLetterToUpperCase(word);
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

    
    /**
     * 
     * @param in
     * @return
     */
    private String makeFirstLetterToUpperCase(String in){
        char firstLetter = in.charAt(0);
        return Character.toUpperCase(firstLetter) + in.substring(1);
    }
}




