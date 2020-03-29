package com.biszczak.marek;

import com.biszczak.marek.display.DisplayContext;
import com.biszczak.marek.display.DisplayStrategy;
import com.biszczak.marek.display.SlashDisplayStrategy;
import com.biszczak.marek.input.ConsoleInputStrategy;
import com.biszczak.marek.input.InputContext;
import com.biszczak.marek.input.InputStrategy;
import com.biszczak.marek.persistence.MapPersistenceStrategy;
import com.biszczak.marek.persistence.PersistenceContext;
import com.biszczak.marek.persistence.PersistenceStrategy;
import com.biszczak.marek.persistence.FileWriterService;
import com.biszczak.marek.persistence.FileReaderService;
import com.biszczak.marek.persistence.FormatterService;


import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        Flashcard questionOne = new Flashcard("Co to oop?", "Object orientated programing");
        Flashcard questionTwo = new Flashcard("Co to mapa?", "Struktura przechowująca pytania i odpowiedzi");
        Flashcard questionThree = new Flashcard("Czy naleśniki z dżemem czy gównem?", "obojętnie");

        DisplayStrategy slashDisplayStrategy = new SlashDisplayStrategy();
        InputStrategy consoleInputStrategy = new ConsoleInputStrategy();

        PersistenceStrategy persistenceStrategy = new MapPersistenceStrategy();
        PersistenceContext persistenceContext = new PersistenceContext(persistenceStrategy);

        InputContext inputContext = new InputContext(consoleInputStrategy);
        DisplayContext displayContext = new DisplayContext();
        displayContext.setDisplayStrategy(slashDisplayStrategy);
        FileWriterService fileWriterService = new FileWriterService();
        FileReaderService fileReaderService = new FileReaderService();
        FormatterService formatterService = new FormatterService();


        persistenceContext.save(questionOne);
        persistenceContext.save(questionTwo);
        persistenceContext.save(questionThree);

        GameController gameController = new GameController(displayContext,inputContext,persistenceContext,formatterService,fileReaderService,fileWriterService);
        gameController.play();

   /* List<String> listToWrite = new ArrayList<>();
    String command;
    boolean exit = false;
    gameController.displayContext.printGreetings();
    while (!exit) {
      List<Flashcard> flashCardList = persistenceContext.getAllFlashcards();
      gameController.displayContext.printMenu();
      command = gameController.inputContext.getCommand();
      switch (command) {
        case "1":
          gameController.displayContext.setDisplayStrategy(new AstrixDisplayStrategy());
          break;
        case "4":
          fileService.readFile("brak");
          break;
        case "5":
          fileService.writeLines(listToWrite);
          break;
        case "6":
          for (int i = 0; i < flashCardList.size(); i++) {
            displayContext.printMessage(flashCardList.get(i).getQuestion());
          }
          break;
        case "7":
          while (true) {
            System.out.println("Put the question in");
            String question = gameController.inputContext.getQuestion();
            if (persistenceContext.getByQuestion(question) == null) {
              System.out.println("Give me the answer");
              persistenceContext.save(new Flashcard(question, gameController.inputContext.getAnswer()));
              break;
            }
            System.out.println("This question already exists");
            }
        case "8":
          displayContext.printGoodbye();
          exit = true;
          break;
          }
      }*/
    }
}

