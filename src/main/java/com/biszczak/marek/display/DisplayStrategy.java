package com.biszczak.marek.display;


import com.biszczak.marek.Flashcard;

import java.util.List;

public interface DisplayStrategy {

    void printList(List<String> options);

    void showAllQuestions(List<Flashcard> flashcards);

    void printMessage(String message);

}
