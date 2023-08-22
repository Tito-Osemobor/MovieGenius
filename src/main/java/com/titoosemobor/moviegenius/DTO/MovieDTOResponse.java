package com.titoosemobor.moviegenius.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieDTOResponse {
  private Long id;

  private String title;

  private Set<Long> genre_ids;

  @JsonProperty("backdrop_path")
  private String backdropPath;

  @JsonProperty("poster_path")
  private String posterPath;

  private String overview;
}
