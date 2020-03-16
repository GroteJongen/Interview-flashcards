package com.biszczak.marek;

import com.biszczak.marek.display.DisplayContext;
import com.biszczak.marek.display.DisplayStrategy;
import com.biszczak.marek.display.SlashDisplayStrategy;
import com.biszczak.marek.input.ConsoleInputStrategy;
import com.biszczak.marek.input.InputContext;
import com.biszczak.marek.input.InputStrategy;
import com.biszczak.marek.persistence.MapPersistenceStrategy;
import com.biszczak.marek.persistence.PersistenceStrategy;

public class Main {

    final static String MSG_TO_NORTHERN_IRISH_DUDE = "Java is awesome";

        public static void main(String[] args) {
        Flashcard flashcard = new Flashcard("Andrzej", "Zbyszek");
        Flashcard flashcard1 = new Flashcard("Debil", "Kurwa debil");
        PersistenceStrategy persistenceStrategy = new MapPersistenceStrategy();
        DisplayStrategy slashDisplayStrategy = new SlashDisplayStrategy();
        InputStrategy consoleInputStrategy = new ConsoleInputStrategy();

        InputContext inputContext = new InputContext(consoleInputStrategy);

        DisplayContext displayContext = new DisplayContext();
        displayContext.setDisplayStrategy(slashDisplayStrategy);
        persistenceStrategy.save(flashcard);
        persistenceStrategy.save(flashcard1);
            System.out.println(persistenceStrategy.getByAnswer("Kurwa debil"));
            GameController gameController = new GameController(displayContext,inputContext);



     /*   String command;
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
                    break;*/
            }
        }


