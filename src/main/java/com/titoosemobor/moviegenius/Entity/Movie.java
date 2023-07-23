package com.titoosemobor.moviegenius.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {
  @Id
  @GeneratedValue
  private Long movie_id;

  public void setMovie_id(Long movieId) {
    this.movie_id = movieId;
  }

  public Long getMovie_id() {
    return movie_id;
  }
}
