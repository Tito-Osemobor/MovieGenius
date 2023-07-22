package com.titoosemobor.moviegenius.Repository;

import com.titoosemobor.moviegenius.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
