package com.sm.support.controller;

import com.sm.support.dao.BlogService;
import com.sm.support.dto.Blog;
import com.sm.support.dto.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/blog")
public class BlogRestApi {

  @Autowired
  BlogService blogService;


  @GetMapping("/{id}")
  public Blog getBlog(@PathVariable String id) throws SQLException, ClassNotFoundException {
    return blogService.getBlogById(id);
  }

  @PostMapping
  public ID createBlog(@RequestBody Blog blog) throws SQLException, ClassNotFoundException {
    long blogId = blogService.createBlog(blog);
    return new ID(blogId);
  }
}
