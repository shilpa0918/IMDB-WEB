package com.web.imdb.controller;

import com.web.imdb.entity.Actor;
import com.web.imdb.request.ActorRequest;
import com.web.imdb.request.UpdateActorRequest;
import com.web.imdb.response.ActorMovieResponse;
import com.web.imdb.service.ActorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class ActorController {

    @Autowired
    ActorService actorService;

    @ApiOperation(value = "Add a new Actor")
    @PostMapping("/addActor")
    public ResponseEntity<Actor> addActor(@RequestBody ActorRequest actorRequest) {
        Actor actor = actorService.addActor(actorRequest);
        return new ResponseEntity(actor, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Actor Details")
    @PutMapping("/updateActor/{actorName}")
    public ResponseEntity<Actor> updateActor(@PathVariable("actorName") String actorName, @RequestBody UpdateActorRequest actorRequest) {
        Actor actor = actorService.updateActor(actorName, actorRequest);
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an Actor")
    @DeleteMapping("/deleteAnActor/{actorName}")
    public ResponseEntity deleteActor(@PathVariable String actorName) {
        actorService.deleteExactActor(actorName);
        return new ResponseEntity("Deleted the actor successfully:", HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get Actor Names By SearchTerm")
    @GetMapping("/actorNamesBySearchTerm/{searchTerm}")
    public ResponseEntity findActorNamesBySearchTerm(@PathVariable String searchTerm) {
        List<String> actorNames = actorService.findActorsNamesBySearchTerm(searchTerm);
        return new ResponseEntity(actorNames, HttpStatus.OK);
    }

    @ApiOperation(value = "Show Actor along with Movies he/she is part of ")
    @GetMapping("/actorName/{searchTerm}")
    public ResponseEntity showCompleteActorInfo(@PathVariable String searchTerm) {
        List<ActorMovieResponse> actorNames = actorService.showCompleteActorInfo(searchTerm);
        return new ResponseEntity(actorNames, HttpStatus.OK);
    }
}
