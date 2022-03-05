package com.web.imdb.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDTO {
    private String movieName;
    private Integer movieReleaseYear;
    private String movieDirectorName;
    private String producerName;
    private String movieType;
}
