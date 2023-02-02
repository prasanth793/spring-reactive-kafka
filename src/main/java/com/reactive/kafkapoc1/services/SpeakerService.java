package com.reactive.kafkapoc1.services;

import com.reactive.kafkapoc1.model.Speaker;
import com.reactive.kafkapoc1.repos.SessionsRepository;
import com.reactive.kafkapoc1.repos.SpeakersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SpeakerService {

    @Autowired
    private SpeakersRepository speakersRepository;

    @Autowired
    private SessionsRepository sessionsRepository;

    public Mono<Speaker> findSpeakerAndSessionsBySpeakerId(int id){
        return speakersRepository.findById(id)
                .zipWith(sessionsRepository.findSessionsBySpeakerId(id).collectList())
                .map(objects -> {
                    var updatedSpeaker = objects.getT1();
                    updatedSpeaker.setSessions(objects.getT2());
                    return updatedSpeaker;
                })
                .switchIfEmpty(Mono.empty());
    }
}
