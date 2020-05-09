package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;

import java.util.List;

public interface PersistenceStrategy {

    Flashcard save(Flashcard flashcard);

    Flashcard getByQuestion(String question);

    Flashcard getByAnswer(String answer);

    List<Flashcard> getAllFlashcards();

    void delete(Flashcard flashcard);

    List<String> getAllQuestionsToList();

}
