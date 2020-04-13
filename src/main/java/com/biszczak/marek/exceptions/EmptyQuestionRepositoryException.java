package com.biszczak.marek.exceptions;

public class EmptyQuestionRepositoryException extends RuntimeException {
    public EmptyQuestionRepositoryException(String message) {
        super(message);
    }
}
