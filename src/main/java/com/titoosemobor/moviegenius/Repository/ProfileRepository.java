package com.titoosemobor.moviegenius.Repository;

import com.titoosemobor.moviegenius.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
