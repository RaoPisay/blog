package com.sm.support.dao;


import com.sm.support.controller.BlogDBConfig;
import com.sm.support.dto.Blog;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Service
public class BlogService {

  public Blog getBlogById(String id) throws SQLException, ClassNotFoundException {
    String sql = "SELECT title, content, author FROM blog where id=" + id;
    Statement sqlStatement = BlogDBConfig.getDBConnection().createStatement();

    // Execute a query

    ResultSet resultSet = sqlStatement.executeQuery(sql);

    Blog blog = null;

    // Process the result set
    while (resultSet.next()) {
      //int id = resultSet.getInt("id");
      String title = resultSet.getString("title");
      String content = resultSet.getString("content");
      String author = resultSet.getString("author");

      blog = new Blog(title, author, content);

      // Print the results
      System.out.println("ID: " + id);
    }

    return blog;
  }

  public long createBlog(Blog blog) throws SQLException, ClassNotFoundException {
    String sql = "INSERT INTO blog (title, content, author) VALUES (?, ?, ?)";
    // Create a PreparedStatement and return generated keys
    PreparedStatement preparedStatement = BlogDBConfig.getDBConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    // Set the parameters
    preparedStatement.setString(1, blog.title());
    preparedStatement.setString(2, blog.content());
    preparedStatement.setString(3, blog.author());
    // Execute the query
    int rowsAffected = preparedStatement.executeUpdate(); // Get the generated key (ID)

    long id = -1;

    if (rowsAffected > 0) {
      ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
      if (generatedKeys.next()) {
         id = generatedKeys.getLong(1);
        System.out.println("A new blog post was inserted successfully with ID: " + id);
      } else {
        System.out.println("No ID obtained.");
      }
    } else {
      System.out.println("Something went wrong, no blog post was inserted.");
    }

    return id;

  }
}
