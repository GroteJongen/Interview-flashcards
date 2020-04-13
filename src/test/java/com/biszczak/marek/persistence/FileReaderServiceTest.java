package com.biszczak.marek.persistence;

import com.biszczak.marek.exceptions.WrongFileFormatException;
import com.biszczak.marek.exceptions.WrongFilePathException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderServiceTest {
  private FileReaderService fileReaderService = new FileReaderService();
  private List<String> list = new ArrayList<>();

  @Test
  void shouldReturnValidListOfStrings() throws WrongFilePathException {
    list.add("Q1:A1");
    list.add("Q2:A2");
    list.add("Q3:A3");
    list.add("Q4:A4");
    System.out.println(list);
    List<String> stringsToCheck = fileReaderService.readFile("test");
    System.out.println(stringsToCheck);
    assertEquals(list, stringsToCheck);
  }

  @Test
  void shouldThrowMessageWhenFilePathIsIncorrect() throws WrongFilePathException {
    assertThrows(
        WrongFilePathException.class,
        () -> {
          fileReaderService.readFile("nonExistingFile");
        });
  }

}
