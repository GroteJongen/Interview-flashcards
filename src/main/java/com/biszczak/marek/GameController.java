package com.biszczak.marek;

import com.biszczak.marek.display.AstrixDisplayStrategy;
import com.biszczak.marek.display.DisplayContext;
import com.biszczak.marek.input.InputContext;
import com.biszczak.marek.persistence.*;

import java.util.List;

public class GameController {
  static final String ADD_QUESTION_MSG = "Put the question in";
  static final String ADD_ANSWER_MSG = "Give me the answer";
  static final String QUESTION_ALREADY_EXISTS_MSG = "This question already exists";
  static final String FLASHCARDS_SAVED_MSG = "Flashcards have been saved";
  static final String FLASHCARDS_IMPORTED_MSG = "Flashcards have been imported";
  static final String FILENAME_INPUT_MSG = "Put filename in";
  static final String FILE_RESOURCE_PATH_MSG = "Put resource path in";
  static final String CORRECT_ANSWER = "Correct answer";

  private DisplayContext displayContext;
  private InputContext inputContext;
  private PersistenceContext persistenceContext;
  private FileWriterService fileService;
  private FormatterService formatterService;
  private FileReaderService fileReaderService;

  public GameController(DisplayContext displayContext, InputContext inputContext) {
    this.displayContext = displayContext;
    this.inputContext = inputContext;
  }

  public void play() {
    boolean exit = false;

    String command;
    displayContext.printGreetings();
    while (!exit) {
      displayContext.printMenu();
      command = inputContext.getCommand();
      switch (command) {
        case "1":
          displayContext.setDisplayStrategy(new AstrixDisplayStrategy());
          break;
        case "2":
          ask();
          break;
        case "3":
          System.out.println("Theme których jeszcze nie znamy");
          break;
        case "4":
          importFlashcards();
          break;
        case "5":
          exportFlashcards();
          break;
        case "6":
          showAllQuestions();
          break;
        case "7":
          addFlashcard();
          break;
        case "8":
          displayContext.printGoodbye();
          exit = true;
      }
    }
  }

  private void showAllQuestions() {
    List<Flashcard> flashcards = persistenceContext.getAllFlashcards();
    for (int i = 0; i < flashcards.size(); i++) {
      displayContext.printMessage(flashcards.get(i).getQuestion());
    }
  }

  private void addFlashcard() {
    displayContext.printMessage(ADD_QUESTION_MSG);
    String question = inputContext.getQuestion();
    if (persistenceContext.getByQuestion(question) == null) {
      displayContext.printMessage(ADD_ANSWER_MSG);
      persistenceContext.save(new Flashcard(question, inputContext.getAnswer()));
    }
    displayContext.printMessage(QUESTION_ALREADY_EXISTS_MSG);
  }

  private void importFlashcards() {
    String filename = inputContext.getCommand();
    String resourcePath = inputContext.getCommand();
    List<Flashcard> flashcards =
        formatterService.getFlashcardsToFormat(fileReaderService.readFile(filename, resourcePath));
    for (int i = 0; i < flashcards.size(); i++) {
      persistenceContext.save(flashcards.get(i));
    }
    displayContext.printMessage(FLASHCARDS_IMPORTED_MSG);
  }

  private void exportFlashcards() {
    displayContext.printMessage(FILENAME_INPUT_MSG);
    String filename = inputContext.getCommand();
    displayContext.printMessage(FILE_RESOURCE_PATH_MSG);
    String resourcePath = inputContext.getCommand();
    fileService.writeLines(persistenceContext.getAllFlashcards(), resourcePath, filename);
    displayContext.printMessage(FLASHCARDS_SAVED_MSG);
  }

  private void ask() {
    Flashcard flashcard;
    RandomQuestsionService randomQuestionService = new RandomQuestsionService(persistenceContext.getAllQuestionsToList());
    int timesToAsk = Integer.parseInt(inputContext.getCommand());

    for (int i = 0; i < timesToAsk; i++) {
      String question = randomQuestionService.getRandomQuestion();
      displayContext.printMessage("\"Print the definition of " + "\"" + question + "\"" + ":");
      String userAnswer = inputContext.getAnswer();
      flashcard = persistenceContext.getByQuestion(question);
      String correctAnswer = flashcard.getAnswer();
      if (userAnswer.equals(correctAnswer)) {
        displayContext.printMessage(CORRECT_ANSWER);
        continue;
      }
      displayContext.printMessage("Wrong answer. The correct one is " + "\"" + correctAnswer + "\"" + ".");
      }
    }
  }
