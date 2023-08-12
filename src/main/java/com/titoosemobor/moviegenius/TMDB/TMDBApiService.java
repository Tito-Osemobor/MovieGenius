package com.titoosemobor.moviegenius.TMDB;

import com.titoosemobor.moviegenius.DTO.MovieDTO;
import com.titoosemobor.moviegenius.DTO.MovieDTOMapper;
import com.titoosemobor.moviegenius.Entity.Genre;
import com.titoosemobor.moviegenius.Entity.Movie;
import com.titoosemobor.moviegenius.Exception.MovieException;
import com.titoosemobor.moviegenius.Repository.GenreRepository;
import com.titoosemobor.moviegenius.Repository.MovieRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
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


  public Optional<MovieDTO> fetchMovieById(long movieId) {
    Optional<Movie> movieOptional = Optional.ofNullable(tmdbApiClient.getWebClient()
      .get()
      .uri("/movie/{id}?append_to_response=videos", movieId)
      .retrieve()
      .bodyToMono(Movie.class)
      .block());

    if (movieOptional.isEmpty()) {
      throw new MovieException.MovieNotFoundException("Movie not found");
    }
    Movie movie = movieOptional.get();

    Set<Genre> existingGenres = genreRepository.findAllByIdIn(
      movie.getGenres().stream().map(Genre::getId).collect(Collectors.toSet())
    );

    Set<Genre> newGenres = new HashSet<>(movie.getGenres());
    newGenres.removeAll(existingGenres);
    existingGenres.addAll(genreRepository.saveAll(newGenres));

    movie.setTrailer();
    movie.setGenres(existingGenres);

    movieRespository.save(movie);
    Optional<MovieDTO> movieDTO = Optional.of(MovieDTOMapper.INSTANCE.apply(movie));
    return movieDTO;
  }
}
