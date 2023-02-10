package com.reactive.kafkapoc1.services;

import com.reactive.kafkapoc1.model.Room;
import com.reactive.kafkapoc1.model.Session;
import com.reactive.kafkapoc1.repos.SessionsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
public class SessionService {


    private SessionsRepository sessionsRepository;

    public SessionService(SessionsRepository sessionsRepository){
        this.sessionsRepository = sessionsRepository;
    }

    public Flux<Session> fetchSessionsByIdAndFormat(int id){
        return sessionsRepository.findSessionsBySpeakerId(id)
                .map(session -> {
                    session.setRoom(new Room(session.getRoomName(),0));
                    return session;
                });
    }
}
