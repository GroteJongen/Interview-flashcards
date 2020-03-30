package com.biszczak.marek.display.themes;

import com.biszczak.marek.display.DisplayStrategy;

import java.util.List;

public class AstrixTheme implements DisplayStrategy {

  private final String BOARDER = "***********************************************";
  private final String NEW_LINE = "\n";

  @Override
  public void printList(List<String> options) {
    final String arrow = " --> ";
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append(BOARDER + NEW_LINE);
    for (int i = 0; i < options.size(); i++) {

      stringBuilder.append(i + 1).append(arrow).append(options.get(i)).append(NEW_LINE);
    }
    stringBuilder.append(BOARDER + NEW_LINE);
    System.out.println(stringBuilder);
  }

  @Override
  public void printMessage(String message) {
    String messageToPrint = (BOARDER + NEW_LINE) + message + NEW_LINE + BOARDER + NEW_LINE;
    System.out.print(messageToPrint);
  }
}
