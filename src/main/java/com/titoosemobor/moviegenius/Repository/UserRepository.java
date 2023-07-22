package com.titoosemobor.moviegenius.Repository;

import com.titoosemobor.moviegenius.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
