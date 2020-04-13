package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterService {
  private final String EXCEPTION_MSG = "No file detected";

  private void writeLine(Flashcard flashcard, String fileName) {
    final String semicolon = ":";
    final String newLine = "\n";
    String formattedLine =
        String.format(
            "%s %s %s%s", flashcard.getQuestion(), semicolon, flashcard.getAnswer(), newLine);

    try (FileWriter fileWriter = new FileWriter(fileName, true)) {
      fileWriter.write(formattedLine);

    } catch (IOException e) {
      System.out.println(EXCEPTION_MSG);
    }
  }

  public void writeLines(List<Flashcard> flashcards, String fileName) {
    for (Flashcard flashcard : flashcards) {
      writeLine(flashcard, fileName);
    }
    System.out.println(EXCEPTION_MSG);
  }
}
