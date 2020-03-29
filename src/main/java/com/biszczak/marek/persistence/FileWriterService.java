package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterService {
  private static final String EXCEPTION_MSG = "No file detected";



  public void writeLine(Flashcard flashcard, String fileName) {
    try (FileWriter fileWriter = new FileWriter(fileName, true)) {
      fileWriter.write(flashcard.getQuestion() + " : " + flashcard.getAnswer() + "\n");

    } catch (IOException e) {
      System.out.println(EXCEPTION_MSG);
      //TODO Implement business exceptions
    }
  }

  public void writeLines(List<Flashcard> lines, String fileName) {
    for (Flashcard line : lines) {
      writeLine(new Flashcard(line.getQuestion(), line.getAnswer()), fileName);
    }
      System.out.println(EXCEPTION_MSG);
      //TODO Implement business exceptions
  }

}

