package com.titoosemobor.moviegenius.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.titoosemobor.moviegenius.DTO.MovieDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Discover {
  private long page;
  @JsonProperty("results")
  private Set<MovieDTOResponse> results;
}
