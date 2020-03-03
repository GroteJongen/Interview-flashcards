package com.biszczak.marek;

public interface PersistenceStrategy {

    Flashcard save(Flashcard flashcard);

    Flashcard getObject(Flashcard flashcard);

    Flashcard getById(long id);

    Flashcard update(Flashcard flashcard);

    void delete(Flashcard flashcard);

}
