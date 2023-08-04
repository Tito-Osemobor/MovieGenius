package com.titoosemobor.moviegenius.Repository;

import com.titoosemobor.moviegenius.Entity.Profile;
import com.titoosemobor.moviegenius.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
  List<Profile> findProfilesByUser(User user);
  Profile findProfileById(Long id);
}
