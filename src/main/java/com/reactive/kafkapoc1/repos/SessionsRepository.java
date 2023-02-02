package com.reactive.kafkapoc1.repos;

import com.reactive.kafkapoc1.model.Session;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface SessionsRepository extends ReactiveCrudRepository<Session,Integer> {

    @Query("select * from sessions where speaker_id=:speakerId")
    Flux<Session> findSessionsBySpeakerId(int speakerId);

}
