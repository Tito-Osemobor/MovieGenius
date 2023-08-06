package com.titoosemobor.moviegenius.Controller;

import com.titoosemobor.moviegenius.DTO.PasswordUpdateDTO;
import com.titoosemobor.moviegenius.DTO.UserDTOResponse ;
import com.titoosemobor.moviegenius.Entity.User;
import com.titoosemobor.moviegenius.Exception.UserException;
import com.titoosemobor.moviegenius.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
  public ResponseEntity<?> getUserById(@PathVariable Long id,
                                       @AuthenticationPrincipal User authUser) {
    try {
      if (isOwnedByAuthenticatedUser(userService.userById(id).get(), authUser)) {
        return new ResponseEntity<>(userService.userById(id), HttpStatus.OK);
      }
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body("You are not authorized to access this profile.");
    } catch (UserException.UserNotFoundException ex){
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateUserPassword(@RequestBody PasswordUpdateDTO passwordUpdateDTO,
                                              @AuthenticationPrincipal User authUser) {
    try {
      if (isOwnedByAuthenticatedUser(userService.userByEmail(passwordUpdateDTO.getEmail()).get(), authUser)) {
        Optional<UserDTOResponse> userDTOResponseOptional = userService.updateUserPassword(passwordUpdateDTO);
        if (userDTOResponseOptional.isEmpty()) {
          return ResponseEntity.badRequest().body("Incorrect email and/or password");
        }
        return new ResponseEntity<>(userDTOResponseOptional, HttpStatus.OK);
      }
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body("You are not authorized to access this profile");
    } catch (UserException.InvalidInputException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    } catch (UserException.PasswordMismatchException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    } catch (UserException.UserNotFoundException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during password update.");
    }
  }

  public boolean isOwnedByAuthenticatedUser(UserDTOResponse requestedUser, User authUser) {
    return authUser.getEmail().equals(requestedUser.getEmail());
  }
}
