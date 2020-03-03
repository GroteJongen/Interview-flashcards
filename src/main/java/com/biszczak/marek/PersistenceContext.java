package com.biszczak.marek;

public class PersistenceContext {
    private PersistenceStrategy persistanceStrategy;

    public Flashcard save(Flashcard flashcard){
        return this.persistanceStrategy.save(flashcard);
    }
    public Flashcard getObject(Flashcard flashcard){
        return this.persistanceStrategy.getObject(flashcard);
    }
    public Flashcard getById(long id){
        return this.persistanceStrategy.getById(id);
    }
    public Flashcard update(Flashcard flashcard){
        return this.persistanceStrategy.update(flashcard);
    }
    public void delete(Flashcard flashcard){
        this.persistanceStrategy.delete(flashcard);
    }
}
