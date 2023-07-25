package com.titoosemobor.moviegenius.Controller;

import com.titoosemobor.moviegenius.DTO.UserDTORequest;
import com.titoosemobor.moviegenius.DTO.UserDTOResponse;
import com.titoosemobor.moviegenius.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<UserDTOResponse>> getUsers () {
    return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<Optional<UserDTOResponse>> getUserById(@PathVariable Long id) {
    return new ResponseEntity<>(userService.userById(id), HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<Optional<UserDTOResponse>> getUserByEmailAndPassword(@RequestBody UserDTORequest userDTORequest) {
    return new ResponseEntity<>(userService.userByEmailAndPassword(userDTORequest), HttpStatus.OK);
  }

  @PostMapping("/sign-up")
  public ResponseEntity<String> createNewUser(@RequestBody UserDTORequest userDTORequest) {
    return null;
  }
}
