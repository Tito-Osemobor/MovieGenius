package com.titoosemobor.moviegenius.Controller;

import com.titoosemobor.moviegenius.Entity.Movie;
import com.titoosemobor.moviegenius.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/moviegenius/movies")
public class MovieController {

  @Autowired
  MovieService movieService;


}
