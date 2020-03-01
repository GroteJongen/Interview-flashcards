package com.biszczak.marek;

public class DisplayContext {
    private DisplayStrategy displayStrategy;

    public DisplayContext(DisplayStrategy displayStrategy) {
        this.displayStrategy = displayStrategy;
    }

    public void printMenu() {
        this.displayStrategy.printMenu();
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
