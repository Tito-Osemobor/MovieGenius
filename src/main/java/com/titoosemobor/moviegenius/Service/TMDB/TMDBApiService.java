package com.titoosemobor.moviegenius.Service.TMDB;

import com.titoosemobor.moviegenius.DTO.MovieDTO;
import com.titoosemobor.moviegenius.DTO.MovieDTOMapper;
import com.titoosemobor.moviegenius.Entity.*;
import com.titoosemobor.moviegenius.Exception.MovieException;
import com.titoosemobor.moviegenius.Repository.GenreRepository;
import com.titoosemobor.moviegenius.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TMDBApiService {
  @Autowired
  private TMDBApiClient tmdbApiClient;
  @Autowired
  private MovieRepository movieRepository;
  @Autowired
  private GenreRepository genreRepository;

  public Optional<MovieDTO> fetchMovieById(Long movieId) {
    Optional<Movie> movieOptional = Optional.ofNullable(tmdbApiClient.getWebClient()
      .get()
        .uri(UriComponentsBuilder
          .fromUriString("/movie/{id}")
          .queryParam("append_to_response","videos")
          .buildAndExpand(movieId)
          .toUriString())
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

    movieRepository.save(movie);
    return Optional.of(MovieDTOMapper.INSTANCE.apply(movie));
  }

  public Optional<Set<MovieDTO>> fetchMoviesByGenre(Long genreId) {
    Discover discover = tmdbApiClient.getWebClient()
      .get()
      .uri(UriComponentsBuilder
        .fromUriString("/discover/movie")
        .queryParam("page", 1)
        .queryParam("include_video", true)
        .queryParam("with_genres", genreId)
        .buildAndExpand(genreId)
        .toUriString())
      .retrieve()
      .bodyToMono(Discover.class)
      .block();

    Set<Long> allGenreIds = discover.getResults().stream()
      .flatMap(movieResponse -> movieResponse.getGenre_ids().stream())
      .collect(Collectors.toSet());
    Map<Long, Genre> genreMap = genreRepository.findAllByIdIn(allGenreIds).stream()
      .collect(Collectors.toMap(Genre::getId, Function.identity()));
    Set<Movie> movies = discover.getResults().stream()
      .map(movieResponse -> {
        Set<Genre> genres = movieResponse.getGenre_ids().stream()
          .map(genreMap::get)
          .collect(Collectors.toSet());
        String trailer = fetchMovieTrailer(movieResponse.getId());
        if (movieResponse.getBackdropPath() == null || movieResponse.getPosterPath() == null ||
          movieResponse.getOverview() == null || trailer.isEmpty()) {
          return null;
        }
        Movie movie =  Movie.builder()
          .id(movieResponse.getId())
          .title(movieResponse.getTitle())
          .genres(genres)
          .backdropPath(movieResponse.getBackdropPath())
          .posterPath(movieResponse.getPosterPath())
          .overview(movieResponse.getOverview())
          .trailer(trailer)
          .build();
        movieRepository.save(movie);
        return movie;
      })
      .filter(Objects::nonNull)
      .collect(Collectors.toSet());
    movieRepository.saveAll(movies);
    return Optional.of(movies.stream().map(MovieDTOMapper.INSTANCE).collect(Collectors.toSet()));
  }

  public String fetchMovieTrailer(Long movieId) {
    Video video = tmdbApiClient.getWebClient()
      .get()
      .uri("/movie/{movie_id}/videos", movieId)
      .retrieve()
      .bodyToMono(Video.class)
      .block();
    if (!video.getResults().isEmpty()) {
      return video.getResults().stream()
        .filter(Result::isOfficial)
        .findFirst()
        .orElse(video.getResults().get(0)).getKey();
    }
    return "";
  }

  public int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }
}
