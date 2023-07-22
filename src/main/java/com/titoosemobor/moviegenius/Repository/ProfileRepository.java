package com.titoosemobor.moviegenius.Repository;

import com.titoosemobor.moviegenius.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
