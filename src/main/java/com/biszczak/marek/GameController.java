package com.biszczak.marek;

import com.biszczak.marek.display.themes.AstrixTheme;
import com.biszczak.marek.display.DisplayContext;
import com.biszczak.marek.display.themes.SlashTheme;
import com.biszczak.marek.display.themes.TriangleTheme;
import com.biszczak.marek.exceptions.EmptyQuestionRepositoryException;
import com.biszczak.marek.exceptions.WrongFileFormatException;
import com.biszczak.marek.exceptions.WrongFilePathException;
import com.biszczak.marek.input.InputContext;
import com.biszczak.marek.persistence.*;

import java.util.List;

class GameController {
  private static final String ADD_QUESTION_MSG = "Put the question in";
  private static final String ADD_ANSWER_MSG = "Give me the answer";
  private static final String QUESTION_ALREADY_EXISTS_MSG = "This question already exists";
  private static final String FLASHCARDS_SAVED_MSG = "Flashcards have been saved";
  private static final String FILENAME_INPUT_MSG = "Put filename in";
  private static final String CORRECT_ANSWER = "Correct answer";
  private static final String WRONG_ANSWER_MESSAGE = "Wrong answer. The correct one is \"%s\":";
  private static final String PRINT_THE_DEFINITION = "Print the definition of \"%s\":";
  private static final String INPUT_TO_DELETE_MSG = "Give me the question";
  private static final String TIMES_TO_ASK_MSG = "How many times to ask";
  private static final String FLASHCARD_DELETED_MSG = "Flashcard deleted";
  private static final String FLASHCARD_NOT_DELETED_MSG = "Flashcard not found";
  private static final String WRONG_NUMBER_FORMAT_MSG = "Give me valid number please";
  private static final String EMPTY_MAP_REPOSITORY_EXCEPTION =
      "There are no questions on your list";
  private static final String WRONG_FILEPATH_EXCEPTION = "Wrong filepath given";
  private static final String WRONG_FILE_FORMAT_EXCEPTION = "Wrong file format given";


  private DisplayContext displayContext;
  private InputContext inputContext;
  private PersistenceContext persistenceContext;
  private FormatterService formatterService;
  private FileReaderService fileReaderService;
  private FileWriterService fileWriterService;

  GameController(
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
    // TODO fool proof all methods
    while (!exit) {
      displayContext.printMenu();
      command = inputContext.getCommand();
      switch (command) {
        case "1":
        case "change theme":
          changeTheme();
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
        case "delete":
          delete();
          break;
        case "9":
        case "exit":
          exit = exit();
      }
    }
  }

  private void showAllQuestions() {
    List<Flashcard> flashcards = persistenceContext.getAllFlashcards();
    displayContext.showAllQuestions(flashcards);
  }

  private void add() {
    displayContext.printMessage(ADD_QUESTION_MSG);
    String question = inputContext.getQuestion();
    if (persistenceContext.getByQuestion(question) != null) {
      displayContext.printMessage(QUESTION_ALREADY_EXISTS_MSG);
      return;
    }
    displayContext.printMessage(ADD_ANSWER_MSG);
    persistenceContext.save(new Flashcard(question, inputContext.getAnswer()));
  }

  private void importFlashcards() {
    try {
      print(FILENAME_INPUT_MSG);
      String filename = inputContext.getCommand();
      List<Flashcard> flashcards =
          formatterService.getFlashcardsToFormat(fileReaderService.readFile(filename));
      for (Flashcard flashcard : flashcards) {
        persistenceContext.save(flashcard);
      }
    } catch (WrongFilePathException e) {
      print(WRONG_FILEPATH_EXCEPTION);
    } catch (WrongFileFormatException e){
      print(WRONG_FILE_FORMAT_EXCEPTION);
    }
  }

  private void export() {
    displayContext.printMessage(FILENAME_INPUT_MSG);
    String filename = inputContext.getCommand();
    fileWriterService.writeLines(persistenceContext.getAllFlashcards(), filename);
    displayContext.printMessage(FLASHCARDS_SAVED_MSG);
  }

  private void ask() {
    RandomQuestionService randomQuestionService =
        new RandomQuestionService(persistenceContext.getAllQuestionsToList());
    print(TIMES_TO_ASK_MSG);
    int timesToAsk = getValidInteger();

    for (int i = 0; i < timesToAsk; i++) {
      try {
        String randomQuestion = randomQuestionService.getRandomQuestion();
        String correctAnswer = (persistenceContext.getByQuestion(randomQuestion)).getAnswer();
        print(String.format(PRINT_THE_DEFINITION, randomQuestion));
        String userAnswer = inputContext.getAnswer();

        if (userAnswer.equals(correctAnswer)) {
          print(CORRECT_ANSWER);
          continue;
        }
        print(String.format(WRONG_ANSWER_MESSAGE, correctAnswer));
      } catch (EmptyQuestionRepositoryException error) {
        print(EMPTY_MAP_REPOSITORY_EXCEPTION);
        break;
      }
    }
  }

  private void delete() {
    print(INPUT_TO_DELETE_MSG);
    String question = inputContext.getQuestion();
    if (persistenceContext.getByQuestion(question) != null) {
      persistenceContext.delete(persistenceContext.getByQuestion(question));
      print(FLASHCARD_DELETED_MSG);
      return;
    }
    print(FLASHCARD_NOT_DELETED_MSG);
  }

  private void changeTheme() {
    displayContext.printThemes();
    String command = inputContext.getCommand();
    switch (command) {
      case "1":
      case "astrix":
        displayContext.setDisplayStrategy(new AstrixTheme());
        break;
      case "2":
      case "slash":
        displayContext.setDisplayStrategy(new SlashTheme());
        break;
      case "3":
      case "triangle":
        displayContext.setDisplayStrategy(new TriangleTheme());
        break;
      case "4":
      case "go back":
        break;
    }
  }

  private void print(String msg) {
    displayContext.printMessage(msg);
  }

  private boolean exit() {
    displayContext.printGoodbye();
    return true;
  }

  private int getValidInteger() {
    while (true) {
      try {
        return Integer.parseInt(inputContext.getCommand());
      } catch (NumberFormatException e) {
        print(WRONG_NUMBER_FORMAT_MSG);
      }
    }
  }
}
