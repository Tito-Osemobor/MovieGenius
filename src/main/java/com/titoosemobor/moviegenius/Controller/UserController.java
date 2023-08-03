package com.titoosemobor.moviegenius.Controller;

import com.titoosemobor.moviegenius.DTO.PasswordUpdateDTO;
import com.titoosemobor.moviegenius.DTO.UserDTORequest;
import com.titoosemobor.moviegenius.DTO.UserDTOResponse;
import com.titoosemobor.moviegenius.Exception.UserException;
import com.titoosemobor.moviegenius.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moviegenius/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDTOResponse>> getUsers() {
    return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<UserDTOResponse>> getUserById(@PathVariable Long id) {
    return new ResponseEntity<>(userService.userById(id), HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<Optional<UserDTOResponse>> getUserByEmailAndPassword(@RequestBody UserDTORequest userDTORequest) {
    return new ResponseEntity<>(userService.userByEmailAndPassword(userDTORequest), HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateUserPassword(@RequestBody PasswordUpdateDTO passwordUpdateDTO) {
    try {
      Optional<UserDTOResponse> userDTOResponseOptional = userService.updateUserPassword(passwordUpdateDTO);
      if (userDTOResponseOptional.isEmpty()) {
        return ResponseEntity.badRequest().body("Invalid email or password");
      }
      return new ResponseEntity<>(userDTOResponseOptional, HttpStatus.OK);
    } catch (UserException.InvalidInputException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    } catch (UserException.PasswordMismatchException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    } catch (UserException.UserNotFoundException ex) {
      return ResponseEntity.notFound().build();
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during password update.");
    }

  }
}
