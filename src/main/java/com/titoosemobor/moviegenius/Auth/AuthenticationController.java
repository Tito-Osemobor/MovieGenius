package com.titoosemobor.moviegenius.Auth;

import com.titoosemobor.moviegenius.Exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  @Autowired
  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<?> register (
    @RequestBody RegisterRequest request
  ) {
    try {
        return ResponseEntity.ok(authenticationService.register(request));
    } catch (UserException.InvalidInputException | UserException.InvalidInformationException |
             UserException.EmailAlreadyUsedException | UserException.PasswordMismatchException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> register (
    @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }
}
