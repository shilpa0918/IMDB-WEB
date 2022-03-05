package com.web.imdb.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActorResponse {
    private String actorName;
    private String actorEmail;
    private Integer actorAge;
}
