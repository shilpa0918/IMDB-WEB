package com.web.imdb.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorRequest {
    private String actorName;
    private String actorEmail;
    private Integer actorAge;
}
