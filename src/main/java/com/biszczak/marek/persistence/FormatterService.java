package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;

import java.util.ArrayList;
import java.util.List;

public class FormatterService {


    public List<Flashcard> getFlashcardsToFormat(List<String> flascardsToFormat) {
        List<Flashcard> flashcards = new ArrayList<>();
        String question;
        String answer;
        for (int i = 0; i < flascardsToFormat.size(); i++) {
            try {
                question = flascardsToFormat.get(i).split(":")[0].trim();
                answer = flascardsToFormat.get(i).split(":")[1].trim();
                flashcards.add(new Flashcard(question, answer));
            }catch(Exception e){
                break;
            }

        }
        return flashcards;
    }
}
