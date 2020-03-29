package com.biszczak.marek.display;

import java.util.List;

public class TriangleDisplayStrategy implements DisplayStrategy{
    private final String THEME_NAME = "Triangle Theme";
    final String BOARDER = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
    @Override
    public void printMenu(List<String> options) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BOARDER +"\n");
        for (int i = 0; i <options.size() ; i++) {
            stringBuilder.append((i+1) + " --> " + options.get(i) + "\n");
        }
        stringBuilder.append(BOARDER + "\n");
        System.out.println(stringBuilder);
    }
    public void printThemes(List<String> themes){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BOARDER +"\n");
        for (int i = 0; i <themes.size() ; i++) {
            stringBuilder.append((i+1) + " --> " + themes.get(i) + "\n");
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
        System.out.print(messageToPrint);
    }

    @Override
    public String getName() {
        return  THEME_NAME;
    }
}
