package com.biszczak.marek;

import java.util.ArrayList;
import java.util.List;

public class AsterixDisplayStrategy implements DisplayStrategy{
    final String BOARDER = "***********************************************";
    @Override
    public void printMenu(List<String> options) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BOARDER + "\n");
        for (int i = 0; i < options.size(); i++) {
            stringBuilder.append((i+1) + " --> " + options.get(i) + "\n");
        }
        stringBuilder.append(BOARDER + "\n");
        System.out.println(stringBuilder);
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
