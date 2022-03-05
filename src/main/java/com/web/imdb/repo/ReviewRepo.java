package com.web.imdb.repo;

import com.web.imdb.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

    @Query(value = "select r from Review r where r.reviewer = :reviewer")
    public Set<Review> searchReviewsForReviewer(@Param("reviewer") String reviewer);

    @Query("select r from Review r where r.reviewer =:reviewer")
    public Review findExactReview(@Param("reviewer") String reviewer);

    @Query(value = "select * from review where reviewer =:reviewer and movie_movie_id in (select movie_id from movie where movie_name in (:movieName))", nativeQuery = true)
    public Review findExistingReviewForMovieByReviewer(@Param("movieName") String movieName, @Param("reviewer") String reviewer);

    @Query(value = "select * from review where movie_movie_id in (select movie_id from movie where movie_name in (:movieName))", nativeQuery = true)
    public Set<Review> findExistingReviewForMovie(@Param("movieName") String movieName);

    @Transactional
    @Modifying
    @Query("delete from Review r where r.reviewId =:reviewId")
    public void deleteByReviewId(@Param("reviewId") Integer reviewId);

}
