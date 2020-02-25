package com.biszczak.marek;

public class InputService {

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
