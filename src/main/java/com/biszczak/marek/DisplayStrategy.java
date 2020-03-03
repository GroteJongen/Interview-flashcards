package com.biszczak.marek;


import java.util.List;

public interface DisplayStrategy {

    void printMenu(List<MenuOptions> options);

    void printMessage(String message);

}
