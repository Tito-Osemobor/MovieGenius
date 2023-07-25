package com.titoosemobor.moviegenius.Controller;

import com.titoosemobor.moviegenius.DataTransferObject.UserDTO;
import com.titoosemobor.moviegenius.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<UserDTO>> getUsers () {
    return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<Optional<UserDTO>> getUserById(@PathVariable Long id) {
    return new ResponseEntity<>(userService.userById(id), HttpStatus.OK);
  }
}
