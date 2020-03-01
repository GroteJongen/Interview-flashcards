package com.biszczak.marek;

public class AsterixDisplayStrategy implements DisplayStrategy{
    @Override
    public void printMenu() {
        System.out.println("***********************************************\n" +
                "1 - Change theme\n " +
                "2 - Exit\n " +
                "***********************************************\n");
    }

    @Override
    public void printGreeting() {
        System.out.println("***********************************************\n" +
                "Hallo dear user \n" +
                "***********************************************\n");
    }

    @Override
    public void printGoodbye() {
        System.out.println("***********************************************\n" +
                "Goodbye dear user\n" +
                "***********************************************\n");
    }

    @Override
    public void printFailure() {

    }

    @Override
    public void printSucces() {

    }
}
