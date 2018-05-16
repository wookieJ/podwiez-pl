package pl.podwiez.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.podwiez.model.Ride;

public interface RideRepository extends MongoRepository<Ride, Long> {
}