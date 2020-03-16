package com.biszczak.marek.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileService {
  private final String resourcePath;
  private static final String EXCEPTION_MSG = "No file detected";

  private final String fileName;

  public FileService(String resourcePath, String fileName) {
    this.resourcePath = resourcePath;
    this.fileName = fileName;
  }

  public void writeLine(String line) {
    try (FileWriter fileWriter = new FileWriter(resourcePath + fileName, true)) {
      fileWriter.write(line + "\n");

    } catch (IOException e) {
      System.out.println(EXCEPTION_MSG);
    }
  }

  public void writeLines(List<String> lines) {
    try (FileWriter fileWriter = new FileWriter(resourcePath + fileName, true)) {

      lines.forEach(this::writeLine);

    } catch (IOException e) {
      System.out.println(EXCEPTION_MSG);
    }
  }

  public List<String> readFile(String filename) {
    try (FileReader fileReader = new FileReader(resourcePath + filename);
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
    }
    return Collections.emptyList();
  }
}
