package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapRepositoryTest {
  private MapRepository mapRepository = new MapRepository();
  private Flashcard flashcard = new Flashcard("Q1", "A1");
  private Flashcard flashcardToPopulate = new Flashcard("Q2", "A2");

  @Test
  void shouldReturnValidFlashcardFromMap() {
    Flashcard flashcardToTest = mapRepository.save(flashcard);
    assertEquals(flashcard.getQuestion(), flashcardToTest.getQuestion());
    assertEquals(flashcard.getAnswer(), flashcardToTest.getAnswer());
    assertEquals(flashcard, flashcardToTest);
  }

  @Test
  void shouldReturnValidAnswer() {
    mapRepository.save(flashcardToPopulate);
    Flashcard flashcardToTest = mapRepository.getByAnswer(flashcardToPopulate.getAnswer());
    assertEquals(flashcardToPopulate.getQuestion(), flashcardToTest.getQuestion());
    assertEquals(flashcardToPopulate.getAnswer(), flashcardToTest.getAnswer());
    assertEquals(flashcardToPopulate, flashcardToTest);
  }

  @Test
  void shouldReturnValidQuestion() {
    mapRepository.save(flashcardToPopulate);
    Flashcard flashcardToTest = mapRepository.getByQuestion(flashcardToPopulate.getQuestion());
    assertEquals(flashcardToPopulate.getQuestion(), flashcardToTest.getQuestion());
    assertEquals(flashcardToPopulate.getAnswer(), flashcardToTest.getAnswer());
    assertEquals(flashcardToPopulate, flashcardToTest);
  }

  @Test
  void shouldUpdateFlashcardInMap() {
    Flashcard flashcardToTest = mapRepository.update(flashcard);
    assertEquals(flashcard.getQuestion(), flashcardToTest.getQuestion());
    assertEquals(flashcard.getAnswer(), flashcardToTest.getAnswer());
    assertEquals(flashcard, flashcardToTest);
  }

  @Test
  void shouldReturnValidListOfFlashcards() {
    mapRepository.save(flashcardToPopulate);
    List<Flashcard> flashcardList = new ArrayList<>();
    flashcardList.add(flashcardToPopulate);
    List<Flashcard> allFlashcards = mapRepository.getAllFlashcards();
    assertEquals(flashcardList, allFlashcards);
  }

  @Test
  void shouldReturnValidListOfQuestions() {
    mapRepository.save(flashcardToPopulate);
    List<String> questions = new ArrayList<>();
    questions.add(flashcardToPopulate.getQuestion());
    List<String> allQuestionsToList = mapRepository.getAllQuestionsToList();
    assertEquals(questions, allQuestionsToList);
  }
}
