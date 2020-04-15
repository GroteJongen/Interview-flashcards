package com.biszczak.marek.exceptions;

public class EmptyQuestionListException extends RuntimeException {
    public EmptyQuestionListException(String message) {
        super(message);
    }
}
