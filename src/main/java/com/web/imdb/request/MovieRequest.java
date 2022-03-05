package com.web.imdb.request;

import com.web.imdb.entity.Actor;
import com.web.imdb.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {

    private String movieName;
    private Integer movieReleaseYear;
    private String movieDirectorName;
    private String producerName;
    private String movieType;
    private Set<Review> reviews;
    private Set<Actor> actors;
}
