package com.titoosemobor.moviegenius.Auth;

import com.titoosemobor.moviegenius.Exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  @Autowired
  private final AuthenticationService service;
  @PostMapping("/register")
  public ResponseEntity<?> register (
    @RequestBody RegisterRequest registerRequest
  ) {
    try {
      return ResponseEntity.ok(service.register(registerRequest));
    } catch (UserException.InvalidInputException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    } catch (UserException.EmailAlreadyUsedException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> register (
    @RequestBody AuthenticationRequest registerRequest
  ) {
    return ResponseEntity.ok(service.authenticate(registerRequest));
  }
}
