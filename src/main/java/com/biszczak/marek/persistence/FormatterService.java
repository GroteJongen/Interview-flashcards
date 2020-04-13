package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;
import com.biszczak.marek.exceptions.EmptyQuestionListException;
import com.biszczak.marek.exceptions.WrongFileFormatException;
import java.util.List;
import java.util.stream.Collectors;

public class FormatterService {

  public List<Flashcard> getFlashcardsToFormat(List<String> linesToFormat) {

    if (linesToFormat.isEmpty()) {
      final String errorMsg = "List of your questions is empty";
      throw new EmptyQuestionListException(errorMsg);
    }
    final String semicolon = ":";
    try {

      return linesToFormat.stream()
          .map(
              line ->
                  new Flashcard(line.split(semicolon)[0].trim(), line.split(semicolon)[1].trim()))
          .collect(Collectors.toList());
    } catch (ArrayIndexOutOfBoundsException error) {
      final String errorMsg = "Wrong file format";
      throw new WrongFileFormatException(errorMsg);
    }
  }
}
