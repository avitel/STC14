package ru.inno.lec09.HW;

import ru.inno.lec05.HW.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ParserLambda extends Parser {

    public ParserLambda(List<BufferedReader> readers, HashSet<String> dictionary) {
        super(readers, dictionary);
    }


    @Override
    public void parseFile(BufferedReader reader, ArrayList<String> result) {

        StringBuilder builder = new StringBuilder();


        reader.lines().
                forEach((line) -> {
                    line.chars().forEach((symbol) -> {
                        builder.append((char)symbol);
                        if (symbol == '.' || symbol == '!' || symbol == '?') {
                            if (sentenceExistInDictionary(builder)) {
                                result.add(builder.toString());
                                builder.delete(0, builder.length());
                            }
                        }
                    });
                });

    }
}
