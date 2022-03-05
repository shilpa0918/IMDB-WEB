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
public class ActorMovieResponse {
    private String actorName;
    private String actorEmail;
    private Integer actorAge;
    private List<MovieDTO> movies;
}
