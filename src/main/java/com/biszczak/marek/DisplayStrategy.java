package com.biszczak.marek;


import java.util.List;

public interface DisplayStrategy {

    void printMenu(List<String> options);

    void printGreeting();

    void printGoodbye();

    void printFailure();

    void printSucces();
}
