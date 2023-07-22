package com.titoosemobor.moviegenius.Repositories;

import com.titoosemobor.moviegenius.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
