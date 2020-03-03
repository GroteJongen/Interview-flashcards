package com.biszczak.marek;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class DisplayContext {
    private DisplayStrategy displayStrategy;


    private List<String> menuOptions = Arrays
            .stream(MenuOptions.values())
            .map(MenuOptions::getOptionName)
            .collect(Collectors.toList());
    private Set<String> strategies = new HashSet<>();

    private final String greeting = "Hello Dear User";
    private final String goodbye = "Goodbye Dear User";

    void printMenu() {
        this.displayStrategy.printMenu(this.menuOptions);
    }

    void printGreetings() {
        displayStrategy.printMessage(greeting);
    }

    void printGoodbye() {
        displayStrategy.printMessage(goodbye);
    }

    void setDisplayStrategy(DisplayStrategy displayStrategy) {
        strategies.add(displayStrategy.getName());
        this.displayStrategy = displayStrategy;
    }
}
