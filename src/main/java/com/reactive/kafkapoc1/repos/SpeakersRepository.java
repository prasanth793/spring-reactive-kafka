package com.reactive.kafkapoc1.repos;

import com.reactive.kafkapoc1.model.Speaker;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SpeakersRepository extends ReactiveCrudRepository<Speaker,Integer> {
}
