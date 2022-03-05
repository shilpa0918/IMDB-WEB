package com.web.imdb.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private String reviewer;
    private String ratings;
    private String comments;
    private Integer reviewCount;
    private String movie;
}
