package com.sm.support.controller;

import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class BlogDBConfig {

  private static final String DB_HOST = System.getenv("BLOG_DB_HOST");
  private static final String DB_USER = System.getenv("BLOG_DB_USER");
  private static final String DB_PASSWORD = System.getenv("BLOG_DB_PASSWORD");
  private static final String DB_NAME = System.getenv("BLOG_DB_NAME");
  private static final String DB_PORT = System.getenv("BLOG_DB_PORT");

  private static final String URL_FORMAT = "jdbc:mysql://%s:%s/%s";

  private static Connection dbConn;

  public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    String URL = String.format(URL_FORMAT, DB_HOST, DB_PORT, DB_NAME);

    System.out.println("DB_DETAILS::" + URL);

    if (dbConn == null || dbConn.isClosed())
      // Establish the connection
      dbConn = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);

    return dbConn;
  }

  public static Connection getDBConnectionForLocalTesting() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    String jdbcUrl = "jdbc:mysql://localhost:3306/blogdb";
    String username = "admin";
    String password = "admin@123";

    System.out.println("DB_DETAILS::" + jdbcUrl);

    if (dbConn == null || dbConn.isClosed())
      // Establish the connection
      dbConn = DriverManager.getConnection(jdbcUrl, username, password);

    return dbConn;
  }
}
