package com.web.imdb.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "actor")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "actorId")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actorId;
    private String actorName;

    private String actorEmail;
    private Integer actorAge;
    @ManyToMany(mappedBy = "actors", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)
    private List<Movie> movies;

    public void addMovies(Movie movie) {
        if (this.movies == null) movies = new ArrayList<>();
        this.movies.add(movie);
    }

}
