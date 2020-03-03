package com.biszczak.marek;

import javax.swing.plaf.InputMapUIResource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DisplayStrategy slashDisplayStrategy = new SlashDisplayStrategy();
        InputStrategy consoleInputStrategy = new ConsoleInputStrategy();
        InputContext inputContext = new InputContext(consoleInputStrategy);
        DisplayContext displayContext = new DisplayContext();
        displayContext.setDisplayStrategy(slashDisplayStrategy);
        GameController gameController = new GameController(displayContext,inputContext);
        List<MenuOptions> options = Arrays.asList(MenuOptions.values());


        String command;
        boolean exit = false;
        gameController.displayContext.printGreetings();
        while(!exit){
            gameController.displayContext.printMenu(options);
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
