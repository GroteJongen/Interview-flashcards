package com.biszczak.marek.persistence;

import com.biszczak.marek.exceptions.WrongFilePathException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderService {

  public List<String> readFile(String filename) throws WrongFilePathException {
    try (FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader)) {

      List<String> flashcards = new ArrayList<>();
      String line = bufferedReader.readLine();
      while (line != null) {
        if (line.isEmpty()) {
          line = bufferedReader.readLine();
          continue;
        }
        flashcards.add(line);
        line = bufferedReader.readLine();
      }
      return flashcards;

    } catch (IOException e) {
      String EXCEPTION_MSG = "No file detected";
      throw new WrongFilePathException(EXCEPTION_MSG);
    }
  }
}
