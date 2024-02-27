package com.titoosemobor.moviegenius.Service;

import com.titoosemobor.moviegenius.DTO.MovieDTO;
import com.titoosemobor.moviegenius.DTO.MovieDTOMapper;
import com.titoosemobor.moviegenius.Entity.Genre;
import com.titoosemobor.moviegenius.Entity.Movie;
import com.titoosemobor.moviegenius.Repository.GenreRepository;
import com.titoosemobor.moviegenius.Repository.MovieGenreRepository;
import com.titoosemobor.moviegenius.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {
  @Autowired
  private MovieRepository movieRepository;
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

  public MovieDTO fetchMovieById(Long movieId) {
    return MovieDTOMapper.INSTANCE.apply(movieRepository.findMovieById(movieId));
  }

  public Set<String> fetchRandomPosters() {
    Set<String> posters = movieRepository.findRandomPosters()
      .stream()
      .map(poster -> "https://image.tmdb.org/t/p/original" + poster)
      .collect(Collectors.toSet());
    return posters;
  }
}
