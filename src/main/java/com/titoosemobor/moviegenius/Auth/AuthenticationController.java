package com.titoosemobor.moviegenius.Auth;

import com.titoosemobor.moviegenius.Exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "${allowed.origins}")
@RequiredArgsConstructor
public class AuthenticationController {
  @Value("${allowed.origins}")
  private String allowedOrigins;

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
  public ResponseEntity<?> signIn (
    @RequestBody AuthenticationRequest request
  ) {
    try {
      return ResponseEntity.ok(authenticationService.authenticate(request));
    } catch (UserException.AuthenticationFailedException | UserException.InvalidInformationException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

  @GetMapping("/exists")
  public ResponseEntity<?> exists (@RequestHeader String email) {
    return ResponseEntity.ok(authenticationService.isEmailAlreadyUsed(email));
  }
}
