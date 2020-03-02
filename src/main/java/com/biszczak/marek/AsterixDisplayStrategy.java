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
    public void printMessage(String message) {
        StringBuilder messageToPrint = new StringBuilder();
        messageToPrint.append(BOARDER + "\n");
        messageToPrint.append(message + "\n");
        messageToPrint.append(BOARDER+"\n");
        System.out.println(messageToPrint);

    }
}
