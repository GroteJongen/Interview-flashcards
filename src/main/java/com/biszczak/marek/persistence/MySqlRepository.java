package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MySqlRepository implements PersistenceStrategy {
  private String connectionString;
  private String user;
  private String password;
  private String tableName;
  private String questionColumn;
  private String answerColumn;

  private MySqlRepository() {}

  static MySqlRepository fromPropertyFile() {
    MySqlRepository mySqlRepository = new MySqlRepository();
    Properties properties = readPropertyFile();
    mySqlRepository.setConnectionString(
        properties.getProperty("databaseAddress")
            + properties.getProperty("databaseName")
            + properties.getProperty("parameters"));
    mySqlRepository.setUser(properties.getProperty("user"));
    mySqlRepository.setPassword(properties.getProperty("password"));
    mySqlRepository.setTableName(properties.getProperty("tableName"));
    mySqlRepository.setQuestionColumn(properties.getProperty("questionColumnName"));
    mySqlRepository.setAnswerColumn(properties.getProperty("answerColumnName"));
    return mySqlRepository;
  }

  private static Properties readPropertyFile() {
    Properties property = null;
    try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Marek\\interviewflashcards\\src\\test\\resources\\mySqlTest.properties")) {
      property = new Properties();
      property.load(fileInputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return property;
  }

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
        questions.add(resultSet.getString(questionColumn));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return questions;
  }

  String getConnectionString() {
    return connectionString;
  }

  private void setConnectionString(String connectionString) {
    this.connectionString = connectionString;
  }

  String getUser() {
    return user;
  }

  private void setUser(String user) {
    this.user = user;
  }

  String getPassword() {
    return password;
  }

  private void setPassword(String password) {
    this.password = password;
  }

  private void setTableName(String tableName) {
    this.tableName = tableName;
  }

  private void setQuestionColumn(String questionColumn) {
    this.questionColumn = questionColumn;
  }

  private void setAnswerColumn(String answerColumn) {
    this.answerColumn = answerColumn;
  }
}
