package com.biszczak.marek;

import java.util.ArrayList;

public class DisplayContext {
    private DisplayStrategy displayStrategy;


    public DisplayContext(DisplayStrategy displayStrategy) {
        this.displayStrategy = displayStrategy;
    }

    public void printMenu(ArrayList options) {
        this.displayStrategy.printMenu(options);
    }
    public void printGreeting() {
        this.displayStrategy.printGreeting();
    }
    public void printGoodbye() {
        this.displayStrategy.printGoodbye();
    }

    public void setDisplayStrategy(DisplayStrategy displayStrategy) {
        this.displayStrategy = displayStrategy;
    }

}
