package com.titoosemobor.moviegenius.Repository;

import com.titoosemobor.moviegenius.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
  Movie findMovieById(Long id);

  @Query(value = "SELECT poster_path FROM movies LIMIT 28", nativeQuery = true)
  Set<String> findRandomPosters();
}
