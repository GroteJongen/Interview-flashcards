package com.biszczak.marek;


import java.util.List;

public class DisplayContext {
    private DisplayStrategy displayStrategy;
    private final String GREETING = "Hello Dear User";
    private final String GOODBYE = "Goodbye Dear User";

    public void printMenu(List<MenuOptions> options){
        this.displayStrategy.printMenu(options);
    }

    public void printGreetings(){
        displayStrategy.printMessage(GREETING);
    }
    public void printGoodbye(){
        displayStrategy.printMessage(GOODBYE);
    }

    public void setDisplayStrategy(DisplayStrategy displayStrategy) {
        this.displayStrategy = displayStrategy;
    }
}
