package com.titoosemobor.moviegenius.Repository;

import com.titoosemobor.moviegenius.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRespository extends JpaRepository<Movie, Long> {
}