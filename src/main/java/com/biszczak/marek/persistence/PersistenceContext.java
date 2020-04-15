package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;

import java.util.List;

public class PersistenceContext {
  private PersistenceStrategy persistenceStrategy;

  public PersistenceContext(PersistenceStrategy persistanceStrategy) {
    this.persistenceStrategy = persistanceStrategy;
  }

  public Flashcard save(Flashcard flashcard) {
    return this.persistenceStrategy.save(flashcard);
  }

  public Flashcard update(Flashcard flashcard) {
    return this.persistenceStrategy.update(flashcard);
  }

  public void delete(Flashcard flashcard) {
    this.persistenceStrategy.delete(flashcard);
  }

  public List<Flashcard> getAllFlashcards() {
    return this.persistenceStrategy.getAllFlashcards();
  }

  public Flashcard getByQuestion(String question) {
    return this.persistenceStrategy.getByQuestion(question);
  }

  public Flashcard getByAnswer(String answer) {
    return this.persistenceStrategy.getByAnswer(answer);
  }
  public List<String> getAllQuestionsToList(){
    return this.persistenceStrategy.getAllQuestionsToList();
  }
}
