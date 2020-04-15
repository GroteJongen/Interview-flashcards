package com.biszczak.marek.display;

import com.biszczak.marek.Flashcard;
import com.biszczak.marek.display.themes.Themes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayContext {
  private DisplayStrategy displayStrategy;

  private List<String> themes =
      Arrays.stream(Themes.values()).map(Themes::getThemeName).collect(Collectors.toList());

  private List<String> menuOptions =
      Arrays.stream(MenuOptions.values())
          .map(MenuOptions::getOptionName)
          .collect(Collectors.toList());

  public void printMenu() {
    displayStrategy.printList(this.menuOptions);
  }

  public void printGreetings() {
    String greeting = "Hello Dear User";
    displayStrategy.printMessage(greeting);
  }

  public void printGoodbye() {
    String goodbye = "Goodbye Dear User";
    displayStrategy.printMessage(goodbye);
  }

  public void printMessage(String msg) {
    displayStrategy.printMessage(msg);
  }

  public void showAllQuestions(List<Flashcard> flashcards) {
    displayStrategy.showAllQuestions(flashcards);
  }

  public void printThemes() {
    displayStrategy.printList(themes);
  }

  public void setDisplayStrategy(DisplayStrategy displayStrategy) {
    this.displayStrategy = displayStrategy;
  }
}
