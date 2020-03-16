package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;

public class PersistenceContext {
    private PersistenceStrategy persistanceStrategy;

    public Flashcard save(Flashcard flashcard){
        return this.persistanceStrategy.save(flashcard);
    }

    public Flashcard update(Flashcard flashcard){
        return this.persistanceStrategy.update(flashcard);
    }
    public void delete(Flashcard flashcard){
        this.persistanceStrategy.delete(flashcard);
    }
}
