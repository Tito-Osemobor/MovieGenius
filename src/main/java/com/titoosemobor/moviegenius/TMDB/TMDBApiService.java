package com.titoosemobor.moviegenius.TMDB;

import com.titoosemobor.moviegenius.Entity.Genre;
import com.titoosemobor.moviegenius.Entity.Movie;
import com.titoosemobor.moviegenius.Entity.Video;
import com.titoosemobor.moviegenius.Repository.GenreRepository;
import com.titoosemobor.moviegenius.Repository.MovieRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TMDBApiService {
  @Autowired
  private TMDBApiClient tmdbApiClient;
  @Autowired
  private MovieRespository movieRespository;
  @Autowired
  private GenreRepository genreRepository;


  public Movie fetchMovieById(long movieId) {
    Movie movie = tmdbApiClient.getWebClient()
      .get()
      .uri("/movie/{id}?append_to_response=videos", movieId)
      .retrieve()
      .bodyToMono(Movie.class)
      .block();

    Set<Genre> existingGenres = genreRepository.findAllByIdIn(
      movie.getGenres().stream().map(Genre::getId).collect(Collectors.toSet())
    );

    Set<Genre> newGenres = new HashSet<>(movie.getGenres());
    newGenres.removeAll(existingGenres);
    existingGenres.addAll(genreRepository.saveAll(newGenres));

    movie.setTrailer();
    movie.setGenres(existingGenres);

    movieRespository.save(movie);
    return movie;
  }

  public Video fetchMovieVideo(long movieId) {
    return tmdbApiClient.getWebClient()
      .get()
      .uri("movie/{id}/videos", movieId)
      .retrieve()
      .bodyToMono(Video.class)
      .block();
  }
}
