package com.biszczak.marek;

import com.biszczak.marek.display.DisplayContext;
import com.biszczak.marek.display.DisplayStrategy;
import com.biszczak.marek.display.themes.SlashTheme;
import com.biszczak.marek.input.ConsoleInputStrategy;
import com.biszczak.marek.input.InputContext;
import com.biszczak.marek.input.InputStrategy;
import com.biszczak.marek.persistence.MapRepository;
import com.biszczak.marek.persistence.PersistenceContext;
import com.biszczak.marek.persistence.PersistenceStrategy;
import com.biszczak.marek.persistence.FileWriterService;
import com.biszczak.marek.persistence.FileReaderService;
import com.biszczak.marek.persistence.FormatterService;

public class Main {
  public static void main(String[] args) {

    DisplayStrategy slashDisplayStrategy = new SlashTheme();
    InputStrategy consoleInputStrategy = new ConsoleInputStrategy();

    PersistenceStrategy persistenceStrategy = new MapRepository();
    PersistenceContext persistenceContext = new PersistenceContext(persistenceStrategy);

    InputContext inputContext = new InputContext(consoleInputStrategy);
    DisplayContext displayContext = new DisplayContext();
    displayContext.setDisplayStrategy(slashDisplayStrategy);
    FileWriterService fileWriterService = new FileWriterService();
    FileReaderService fileReaderService = new FileReaderService();
    FormatterService formatterService = new FormatterService();

    GameController gameController =
        new GameController(
            displayContext,
            inputContext,
            persistenceContext,
            formatterService,
            fileReaderService,
            fileWriterService);
    gameController.play();
  }
}
