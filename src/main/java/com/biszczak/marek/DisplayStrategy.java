package com.biszczak.marek;


import java.util.List;

public interface DisplayStrategy {

    void printMenu(List<String> options);

    void printMessage(String message);

    String getName();


}
