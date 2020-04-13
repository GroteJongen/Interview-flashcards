package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;
import com.biszczak.marek.exceptions.EmptyQuestionListException;
import com.biszczak.marek.exceptions.WrongFileFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FormatterServiceTest {
  private FormatterService formatterService = new FormatterService();
  private Flashcard flashcard = new Flashcard("Q1","A1");
  private Flashcard flashcard1 = new Flashcard("Q2","A2");
  private Flashcard flashcard2 = new Flashcard("Q3","A3");
  private Flashcard flashcard3 = new Flashcard("Q4","A4");
  private Flashcard flashcard4 = new Flashcard("Q5","A5");



  @Test
  void shouldReturnValidListOfFlashcards() {
    List<Flashcard> flashcards = new ArrayList<>();
    List<String> flashcardsToTest = new ArrayList<>();
    flashcardsToTest.add("Q1:A1");
    flashcardsToTest.add("Q2:A2");
    flashcardsToTest.add("Q3:A3");
    flashcardsToTest.add("Q4:A4");
    flashcardsToTest.add("Q5:A5");
    flashcards.add(flashcard);
    flashcards.add(flashcard1);
    flashcards.add(flashcard2);
    flashcards.add(flashcard3);
    flashcards.add(flashcard4);
    List<Flashcard> formattedFlashcards = formatterService.getFlashcardsToFormat(flashcardsToTest);
    System.out.println(flashcards.equals(formattedFlashcards));
      Assertions.assertEquals(flashcards,formattedFlashcards);
  }

  @Test
  void shouldReturnEmptyListIfEmptyListIsGiven(){
    List listToCompare = Collections.EMPTY_LIST;
    List listToCheck = Collections.EMPTY_LIST;
    List testList = formatterService.getFlashcardsToFormat(listToCheck);
    assertEquals(listToCompare,testList);
  }

  @Test
  void shouldReturnEmptyList(){
    assertThrows(EmptyQuestionListException.class,()->{
      formatterService.getFlashcardsToFormat(Collections.emptyList());
    });
  }

  @Test
  void shouldThrowMessageWhenFileFormatIsIncorrect() {
    List<String> wrongFormatList = new ArrayList<>();
    wrongFormatList.add("Wrong Formatted String");
    assertThrows(
            WrongFileFormatException.class,
            () -> {
              formatterService.getFlashcardsToFormat(wrongFormatList);
            });
  }

}