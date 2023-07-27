package com.titoosemobor.moviegenius.Auth;

import com.titoosemobor.moviegenius.Config.JWTService;
import com.titoosemobor.moviegenius.Entity.Role;
import com.titoosemobor.moviegenius.Entity.User;
import com.titoosemobor.moviegenius.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JWTService jwtService;
  private final AuthenticationManager authenticationManager;
  public AuthenticationResponse register(RegisterRequest registerRequest) {
    var user = User.builder()
      .email(registerRequest.getEmail())
      .password(passwordEncoder.encode(registerRequest.getPassword()))
      .role(Role.USER)
      .created_at(new Timestamp(System.currentTimeMillis()))
      .build();
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
      .token(jwtToken)
      .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest registerRequest) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        registerRequest.getEmail(),
        registerRequest.getPassword()
      )
    );
    var user = userRepository.findUserByEmail(registerRequest.getEmail())
      .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
      .token(jwtToken)
      .build();  }
}
