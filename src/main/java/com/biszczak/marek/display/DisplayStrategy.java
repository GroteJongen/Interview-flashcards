package com.biszczak.marek.display;


import java.util.List;

public interface DisplayStrategy {

    void printList(List<String> options);

    void printMessage(String message);
}
