package com.titoosemobor.moviegenius.Controller;

import com.titoosemobor.moviegenius.Entity.User;
import com.titoosemobor.moviegenius.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers () {
    return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
  }
}
