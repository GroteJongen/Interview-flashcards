package com.biszczak.marek;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.biszczak.marek.MenuOptions.*;

public class Main {

    public static void main(String[] args) {

        DisplayStrategy slashDisplayStrategy = new SlashDisplayStrategy();
        InputStrategy consoleInputStrategy = new ConsoleInputStrategy();

        InputContext inputContext = new InputContext(consoleInputStrategy);

        DisplayContext displayContext = new DisplayContext();
        displayContext.setDisplayStrategy(slashDisplayStrategy);


        GameController gameController = new GameController(displayContext,inputContext);



        String command;
        boolean exit = false;
        gameController.displayContext.printGreetings();
        while(!exit){
            gameController.displayContext.printMenu();
            command = gameController.inputContext.getCommand();
            switch (command){
                case "1":
                    gameController.displayContext.setDisplayStrategy(new AstrixDisplayStrategy());
                    break;
                case "6":
                    exit = true;
                    gameController.displayContext.printGoodbye();
                    break;
            }
        }
    }
}
