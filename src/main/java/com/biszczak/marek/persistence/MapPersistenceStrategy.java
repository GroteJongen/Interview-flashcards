package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapPersistenceStrategy implements PersistenceStrategy {
  Map<String, String> flashCardMap = new HashMap<>();

  @Override
  public Flashcard save(Flashcard flashcard) {
    flashCardMap.put(flashcard.getQuestion(), flashcard.getAnswer());
    String answer = flashCardMap.get(flashcard.getQuestion());
    return new Flashcard(flashcard.getQuestion(), answer);
  }

  public Flashcard getByAnswer(String answer) {
    for (String key : flashCardMap.keySet()) {
      if (flashCardMap.get(key).equals(answer)) {
        return new Flashcard(key, answer);
      }
    }
    return null;
  }

  @Override
  public Flashcard getByQuestion(String question) {
    if (!flashCardMap.containsKey(question)) {
      return null;
    }
    String answer = flashCardMap.get(question);
    return new Flashcard(question, answer);
  }

  @Override
  public Flashcard update(Flashcard flashcard) {
    if (!flashCardMap.containsValue(flashcard)) {
      return null;
    }
    flashCardMap.replace(flashcard.getQuestion(), flashcard.getAnswer());
    return flashcard;
  }

  @Override
  public List<Flashcard> getAllFlashcards() {
    List<Flashcard> flashcards = new ArrayList<>();
    for (String question : flashCardMap.keySet()) {
      flashcards.add(new Flashcard(question, flashCardMap.get(question)));
    }
    return flashcards;
  }

  @Override
  public void delete(Flashcard flashcard) {
    flashCardMap.remove(flashcard.getQuestion());
  }
  public List<String> getAllQuestionsToList(){
    List<String> questions = new ArrayList<>();
    questions.addAll(flashCardMap.keySet());
    return questions;
  }
}
