package com.biszczak.marek.input;

import java.util.Scanner;

public class ConsoleInputStrategy implements InputStrategy {
    private Scanner scanner = new Scanner(System.in);

    public String getQuestion() {
        String questionFromUsreInput = scanner.nextLine();
        return questionFromUsreInput;
    }

    public String getAnswer() {
        String answerFromUsreInput = scanner.nextLine();
        return answerFromUsreInput;
    }

    public String getCommand() {
        String commandFromUsreInput = scanner.nextLine();
        return commandFromUsreInput;
    }
}