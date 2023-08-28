package com.titoosemobor.moviegenius.Service;

import com.titoosemobor.moviegenius.DTO.MovieDTO;
import com.titoosemobor.moviegenius.DTO.MovieDTOMapper;
import com.titoosemobor.moviegenius.Entity.Genre;
import com.titoosemobor.moviegenius.Entity.Movie;
import com.titoosemobor.moviegenius.Entity.MovieGenre;
import com.titoosemobor.moviegenius.Repository.GenreRepository;
import com.titoosemobor.moviegenius.Repository.MovieGenreRepository;
import com.titoosemobor.moviegenius.Repository.MovieRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {
  @Autowired
  private MovieRespository movieRespository;
  @Autowired
  private GenreRepository genreRepository;
  @Autowired
  private MovieGenreRepository movieGenreRepository;
  @Autowired
  private MovieDTOMapper movieDTOMapper;

  public Set<MovieDTO> fetchMoviesByGenre(Set<String> genreNames) {
    Set<Long> genreIds = genreRepository.findAllByNameIn(genreNames)
      .stream()
      .map(Genre::getId)
      .collect(Collectors.toSet());
    Set<Movie> movies = movieGenreRepository.findMoviesByAllGenreIds(genreIds, (long) genreIds.size());
    return movies
      .stream()
      .map(movieDTOMapper)
      .collect(Collectors.toSet());
  }
}
