package com.example.evaexchange.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evaexchange.dao.UserRepository;
import com.example.evaexchange.model.User;

@RestController
@RequestMapping("/rest")
@CrossOrigin(allowedHeaders = "*", origins = "*")  //Optional Endpoint: To list all the users registered
class UserController {

  private final UserRepository repository;

  UserController(UserRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/users")
  List<User> all() {
    return (List<User>) repository.findAll();
  }

}
