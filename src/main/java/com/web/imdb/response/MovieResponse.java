package com.web.imdb.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse {
    private String movieName;
    private Integer movieReleaseYear;
    private String movieDirectorName;
    private String producerName;
    private String movieType;
    private List<Object> actors;
}
