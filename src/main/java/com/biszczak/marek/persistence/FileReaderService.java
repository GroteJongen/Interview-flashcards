package com.biszczak.marek.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileReaderService {
    private static final String EXCEPTION_MSG = "No file detected";

    public List<String> readFile(String filename) {
        try (FileReader fileReader = new FileReader( filename);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            List<String> flashcards = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                flashcards.add(line);
                line = bufferedReader.readLine();
            }
            return flashcards;
        } catch (IOException e) {
            System.out.println(EXCEPTION_MSG);
            //TODO Implement business exceptions
        }
        return Collections.emptyList();
    }
}
