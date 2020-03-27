package com.biszczak.marek;

import com.biszczak.marek.display.AstrixDisplayStrategy;
import com.biszczak.marek.display.DisplayContext;
import com.biszczak.marek.input.InputContext;
import com.biszczak.marek.persistence.*;

import java.util.List;
import java.util.stream.Stream;

class GameController {
    private static final String ADD_QUESTION_MSG = "Put the question in";
    private static final String ADD_ANSWER_MSG = "Give me the answer";
    private static final String QUESTION_ALREADY_EXISTS_MSG = "This question already exists";
    private static final String FLASHCARDS_SAVED_MSG = "Flashcards have been saved";
    private static final String FLASHCARDS_IMPORTED_MSG = "Flashcards have been imported";
    private static final String FILENAME_INPUT_MSG = "Put filename in";
    private static final String FILE_RESOURCE_PATH_MSG = "Put resource path in";
    private static final String CORRECT_ANSWER = "Correct answer";
    private static final String WRONG_ANSWER_MESSAGE = "Wrong answer. The correct one is \"%s\":";
    private static final String PRINT_THE_DEFINITION = "Print the definition of \"%s\":";

    private DisplayContext displayContext;
    private InputContext inputContext;
    private PersistenceContext persistenceContext;
    //TODO NULLPOINTERY qrwo
    private FileWriterService fileService;
    private FormatterService formatterService;
    private FileReaderService fileReaderService;

    GameController(DisplayContext displayContext, InputContext inputContext, PersistenceContext persistenceContext) {
        this.displayContext = displayContext;
        this.inputContext = inputContext;
        this.persistenceContext = persistenceContext;
    }

    void play() {
        boolean exit = false;

        String command;
        displayContext.printGreetings();
        while (!exit) {
            displayContext.printMenu();
            command = inputContext.getCommand();
            switch (command) {
                // TODO As app user I want to be able to pick option using option number or option name
                case "1":
                    //TODO make menu for choosing display, add at least 1 more display implementation
                    displayContext.setDisplayStrategy(new AstrixDisplayStrategy());
                    break;
                case "2":
                    //TODO Add asking for number of questions
                    ask();
                    break;
                case "3":
                    // TODO remove me
                    System.out.println("Theme których jeszcze nie znamy");
                    break;
                case "4":
                    //TODO shorten method name to import and export
                    // implement asking for filename
                    importFlashcards();
                    break;
                case "5":
                    // TODO implement asking for filename
                    exportFlashcards();
                    break;
                case "6":
                    //TODO Implement FormatterService and print using
                    // border + list + border, rather than border + element + border +element ...
                    showAllQuestions();
                    break;
                case "7":
                    //TODO shorten to add()
                    addFlashcard();
                    break;
                case "8":
                    //TODO make it one method.
                    displayContext.printGoodbye();
                    exit = true;
            }
        }
    }

    private void showAllQuestions() {
        List<Flashcard> flashcards = persistenceContext.getAllFlashcards();
        for (Flashcard flashcard : flashcards) {
            displayContext.printMessage(flashcard.getQuestion());
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

        //TODO ??!!
        String filename = inputContext.getCommand();
        String resourcePath = inputContext.getCommand();
        List<Flashcard> flashcards =
                formatterService.getFlashcardsToFormat(fileReaderService.readFile(filename, resourcePath));
        for (Flashcard flashcard : flashcards) {
            persistenceContext.save(flashcard);
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
        RandomQuestsionService randomQuestionService = new RandomQuestsionService(persistenceContext.getAllQuestionsToList());

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

    private void print(String msg) {
        displayContext.printMessage(msg);
    }
}
