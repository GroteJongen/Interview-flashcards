package com.biszczak.marek;

import java.sql.*;

public class MySqlConnector {
  public static void main(String[] args) throws SQLException {
    final String connectionString = "jdbc:mysql://localhost:3306/flashcarddb?&serverTimezone=UTC";
    final String user = "root";
    final String password = "goyz2v85";
    try (Connection connection = DriverManager.getConnection(connectionString, user, password)) {
      Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM flashcards");

      while (resultSet.next()) {
        System.out.println(resultSet.getInt(1));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
