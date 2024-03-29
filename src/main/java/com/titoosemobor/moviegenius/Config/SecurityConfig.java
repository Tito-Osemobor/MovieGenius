package com.titoosemobor.moviegenius.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JWTAuthenticationFilter jwtAthFiler;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests((authorizeHttpRequests) ->
        authorizeHttpRequests
          .requestMatchers("/api/v1/auth/**").permitAll()
          .requestMatchers("/moviegenius/movies/posters").permitAll()
          .anyRequest()
          .authenticated())
      .sessionManagement((sessionManagement) ->
        sessionManagement
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authenticationProvider(authenticationProvider)
      .addFilterBefore(jwtAthFiler, UsernamePasswordAuthenticationFilter.class);
    return httpSecurity.build();
  }
}
