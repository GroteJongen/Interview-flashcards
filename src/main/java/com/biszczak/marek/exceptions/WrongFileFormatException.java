package com.biszczak.marek.exceptions;

public class WrongFileFormatException extends RuntimeException {
    public WrongFileFormatException(String message) {
        super(message);
    }
}
