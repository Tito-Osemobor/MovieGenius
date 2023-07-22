package com.titoosemobor.moviegenius.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
  @GetMapping("/users")
  public ResponseEntity<String> getUsers () {
    return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
  }
}
