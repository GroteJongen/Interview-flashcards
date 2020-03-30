package com.biszczak.marek.input;

public class InputContext {
    public InputContext(InputStrategy inputStrategy) {
        this.inputStrategy = inputStrategy;
    }

    private InputStrategy inputStrategy;

    public String getQuestion() {
        return inputStrategy.getQuestion();
    }

    public String getAnswer() {
        return inputStrategy.getAnswer();
    }

    public String getCommand() {
        return inputStrategy.getCommand();
    }
}
