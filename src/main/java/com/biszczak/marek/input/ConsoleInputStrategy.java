package com.biszczak.marek.input;

import java.util.Scanner;

public class ConsoleInputStrategy implements InputStrategy {
  private Scanner scanner = new Scanner(System.in);

  public String getQuestion() {
    return scanner.nextLine();
  }

  public String getAnswer() {
    return scanner.nextLine();
  }

  public String getCommand() {
    return scanner.nextLine();
  }
}
