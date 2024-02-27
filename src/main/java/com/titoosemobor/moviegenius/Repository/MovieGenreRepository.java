package com.titoosemobor.moviegenius.Repository;

import com.titoosemobor.moviegenius.Entity.Movie;
import com.titoosemobor.moviegenius.Entity.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
  @Query("SELECT mg.movie " +
      "FROM MovieGenre mg " +
      "WHERE mg.genre.id IN :genreIds " +
      "GROUP BY mg.movie HAVING COUNT(DISTINCT mg.genre.id) = :genreCount")
  Set<Movie> findMoviesByAllGenreIds(@Param("genreIds") Set<Long> genreIds, @Param("genreCount") Long genreCount);
}

