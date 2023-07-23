package com.titoosemobor.moviegenius.Service;

import com.titoosemobor.moviegenius.Entity.User;
import com.titoosemobor.moviegenius.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public List<User> allUsers() {
    return userRepository.findAll();
  }
}
