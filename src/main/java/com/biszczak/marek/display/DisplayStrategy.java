package com.biszczak.marek.display;


import java.util.List;

public interface DisplayStrategy {

    void printMenu(List<String> options);

    void printMessage(String message);

    String getName();

    void printThemes(List<String> themes);

}
