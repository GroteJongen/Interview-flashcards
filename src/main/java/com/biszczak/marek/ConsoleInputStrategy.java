package com.biszczak.marek;

import java.util.Scanner;

public class ConsoleInputStrategy implements InputStrategy {
    private Scanner scanner = new Scanner(System.in);

    public String getQuestion() {
        System.out.println("Pass the question");
        String questionFromUsreInput = scanner.nextLine();
        return questionFromUsreInput;
    }

    public String getAnswer() {
        System.out.println("Pass the answer");
        String answerFromUsreInput = scanner.nextLine();
        return answerFromUsreInput;
    }

    public String getCommand() {
        System.out.println("Pass the command");
        String commandFromUsreInput = scanner.nextLine();
        return commandFromUsreInput;
    }
}