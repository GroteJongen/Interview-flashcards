package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;

import java.util.List;

public class PersistenceContext {
  private PersistenceStrategy persistanceStrategy;

  public PersistenceContext(PersistenceStrategy persistanceStrategy) {
    this.persistanceStrategy = persistanceStrategy;
  }

  public Flashcard save(Flashcard flashcard) {
    return this.persistanceStrategy.save(flashcard);
  }

  public Flashcard update(Flashcard flashcard) {
    return this.persistanceStrategy.update(flashcard);
  }

  public void delete(Flashcard flashcard) {
    this.persistanceStrategy.delete(flashcard);
  }

  public List<Flashcard> getAllFlashcards() {
    return this.persistanceStrategy.getAllFlashcards();
  }

  public Flashcard getByQuestion(String question) {
    return this.persistanceStrategy.getByQuestion(question);
  }

  public Flashcard getByAnswer(String answer) {
    return this.persistanceStrategy.getByAnswer(answer);
  }
  public List<String> getAllQuestionsToList(){
    return this.persistanceStrategy.getAllQuestionsToList();
  }
}
