package com.biszczak.marek.display;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DisplayContext {
  private DisplayStrategy displayStrategy;

  private List<String> menuOptions =
      Arrays.stream(MenuOptions.values())
          .map(MenuOptions::getOptionName)
          .collect(Collectors.toList());
  private Set<String> strategies = new HashSet<>();

  private final String greeting = "Hello Dear User";
  private final String goodbye = "Goodbye Dear User";

  public void printMenu() {
    this.displayStrategy.printMenu(this.menuOptions);
  }

  public void printGreetings() {
    displayStrategy.printMessage(greeting);
  }

  public void printGoodbye() {
    displayStrategy.printMessage(goodbye);
  }
  public void printMessage(String msg){
    displayStrategy.printMessage(msg);
  }

  public void setDisplayStrategy(DisplayStrategy displayStrategy) {
    strategies.add(displayStrategy.getName());
    this.displayStrategy = displayStrategy;
  }
}
