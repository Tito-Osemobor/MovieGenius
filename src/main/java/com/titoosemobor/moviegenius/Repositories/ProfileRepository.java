package com.titoosemobor.moviegenius.Repositories;

import com.titoosemobor.moviegenius.Entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
