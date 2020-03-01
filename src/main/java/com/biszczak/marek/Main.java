package com.biszczak.marek;

import javax.swing.plaf.InputMapUIResource;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        DisplayStrategy slashDisplayStrategy = new SlashDisplayStrategy();
        InputStrategy consoleInputStrategy = new ConsoleInputStrategy();
        InputContext inputContext = new InputContext(consoleInputStrategy);
        DisplayContext displayContext = new DisplayContext(slashDisplayStrategy);
        GameController gameController = new GameController(displayContext,inputContext);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Exit");
        arrayList.add("Do something else");

        String command;
        boolean exit = false;
        gameController.displayContext.printGreeting();
        while(!exit){
            gameController.displayContext.printMenu(arrayList);
            command = gameController.inputContext.getCommand();
            switch (command){
                case "1":
                    gameController.displayContext.setDisplayStrategy(new AsterixDisplayStrategy());
                    break;
                case "2":
                    exit = true;
                    gameController.displayContext.printGoodbye();
                    break;
            }
        }
    }
}
