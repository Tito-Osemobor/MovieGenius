package com.titoosemobor.moviegenius.Service;

import com.titoosemobor.moviegenius.DTO.MovieDTO;
import com.titoosemobor.moviegenius.DTO.MovieDTOMapper;
import com.titoosemobor.moviegenius.Entity.Movie;
import com.titoosemobor.moviegenius.Repository.MovieRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MovieService {
  @Autowired
  MovieRespository movieRespository;
  @Autowired
  MovieDTOMapper movieDTOMapper;

}
