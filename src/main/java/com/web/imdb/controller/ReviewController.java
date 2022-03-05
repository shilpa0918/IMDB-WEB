package com.web.imdb.controller;

import com.web.imdb.entity.Review;
import com.web.imdb.request.ReviewRequest;
import com.web.imdb.service.ReviewService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @ApiOperation(value = "Add Review")
    @PostMapping("/addReview")
    public ResponseEntity<Review> addReview(@RequestBody ReviewRequest reviewRequest) {
        Review review = reviewService.addReview(reviewRequest);
        return new ResponseEntity(review, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get Reviews Details")
    @GetMapping("/reviews/{reviewer}")
    public ResponseEntity<Set<Review>> searchReviewsForReviewer(@PathVariable("reviewer") String reviewer) {
        Set<Review> reviews = reviewService.searchReviewsForReviewer(reviewer);
        return new ResponseEntity(reviews, HttpStatus.OK);
    }

    @ApiOperation(value = "Get All Reviews Details")
    @GetMapping("/allReviews")
    public ResponseEntity<List<Review>> showReviews() {
        List<Review> reviews = reviewService.showReviews();
        return new ResponseEntity(reviews, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Review Details")
    @DeleteMapping("/deleteReview/{reviewName}/movieName/{movieName}")
    public ResponseEntity deleteExactReview(@PathVariable("reviewName") String reviewName, @PathVariable("movieName") String movieName) {
        reviewService.deleteExistingReviewForMovie(reviewName, movieName);
        return new ResponseEntity("Deleted the Review successfully:", HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Update Review Details")
    @PutMapping("/updateReview/{reviewerName}")
    public ResponseEntity updateMovie(@PathVariable String reviewerName, @RequestBody ReviewRequest reviewRequest) {
        reviewService.updateReview(reviewerName, reviewRequest);
        return new ResponseEntity("Updated the Review successfully:", HttpStatus.OK);
    }
}
