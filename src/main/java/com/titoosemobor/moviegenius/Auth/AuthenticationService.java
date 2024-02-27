package com.titoosemobor.moviegenius.Auth;

import com.titoosemobor.moviegenius.Config.JWTService;
import com.titoosemobor.moviegenius.Entity.Role;
import com.titoosemobor.moviegenius.Entity.User;
import com.titoosemobor.moviegenius.Exception.UserException;
import com.titoosemobor.moviegenius.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JWTService jwtService;
  private final AuthenticationManager authenticationManager;

  public Optional<AuthenticationResponse> register(RegisterRequest registerRequest) {
    if (registerRequest.getEmail().isEmpty() ||
      registerRequest.getPassword().isEmpty() ||
      registerRequest.getReEnterPassword().isEmpty()){
      throw new UserException.InvalidInputException("Enter email and password");
    }
    if(isEmailAlreadyUsed(registerRequest.getEmail())) {
      throw new UserException.EmailAlreadyUsedException("Email already in use");
    }
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    if(!isValidRegex(registerRequest.getEmail(), emailRegex)) {
      throw new UserException.InvalidInformationException("Email is invalid");
    }
    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
    if (!isValidRegex(registerRequest.getPassword(), passwordRegex)) {
      throw new UserException.InvalidInformationException("Password is invalid");
    }
    if(!registerRequest.getPassword().equals(registerRequest.getReEnterPassword())) {
      throw new UserException.PasswordMismatchException("Passwords do not match");
    }
    User user = User.builder()
      .email(registerRequest.getEmail())
      .password(passwordEncoder.encode(registerRequest.getPassword()))
      .role(Role.USER)
      .createdAt(new Timestamp(System.currentTimeMillis()))
      .build();
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user, registerRequest.getRememberMe());
    return Optional.ofNullable(AuthenticationResponse.builder()
      .token(jwtToken)
      .build());
  }

  public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        authRequest.getEmail(),
        authRequest.getPassword()
      )
    );
    User user = userRepository.findUserByEmail(authRequest.getEmail())
      .orElseThrow(() -> new UserException.UserNotFoundException("Incorrect email and/or password"));
    var jwtToken = jwtService.generateToken(user, authRequest.getRememberMe());
    return AuthenticationResponse.builder()
      .token(jwtToken)
      .build();
  }

  public boolean isEmailAlreadyUsed(String email) {
    Optional<User> user = userRepository.findUserByEmail(email);
    return user.isPresent();
  }

  public Boolean isValidRegex(String newPassword, String regex) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(newPassword);
    return matcher.matches();
  }
}
