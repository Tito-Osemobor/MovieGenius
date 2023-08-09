package com.titoosemobor.moviegenius.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "movies")
public class Movie {
  @Id
  @Column(name = "movie_id")
  private Long id;

  private String imdb_id;

  private String title;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "movies_genres",
    joinColumns = @JoinColumn(name = "movie_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id")
  )
  private Set<Genre> genres = new HashSet<>();

  @JsonProperty("backdrop_path")
  @Column(name = "backdrop_path")
  private String backdropPath;

  @JsonProperty("poster_path")
  @Column(name = "poster_path")
  private String posterPath;

  private String overview;

  @Transient
  @JsonProperty("videos")
  private Video video;

  private String trailer;

  public String getTrailer() {
    Result officialResult = video.getResults().stream()
      .filter(Result::isOfficial)
      .findFirst()
      .orElse(video.getResults().get(0));
    return "https://www.youtube.com/watch?v=" + officialResult.getKey();
  }

  public void setTrailer() {
    this.trailer = getTrailer();
  }
}
