package com.web.imdb.repo;

import com.web.imdb.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

    @Query(value = "select m from Movie m where m.movieName like %:movieName%")
    public List<Movie> searchMovies(@Param("movieName") String movieName);

    @Query(value = "select m.movieName from Movie m where m.movieName like %:movieName%")
    public List<String> getMovieNamesBySearchTerm(@Param("movieName") String movieName);

    public Optional<Movie> findByMovieName(String searchTerm);

    @Transactional
    @Modifying
    @Query("delete from Movie m where m.movieName =:movieName")
    public void deleteExactMovie(@Param("movieName") String movieName);
}
