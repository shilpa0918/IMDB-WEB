package com.web.imdb.service;

import com.web.imdb.exception.MovieAlreadyExistException;
import com.web.imdb.exception.MovieNotExistException;
import com.web.imdb.entity.Actor;
import com.web.imdb.entity.Movie;
import com.web.imdb.entity.Review;
import com.web.imdb.repo.ActorRepo;
import com.web.imdb.repo.MovieRepo;
import com.web.imdb.repo.ReviewRepo;
import com.web.imdb.request.MovieRequest;
import com.web.imdb.response.ActorResponse;
import com.web.imdb.response.MovieResponse;
import com.web.imdb.response.MovieReviewResponse;
import com.web.imdb.response.ReviewResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieService {

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    ActorRepo actorRepo;

    @Autowired
    ReviewRepo reviewRepo;

    @Transactional
    public MovieResponse addMovie(MovieRequest movieRequest) {
        if (!movieRepo.findByMovieName(movieRequest.getMovieName()).isEmpty()) {
            throw new MovieAlreadyExistException();
        }
        Movie movie = Movie.builder()
                .movieName(movieRequest.getMovieName())
                .movieDirectorName(movieRequest.getMovieDirectorName())
                .producerName(movieRequest.getProducerName())
                .movieType(movieRequest.getMovieType())
                .movieReleaseYear(movieRequest.getMovieReleaseYear())
                .build();
        movie.addActors(movieRequest.getActors());
        movieRequest.getActors().stream().forEach(actor -> actor.addMovies(movie));
        Movie savedMovie = movieRepo.saveAndFlush(movie);
        return convertToDto(savedMovie);
    }

    private MovieResponse convertToDto(Movie movie) {
        return MovieResponse.builder()
                .actors(movie.getActors().stream().map(actor -> {
                    return ActorResponse.builder().
                            actorName(actor.getActorName())
                            .actorEmail(actor.getActorEmail())
                            .actorAge(actor.getActorAge())
                            .build();
                }).collect(Collectors.toList()))
                .movieDirectorName(movie.getMovieDirectorName())
                .movieName(movie.getMovieName())
                .movieReleaseYear(movie.getMovieReleaseYear())
                .movieType(movie.getMovieType())
                .producerName(movie.getProducerName())
                .build();
    }

    private MovieReviewResponse convertToMovieReviewDto(Movie movie) {
        return MovieReviewResponse.builder()
                .actors(movie.getActors().stream().map(actor -> {
                    return ActorResponse.builder().
                            actorName(actor.getActorName())
                            .actorEmail(actor.getActorEmail())
                            .actorAge(actor.getActorAge())
                            .build();
                }).collect(Collectors.toList()))
                .movieDirectorName(movie.getMovieDirectorName())
                .movieName(movie.getMovieName())
                .movieReleaseYear(movie.getMovieReleaseYear())
                .movieType(movie.getMovieType())
                .producerName(movie.getProducerName())
                .reviewCount(movie.getReviews().size())
                .reviews(movie.getReviews() != null ? movie.getReviews().stream().map(review -> {
                    return ReviewResponse.builder()
                            .comments(review.getComments())
                            .ratings(review.getRatings())
                            .reviewer(review.getReviewer()).build();
                }).collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }

    private MovieReviewResponse convertMovieToMovieReviewDto(Movie movie) {
        Set<Review> existingReviews = reviewRepo.findExistingReviewForMovie(movie.getMovieName());
        movie.addReviews(existingReviews);
        return MovieReviewResponse.builder()
                .actors(movie.getActors().stream().map(actor -> {
                    return ActorResponse.builder().
                            actorName(actor.getActorName())
                            .actorEmail(actor.getActorEmail())
                            .actorAge(actor.getActorAge())
                            .build();
                }).collect(Collectors.toList()))
                .reviewCount(existingReviews.size())
                .movieDirectorName(movie.getMovieDirectorName())
                .movieName(movie.getMovieName())
                .movieReleaseYear(movie.getMovieReleaseYear())
                .movieType(movie.getMovieType())
                .producerName(movie.getProducerName())
                .reviews(movie.getReviews() != null ? movie.getReviews().stream().map(review -> {
                    return ReviewResponse.builder()
                            .comments(review.getComments())
                            .ratings(review.getRatings())
                            .reviewer(review.getReviewer()).build();
                }).collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }

    public Set<MovieReviewResponse> findAllMoviews() {
        return movieRepo.findAll().stream().map(movie -> convertMovieToMovieReviewDto(movie)).collect(Collectors.toSet());
    }

    public List<Movie> searchMovies(String movieName) {
        return movieRepo.searchMovies(movieName);
    }

    public void deleteMovie(String movieName) {
        if (movieRepo.findByMovieName(movieName).isEmpty()) {
            throw new MovieNotExistException();
        }
        Movie movie = movieRepo.findByMovieName(movieName).get();
        movieRepo.delete(movie);
    }

    public List<String> findMovieNamesByName(String searchTerm) {
        List<String> movies = movieRepo.getMovieNamesBySearchTerm(searchTerm);
        return movies;
    }

    public List<Movie> findMoviesByName(String searchTerm) {
        List<Movie> movies = movieRepo.searchMovies(searchTerm);
        return movies;
    }

    public MovieReviewResponse updateMovieInfo(String movieName, MovieRequest movieRequest) {
        if (movieRepo.findByMovieName(movieName).isEmpty()) {
            throw new MovieNotExistException();
        }
        Movie movie = movieRepo.findByMovieName(movieName).get();
        movie.setMovieName((movieRequest.getMovieName()));
        movie.setMovieType(movieRequest.getMovieType());
        movie.setMovieDirectorName(movieRequest.getMovieDirectorName());
        movie.setMovieReleaseYear(movieRequest.getMovieReleaseYear());
        movie.setProducerName(movieRequest.getProducerName());
        movie.setReviews(movieRequest.getReviews());

        movieRequest.getActors().stream().forEach(actorInRequest -> {
            Actor existingActor = actorRepo.findByActorEmail(actorInRequest.getActorEmail()).isPresent() ?
                    actorRepo.findByActorEmail(actorInRequest.getActorEmail()).get() :
                    new Actor();
            existingActor.setActorName(actorInRequest.getActorName());
            existingActor.setActorEmail(actorInRequest.getActorEmail());
            existingActor.setActorAge(actorInRequest.getActorAge());
            movie.addActors(actorRepo.saveAndFlush(existingActor));
        });
        return convertToMovieReviewDto(movieRepo.saveAndFlush(movie));
    }
}