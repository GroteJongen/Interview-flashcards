package com.biszczak.marek;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class Flashcard {

    private String question;
    private String answer;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flashcard flashcard = (Flashcard) o;
        return Objects.equals(question, flashcard.question) &&
                Objects.equals(answer, flashcard.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }
}



