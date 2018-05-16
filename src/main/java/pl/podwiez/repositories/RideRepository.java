package pl.podwiez.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.podwiez.model.Ride;

/**
 * Ride repository pattern for CRUD operation on MongoDB
 */
public interface RideRepository extends MongoRepository<Ride, Long> {
}