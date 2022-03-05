package com.web.imdb.controller;

import com.web.imdb.entity.Movie;
import com.web.imdb.request.MovieRequest;
import com.web.imdb.response.MovieResponse;
import com.web.imdb.response.MovieReviewResponse;
import com.web.imdb.service.MovieService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1")
public class MovieController {

    @Autowired
    MovieService movieService;

    @ApiOperation(value = "Add a new Movie")
    @PostMapping("/addMovie")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieRequest movieRequest) {
        MovieResponse movie = movieService.addMovie(movieRequest);
        return new ResponseEntity(movie, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get List movie along with reviews and Actors")
    @GetMapping("/findAllMovieWithActorAndReview")
    public ResponseEntity<List<Movie>> findMovieWithActorAndReview() {
        Set<MovieReviewResponse> movies = movieService.findAllMoviews();
        return new ResponseEntity(movies, HttpStatus.OK);
    }

    @ApiOperation(value = "Search movies")
    @GetMapping("/{movieName}")
    public ResponseEntity<List<Movie>> searchMovies(@PathVariable String movieName) {
        List<Movie> movies = movieService.searchMovies(movieName);
        return new ResponseEntity(movies, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Movie Details")
    @PutMapping("/updateMovie/{movieName}")
    public ResponseEntity updateMovie(@PathVariable String movieName, @RequestBody MovieRequest movieRequest) {
        MovieReviewResponse movieReviewResponse = movieService.updateMovieInfo(movieName, movieRequest);
        return new ResponseEntity(movieReviewResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Exact Movie Details")
    @DeleteMapping("/deleteMovie/{movieName}")
    public ResponseEntity deleteExactMovie(@PathVariable String movieName) {
        movieService.deleteMovie(movieName);
        return new ResponseEntity("Deleted the movie successfully:", HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get Movie Names By SearchTerm")
    @GetMapping("/movieNamesBySearchTerm/{searchTerm}")
    public ResponseEntity findMovieNamesBySearchTerm(@PathVariable String searchTerm) {
        List<String> movieNames = movieService.findMovieNamesByName(searchTerm);
        return new ResponseEntity(movieNames, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Movie Details By SearchTerm")
    @GetMapping("/movieName/{searchTerm}")
    public ResponseEntity findMoviesByName(@PathVariable String searchTerm) {
        List<Movie> movieNames = movieService.findMoviesByName(searchTerm);
        return new ResponseEntity(movieNames, HttpStatus.OK);
    }

}
