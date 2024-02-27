package com.titoosemobor.moviegenius.Entity;


import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Video {
  private List<Result> results;
}
