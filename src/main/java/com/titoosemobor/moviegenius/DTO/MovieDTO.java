package com.titoosemobor.moviegenius.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class MovieDTO {
  private String title;
  private Set<String> genres;
  private String backdropPath;
  private String posterPath;
  private String overview;
  private String trailer;
}
