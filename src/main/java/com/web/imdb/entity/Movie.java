package com.web.imdb.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "movie")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "movieId")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(unique = true)
    private String movieName;

    private Integer movieReleaseYear;
    private String movieDirectorName;
    private String producerName;
    private String movieType;

    @Transient
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "movie")
    @Fetch(value = FetchMode.SELECT)
    @JsonBackReference
    private Set<Review> reviews;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_actor", joinColumns = {@JoinColumn(name = "movie_id", referencedColumnName = "movieId")}
            , inverseJoinColumns = {@JoinColumn(name = "actor_id", referencedColumnName = "actorId")})
    @Fetch(value = FetchMode.SELECT)
    private Set<Actor> actors;

    public void addReviews(Set<Review> reviewsList) {
        if (this.reviews == null) reviews = new HashSet<>();
        this.reviews.addAll(reviewsList);
    }

    public void addActors(Set<Actor> actorList) {
        if (this.actors == null) actors = new HashSet<>();
        this.actors.addAll(actorList);
    }

    public void addActors(Actor actor) {
        if (this.actors == null) actors = new HashSet<>();
        this.actors.add(actor);
    }
}
