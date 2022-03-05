package com.web.imdb.service;

import com.web.imdb.exception.ActorAlreadyExistException;
import com.web.imdb.exception.ActorNotExistException;
import com.web.imdb.entity.Actor;
import com.web.imdb.repo.ActorRepo;
import com.web.imdb.request.ActorRequest;
import com.web.imdb.request.UpdateActorRequest;
import com.web.imdb.response.ActorMovieResponse;
import com.web.imdb.response.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorService {

    @Autowired
    ActorRepo actorRepo;

    public Actor addActor(ActorRequest actorRequest) {
        if (actorRepo.findByActorEmail(actorRequest.getActorEmail()).isPresent()) {
            throw new ActorAlreadyExistException();
        }
        Actor actor = Actor.builder()
                .actorAge(actorRequest.getActorAge())
                .actorName(actorRequest.getActorName())
                .actorEmail(actorRequest.getActorEmail()).build();
        return actorRepo.saveAndFlush(actor);
    }

    @Transactional
    public Actor updateActor(String actorName, UpdateActorRequest actorRequest) {
        if (actorRepo.findActorByActorName(actorRequest.getActorName().toUpperCase()).isEmpty()) {
            throw new ActorNotExistException();
        } else {
            Actor actor = actorRepo.findActorByActorName(actorName.toUpperCase()).get();
            actor.setActorName(actorRequest.getActorName());
            actor.setActorEmail(actorRequest.getActorEmail());
            actor.setActorAge(actorRequest.getActorAge());
            return actorRepo.saveAndFlush(actor);
        }
    }

    public void deleteExactActor(String actorName) {
        if (actorRepo.findByActorName(actorName).isEmpty()) {
            throw new ActorNotExistException();
        } else {
            actorRepo.deleteExactActor(actorName);
        }
    }

    public List<String> findActorsNamesBySearchTerm(String searchTerm) {
        List<Actor> actors = actorRepo.findActorsNamesBySearchTerm(searchTerm.toUpperCase());
        return actors.stream().map(actor -> actor.getActorName()).collect(Collectors.toList());
    }

    public List<ActorMovieResponse> showCompleteActorInfo(String searchTerm) {
        List<Actor> actors = actorRepo.findActorsNamesBySearchTerm(searchTerm.toUpperCase());
        List<ActorMovieResponse> actorsResponse = new ArrayList<ActorMovieResponse>();
        actors.stream().forEach(actor -> {
            List<MovieDTO> movieDTOs = actor.getMovies().stream().map(movie -> {
                return MovieDTO.builder()
                        .movieDirectorName(movie.getMovieDirectorName())
                        .movieName(movie.getMovieName())
                        .movieReleaseYear(movie.getMovieReleaseYear())
                        .movieType(movie.getMovieType())
                        .producerName(movie.getProducerName())
                        .build();
            }).collect(Collectors.toList());

            actorsResponse.add(ActorMovieResponse.builder()
                    .actorAge(actor.getActorAge())
                    .actorEmail(actor.getActorEmail())
                    .actorName(actor.getActorName())
                    .movies(movieDTOs)
                    .build());
        });
        return actorsResponse;
    }
}
