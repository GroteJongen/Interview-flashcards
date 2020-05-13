package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MySqlRepositoryTest {
  private MySqlRepository mySqlRepository =
      MySqlRepository.fromPropertyFile(
      );
  private final String connectionString = mySqlRepository.getConnectionString();
  private final String user = mySqlRepository.getUser();
  private final String password = mySqlRepository.getPassword();

  @BeforeAll
  void prepareDatabase() throws SQLException {
    Connection connection = DriverManager.getConnection(connectionString, user, password);
    connection.setAutoCommit(false);
    PreparedStatement statement =
        connection.prepareStatement("CREATE DATABASE IF NOT EXISTS flashcardtest");
    statement.execute();

    connection.setCatalog("flashcardtest");
    PreparedStatement insertStatement =
        connection.prepareStatement(
            "INSERT INTO flashcards (question,answer) VALUES (\"Q1\",\"A1\");");
    insertStatement.execute();
    insertStatement =
        connection.prepareStatement(
            "INSERT INTO flashcards (question,answer) VALUES (\"Q2\",\"A2\");");
    insertStatement.execute();
    insertStatement =
        connection.prepareStatement(
            "INSERT INTO flashcards (question,answer) VALUES (\"Q3\",\"A3\");");
    insertStatement.execute();
    insertStatement =
        connection.prepareStatement(
            "INSERT INTO flashcards (question,answer) VALUES (\"Q4\",\"A4\");");
    insertStatement.execute();
    insertStatement =
        connection.prepareStatement(
            "INSERT INTO flashcards (question,answer) VALUES (\"Q5\",\"A5\");");
    insertStatement.execute();

    statement.execute();
    connection.commit();
    statement.close();
  }

  @AfterAll
  void cleanDataBase() throws SQLException {
    Connection connection = DriverManager.getConnection(connectionString, user, password);
    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM flashcards");
    preparedStatement.execute();
    preparedStatement.close();
  }

  void cleanTableAfterSaveMethod() throws SQLException {
    Connection connection = DriverManager.getConnection(connectionString, user, password);
    PreparedStatement preparedStatement =
        connection.prepareStatement("DELETE FROM flashcards WHERE question='Q6'");
    preparedStatement.execute();
    preparedStatement.close();
  }

  @Test
  @Order(0)
  void shouldReturnListOfFlashcards() {
    List<Flashcard> flashcards;
    List<Flashcard> excpectedFlashcards =
        Arrays.asList(
            new Flashcard("Q1", "A1"),
            new Flashcard("Q2", "A2"),
            new Flashcard("Q3", "A3"),
            new Flashcard("Q4", "A4"),
            new Flashcard("Q5", "A5"));

    flashcards = mySqlRepository.getAllFlashcards();
    assertEquals(excpectedFlashcards, flashcards);
  }

  @Test
  @Order(3)
  void shouldSaveAndReturnValidFlashcard() throws SQLException {
    Flashcard flashcardToSave = new Flashcard("Q6", "A6");
    Flashcard returnedFlashcard = mySqlRepository.save(flashcardToSave);
    assertEquals(flashcardToSave, returnedFlashcard);
    cleanTableAfterSaveMethod();
  }

  @Test
  @Order(2)
  void shouldReturnValidFlashcardByQuestion() {
    Flashcard flashcard = new Flashcard("Q1", "A1");
    Flashcard flashcardToGet = mySqlRepository.getByQuestion("Q1");
    assertEquals(flashcard, flashcardToGet);
  }

  @Test
  @Order(1)
  void shouldReturnValidFlashcardByAnswer() {
    Flashcard flashcard = new Flashcard("Q1", "A1");
    Flashcard flashcardToGet = mySqlRepository.getByAnswer("A1");
    assertEquals(flashcard, flashcardToGet);
  }

  @Test
  @Order(4)
  void shouldReturnValidListOfQuestions() {
    List<String> questions = new ArrayList<>();
    questions.add("Q1");
    questions.add("Q2");
    questions.add("Q3");
    questions.add("Q4");
    questions.add("Q5");
    List<String> questionsFromDatabase = mySqlRepository.getAllQuestionsToList();
    assertEquals(questions, questionsFromDatabase);
  }
}
