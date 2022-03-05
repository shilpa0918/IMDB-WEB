package com.web.imdb.service;

import com.web.imdb.exception.MovieNotExistException;
import com.web.imdb.entity.Movie;
import com.web.imdb.entity.Review;
import com.web.imdb.repo.MovieRepo;
import com.web.imdb.repo.ReviewRepo;
import com.web.imdb.request.ReviewRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ReviewService {

    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    MovieRepo movieRepo;

    @Transactional
    public Review addReview(ReviewRequest reviewRequest) {
        Movie movie = null;
        if (movieRepo.findByMovieName(reviewRequest.getMovie()).isEmpty()) {
            throw new MovieNotExistException();
        }
        movie = movieRepo.findByMovieName(reviewRequest.getMovie()).get();
        Review review = Review.builder()
                .reviewer(reviewRequest.getReviewer())
                .ratings(reviewRequest.getRatings())
                .comments(reviewRequest.getComments())
                .movie(movie).build();
        return reviewRepo.saveAndFlush(review);
    }

    public Set<Review> searchReviewsForReviewer(String reviewer) {
        return reviewRepo.searchReviewsForReviewer(reviewer);
    }

    public List<Review> showReviews() {
        return reviewRepo.findAll();
    }

    public void deleteExistingReviewForMovie(String reviewName, String movieName) {
        Movie movie = null;
        if (movieRepo.findByMovieName(movieName).isEmpty()) {
            throw new MovieNotExistException();
        }
        movie = movieRepo.findByMovieName(movieName).get();
        Review review = reviewRepo.findExistingReviewForMovieByReviewer(movie.getMovieName(), reviewName);
        reviewRepo.deleteByReviewId(review.getReviewId());
    }

    public Review updateReview(String reviewerName, ReviewRequest reviewRequest) {
        Movie movie = null;
        if (movieRepo.findByMovieName(reviewRequest.getMovie()).isEmpty()) {
            throw new MovieNotExistException();
        }
        movie = movieRepo.findByMovieName(reviewRequest.getMovie()).get();
        Review review = reviewRepo.findExistingReviewForMovieByReviewer(movie.getMovieName(), reviewerName);
        review.setRatings(reviewRequest.getRatings());
        review.setComments(reviewRequest.getComments());
        return reviewRepo.save(review);
    }
}
