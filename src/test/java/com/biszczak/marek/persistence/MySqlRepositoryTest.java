package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySqlRepositoryTest {
  MySqlRepository mySqlRepository = new MySqlRepository();

  @Test
  void shouldSaveAndReturnValidFlashcard() {
    Flashcard flashcardToSave = new Flashcard("Q1", "A1");
    Flashcard returnedFlashcard = mySqlRepository.save(flashcardToSave);
    assertEquals(flashcardToSave, returnedFlashcard);
  }

  @Test
  void shouldReturnValidFlashcardByQuestion() {
    Flashcard flashcard = new Flashcard("Q1", "A1");
    mySqlRepository.save(flashcard);
    Flashcard flashcardToGet = mySqlRepository.getByQuestion("Q1");
    assertEquals(flashcard, flashcardToGet);
  }
  @Test
  void shouldReturnValidFlashcardByAnswer(){
    Flashcard flashcard = new Flashcard("Q2","A2");
    mySqlRepository.save(flashcard);
    Flashcard flashcardToGet = mySqlRepository.getByAnswer("A2");
    assertEquals(flashcard,flashcardToGet);
  }
  @Test
  void shouldReturnListOfFlashcards(){
    List<Flashcard> flashcards = new ArrayList<>();
    List<Flashcard> excpectedFlashcards = new ArrayList<>();
    flashcards.add(new Flashcard("Q1","A1"));
    flashcards.add(new Flashcard("Q2","A2"));
    flashcards.add(new Flashcard("Q3","A3"));
    excpectedFlashcards = mySqlRepository.getAllFlashcards();
    assertEquals(flashcards,excpectedFlashcards);
  }
}
