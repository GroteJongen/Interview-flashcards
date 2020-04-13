package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapRepository implements PersistenceStrategy {
  private Map<String, String> repository = new HashMap<>();

  @Override
  public Flashcard save(Flashcard flashcard) {
    repository.put(flashcard.getQuestion(), flashcard.getAnswer());
    String answer = repository.get(flashcard.getQuestion());
    String question = flashcard.getQuestion();
    return new Flashcard(question, answer);
  }

  public Flashcard getByAnswer(String answer) {
    for (String key : repository.keySet()) {
      if (repository.get(key).equals(answer)) {
        return new Flashcard(key, answer);
      }
    }
    return null;
  }

  @Override
  public Flashcard getByQuestion(String question) {
    if(repository.containsKey(question)){
      String answer = repository.get(question);
      return new Flashcard(question, answer);
    }
    return null;
  }

  @Override
  public Flashcard update(Flashcard flashcard) {
    if (!repository.containsValue(flashcard)) {
      return save(flashcard);
    }
    repository.replace(flashcard.getQuestion(), flashcard.getAnswer());
    return flashcard;
  }

  @Override
  public List<Flashcard> getAllFlashcards() {
    List<Flashcard> flashcards = new ArrayList<>();
    for (String question : repository.keySet()) {
      flashcards.add(new Flashcard(question, repository.get(question)));
    }
    return flashcards;
  }

  @Override
  public void delete(Flashcard flashcard) {
    repository.remove(flashcard.getQuestion());
  }

  @Override
  public List<String> getAllQuestionsToList() {
    return new ArrayList<>(repository.keySet());
  }
}
