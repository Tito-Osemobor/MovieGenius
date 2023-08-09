package com.titoosemobor.moviegenius.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "genres")
public class Genre {
  @Id
  @Column(name = "genre_id")
  private Long id;
  private String name;

  @ManyToMany(mappedBy = "genres")
  private Set<Movie> movies = new HashSet<>();
}
