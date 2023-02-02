package com.reactive.kafkapoc1.controllers;

import com.reactive.kafkapoc1.model.Session;
import com.reactive.kafkapoc1.model.Speaker;
import com.reactive.kafkapoc1.repos.SessionsRepository;
import com.reactive.kafkapoc1.repos.SpeakersRepository;
import com.reactive.kafkapoc1.services.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController("/")
public class MainController {

    @Autowired
    private SpeakerService speakerService;

    private SpeakersRepository speakersRepository;

    private SessionsRepository sessionsRepository;

    MainController(SpeakersRepository speakersRepository,SessionsRepository sessionsRepository){
        this.speakersRepository=speakersRepository;
        this.sessionsRepository=sessionsRepository;
    }

    @GetMapping("/all")
    Flux<Speaker> getAllSpeakers(){
        var allSpeakers= speakersRepository.findAll();

        return allSpeakers.flatMap(speaker -> sessionsRepository.findSessionsBySpeakerId(speaker.getId())
                .collectList()
                .map(sessions -> {
                    speaker.setSessions(sessions);
                    return speaker;
                })
        );

}
    @GetMapping("/speakers/{id}")
    Mono<Speaker> getSpeakerById(@PathVariable int id){
        return speakerService.findSpeakerAndSessionsBySpeakerId(id);
    }


    @GetMapping("/sessions/{id}")
    Mono<Session> getSessionById(@PathVariable int id){
        var res = sessionsRepository.findById(id);
        return res;
    }

    @GetMapping("/sessions")
    Flux<Session> getSessionBySpeakerId(@RequestParam int speakerId){
        var res = sessionsRepository.findSessionsBySpeakerId(speakerId);
        return res;
    }

    @PostMapping("/speakers/create")
    Mono<Speaker> createSpeaker(@RequestBody Speaker speaker){
       return speakersRepository.save(speaker);
    }

    @PostMapping("sessions/create")
    Mono<Speaker> createSession(@RequestBody Session session){
        return sessionsRepository.save(session)
                .flatMap(session1 -> speakerService.findSpeakerAndSessionsBySpeakerId(session1.getSpeakerId()));
    }
}
