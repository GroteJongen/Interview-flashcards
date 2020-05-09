package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlRepository implements PersistenceStrategy {
  private final String connectionString =
      "jdbc:mysql://localhost:3306/flashcarddb?&serverTimezone=UTC";
  private final String user = "root";
  private final String password = "goyz2v85";
  private final String tableName = "flashcards";
  private final String questionColumn = "question";
  private final String answerColumn = "answer";

  @Override
  public Flashcard save(Flashcard flashcard) {
    final String insertFormat = "INSERT INTO %s (%s,%s) VALUES ('%s', '%s');";
    final String selectFormat = "SELECT * FROM %s WHERE %s='%s'";
    try (Connection connection = DriverManager.getConnection(connectionString, user, password)) {
      Statement statement = connection.createStatement();
      statement.execute(
          String.format(
              insertFormat,
              tableName,
              questionColumn,
              answerColumn,
              flashcard.getQuestion(),
              flashcard.getAnswer()));
      final ResultSet resultSet =
          statement.executeQuery(
              String.format(selectFormat, tableName, questionColumn, flashcard.getQuestion()));
      resultSet.next();
      return new Flashcard(resultSet.getString(questionColumn), resultSet.getString(answerColumn));
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  @Override
  public Flashcard getByQuestion(String question) {
    final String selectFormat = "SELECT * FROM %s WHERE %s='%s'";
    try (Connection connection = DriverManager.getConnection(connectionString, user, password)) {
      Statement statement = connection.createStatement();

      final ResultSet resultSet =
          statement.executeQuery(String.format(selectFormat, tableName, questionColumn, question));
      resultSet.next();
      return new Flashcard(resultSet.getString(questionColumn), resultSet.getString(answerColumn));
    } catch (SQLException e) {
      System.out.println(e);
    }
    return null;
  }

  @Override
  public Flashcard getByAnswer(String answer) {
    final String selectFormat = "SELECT * FROM %s WHERE %s='%s'";
    try (Connection connection = DriverManager.getConnection(connectionString, user, password)) {
      Statement statement = connection.createStatement();

      final ResultSet resultSet =
          statement.executeQuery(String.format(selectFormat, tableName, answerColumn, answer));
      resultSet.next();
      return new Flashcard(resultSet.getString(questionColumn), resultSet.getString(answerColumn));
    } catch (SQLException e) {
      System.out.println(e);
    }
    return null;
  }

  @Override
  public List<Flashcard> getAllFlashcards() {
    final String selectFormat = "SELECT * FROM %s";
    List<Flashcard> flashcards = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection(connectionString, user, password)) {
      Statement statement = connection.createStatement();

      final ResultSet resultSet = statement.executeQuery(String.format(selectFormat, tableName));
      while (resultSet.next()) {
        flashcards.add(
            new Flashcard(resultSet.getString(questionColumn), resultSet.getString(answerColumn)));
        if (!resultSet.next()) {
          break;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flashcards;
  }

  @Override
  public void delete(Flashcard flashcard) {
    final String deleteFormat = "DELETE * FROM %s WHERE '%s'='%s'";
    try (Connection connection = DriverManager.getConnection(connectionString, user, password)) {
      Statement statement = connection.createStatement();
      statement.execute(
          String.format(deleteFormat, tableName, questionColumn, flashcard.getQuestion()));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<String> getAllQuestionsToList() {
    final String selectFormat = "SELECT %s FROM %s";
    List<String> questions = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection(connectionString, user, password)) {
      Statement statement = connection.createStatement();
      ResultSet resultSet =
          statement.executeQuery(String.format(selectFormat, questionColumn, tableName));
      while (resultSet.next()) {
        if (!resultSet.next()) {
          break;
        }
        questions.add(resultSet.getString(questionColumn));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return questions;
  }
}
