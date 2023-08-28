package com.titoosemobor.moviegenius.Repository;

import com.titoosemobor.moviegenius.Entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
  Set<Genre> findAllByIdIn(Set<Long> ids);
  Set<Genre> findAllByNameIn(Set<String> genreNames);
}
