package com.biszczak.marek;

import com.biszczak.marek.display.AstrixDisplayStrategy;
import com.biszczak.marek.display.DisplayContext;
import com.biszczak.marek.display.SlashDisplayStrategy;
import com.biszczak.marek.display.TriangleDisplayStrategy;
import com.biszczak.marek.input.InputContext;
import com.biszczak.marek.persistence.*;

import java.util.List;

class GameController {
  private static final String ADD_QUESTION_MSG = "Put the question in";
  private static final String ADD_ANSWER_MSG = "Give me the answer";
  private static final String QUESTION_ALREADY_EXISTS_MSG = "This question already exists";
  private static final String FLASHCARDS_SAVED_MSG = "Flashcards have been saved";
  private static final String FLASHCARDS_IMPORTED_MSG = "Flashcards have been imported";
  private static final String FILENAME_INPUT_MSG = "Put filename in";
  private static final String CORRECT_ANSWER = "Correct answer";
  private static final String WRONG_ANSWER_MESSAGE = "Wrong answer. The correct one is \"%s\":";
  private static final String PRINT_THE_DEFINITION = "Print the definition of \"%s\":";
  private static final String TIMES_TO_ASK_MSG = "How many times to ask";

  private DisplayContext displayContext;
  private InputContext inputContext;
  private PersistenceContext persistenceContext;
  private FormatterService formatterService;
  private FileReaderService fileReaderService;
  private FileWriterService fileWriterService;

  public GameController(
      DisplayContext displayContext,
      InputContext inputContext,
      PersistenceContext persistenceContext,
      FormatterService formatterService,
      FileReaderService fileReaderService,
      FileWriterService fileWriterService) {
    this.displayContext = displayContext;
    this.inputContext = inputContext;
    this.persistenceContext = persistenceContext;
    this.fileWriterService = fileWriterService;
    this.formatterService = formatterService;
    this.fileReaderService = fileReaderService;
  }

  void play() {
    boolean exit = false;

    String command;
    displayContext.printGreetings();
    while (!exit) {
      displayContext.printMenu();
      command = inputContext.getCommand();
      switch (command) {
        case "1":
        case "change theme":
          chageTheme();
          break;
        case "2":
        case "ask":
          ask();
          break;
        case "3":
        case "Print themes":
          displayContext.printThemes();
          break;
        case "4":
        case "import":
          importFlashcards();
          break;
        case "5":
        case "export":
          export();
          break;
        case "6":
        case "show all":
          showAllQuestions();
          break;
        case "7":
        case "add":
          add();
          break;
        case "8":
        case "exit":
         exit = exit();
      }
    }
  }

  private void showAllQuestions() {
    List<String> flashcards = persistenceContext.getAllQuestionsToList();
    displayContext.printQuestions(flashcards);
  }

  private void add() {
    displayContext.printMessage(ADD_QUESTION_MSG);
    String question = inputContext.getQuestion();
    if (persistenceContext.getByQuestion(question) == null) {
      displayContext.printMessage(ADD_ANSWER_MSG);
      persistenceContext.save(new Flashcard(question, inputContext.getAnswer()));
    }
    displayContext.printMessage(QUESTION_ALREADY_EXISTS_MSG);
  }

  private void importFlashcards() {
    print(FILENAME_INPUT_MSG);
    String filename = inputContext.getCommand();
    List<Flashcard> flashcards =
        formatterService.getFlashcardsToFormat(fileReaderService.readFile(filename));
    for (Flashcard flashcard : flashcards) {
      persistenceContext.save(flashcard);
    }
    displayContext.printMessage(FLASHCARDS_IMPORTED_MSG);
  }

  private void export() {
    displayContext.printMessage(FILENAME_INPUT_MSG);
    String filename = inputContext.getCommand();
    fileWriterService.writeLines(persistenceContext.getAllFlashcards(), filename);
    displayContext.printMessage(FLASHCARDS_SAVED_MSG);
  }

  private void ask() {
    RandomQuestsionService randomQuestionService =
        new RandomQuestsionService(persistenceContext.getAllQuestionsToList());
    print(TIMES_TO_ASK_MSG);
    int timesToAsk = Integer.parseInt(inputContext.getCommand());

    for (int i = 0; i < timesToAsk; i++) {

      String randomQuestion = randomQuestionService.getRandomQuestion();
      String correctAnswer = (persistenceContext.getByQuestion(randomQuestion)).getAnswer();

      print(String.format(PRINT_THE_DEFINITION, randomQuestion));
      String userAnswer = inputContext.getAnswer();

      if (userAnswer.equals(correctAnswer)) {
        print(CORRECT_ANSWER);
        continue;
      }
      print(String.format(WRONG_ANSWER_MESSAGE, correctAnswer));
    }
  }

  private void chageTheme() {
    displayContext.printThemes();
    String command = inputContext.getCommand();
    switch (command) {
      case "1":
      case "astrix":
        displayContext.setDisplayStrategy(new AstrixDisplayStrategy());
        break;
      case "2":
      case "slash":
        displayContext.setDisplayStrategy(new SlashDisplayStrategy());
        break;
      case "3":
      case "triangle":
        displayContext.setDisplayStrategy(new TriangleDisplayStrategy());
        break;
    }
  }

  private void print(String msg) {
    displayContext.printMessage(msg);
  }
  private boolean exit(){
    displayContext.printGoodbye();
    return true;

  }
}
