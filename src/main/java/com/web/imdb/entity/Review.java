package com.web.imdb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;
    private String reviewer;
    private String ratings;
    private String comments;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Movie movie;

}
