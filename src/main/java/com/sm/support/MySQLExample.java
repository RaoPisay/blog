package com.sm.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLExample {
  public static void main(String[] args) {
    // Database credentials
    String jdbcUrl = "jdbc:mysql://localhost:3306/blogdb";
    String username = "admin";
    String password = "admin@123";

    Connection connection = null;
    Statement statement = null;

    try {
      // Load MySQL JDBC Driver
      Class.forName("com.mysql.cj.jdbc.Driver");

      // Establish the connection
      connection = DriverManager.getConnection(jdbcUrl, username, password);

      // Create a statement
      statement = connection.createStatement();

      // Execute a query
      String sql = "SELECT id, title, content, author, created_at FROM blog";
      ResultSet resultSet = statement.executeQuery(sql);

      // Process the result set
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        String author = resultSet.getString("author");
        String createdAt = resultSet.getString("created_at");

        // Print the results
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Content: " + content);
        System.out.println("Author: " + author);
        System.out.println("Created At: " + createdAt);
        System.out.println();
      }

      // Close the result set
      resultSet.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (statement != null) statement.close();
        if (connection != null) connection.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
