package com.web.imdb.repo;

import com.web.imdb.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ActorRepo extends JpaRepository<Actor, Integer> {

    public List<Actor> findByActorName(String searchTerm);

    @Query("select a from Actor a where a.actorName =:actorName")
    public Actor findExactActor(@Param("actorName") String actorName);

    @Query(value = "select * from actor where upper(actor_name) like %:actorName%", nativeQuery = true)
    public List<Actor> findActorsNamesBySearchTerm(@Param("actorName") String actorName);

    @Query("select a from Actor a where upper(a.actorName) =:actorName")
    public Optional<Actor> findActorByActorName(@Param("actorName") String actorName);

    public Optional<Actor> findByActorEmail(String email);

    @Transactional
    @Modifying
    @Query("delete from Actor a where a.actorName =:actorName")
    public void deleteExactActor(@Param("actorName") String actorName);


}
