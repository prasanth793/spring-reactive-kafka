package com.reactive.kafkapoc1.controllers;

import com.reactive.kafkapoc1.model.Room;
import com.reactive.kafkapoc1.model.Session;
import com.reactive.kafkapoc1.model.Speaker;
import com.reactive.kafkapoc1.repos.SessionsRepository;
import com.reactive.kafkapoc1.repos.SpeakersRepository;
import com.reactive.kafkapoc1.services.SessionService;
import com.reactive.kafkapoc1.services.SpeakerService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController("/")
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {

    private SpeakerService speakerService;
    private SpeakersRepository speakersRepository;
    private SessionsRepository sessionsRepository;
    private SessionService sessionService;

    MainController(SpeakersRepository speakersRepository,SessionsRepository sessionsRepository,SpeakerService speakerService, SessionService sessionService){
        this.speakersRepository=speakersRepository;
        this.sessionsRepository=sessionsRepository;
        this.speakerService=speakerService;
        this.sessionService=sessionService;
    }

    @GetMapping("/all")

    Flux<Speaker> getAllSpeakers(){
        var allSpeakers= speakersRepository.findAll();

        return allSpeakers.flatMap(speaker -> sessionService.fetchSessionsByIdAndFormat(speaker.getId())
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
        speakerService.sendKafkaMessage(speaker);
       return speakersRepository.save(speaker);
    }

    @PostMapping("sessions/create")
    Mono<Speaker> createSession(@RequestBody Session session){
        return sessionsRepository.save(session)
                .flatMap(session1 -> speakerService.findSpeakerAndSessionsBySpeakerId(session1.getSpeakerId()))
                .map(speaker -> {
                    speakerService.sendKafkaMessage(speaker);
                    return speaker;
                });
    }

    @PutMapping("speakers/update")
    Mono<Speaker> updateSpeaker(@RequestBody Speaker speaker){

        speakerService.sendKafkaMessage(speaker);
        return speakersRepository.save(speaker);
    }

    @DeleteMapping("speakers/delete")
    Mono<Void> deleteSpeaker(@RequestParam Integer id){
        return speakerService.findSpeakerAndSessionsBySpeakerId(id)
                .flatMap(speakersRepository::delete);
    }
}
