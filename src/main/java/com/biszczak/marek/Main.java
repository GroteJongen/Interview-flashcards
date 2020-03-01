package com.biszczak.marek;

import javax.swing.plaf.InputMapUIResource;

public class Main {

    public static void main(String[] args) {
        DisplayStrategy slashDisplayStrategy = new SlashDisplayStrategy();
        InputStrategy consoleInputStrategy = new ConsoleInputStrategy();
        InputContext inputContext = new InputContext(consoleInputStrategy);
        DisplayContext displayContext = new DisplayContext(slashDisplayStrategy);
        GameController gameController = new GameController(displayContext,inputContext);

        String command;
        boolean exit = false;
        gameController.displayContext.printGreeting();
        while(!exit){
            gameController.displayContext.printMenu();
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
