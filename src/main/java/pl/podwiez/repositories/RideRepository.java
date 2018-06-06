package pl.podwiez.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.podwiez.model.Account;
import pl.podwiez.model.Ride;

import java.util.List;

/**
 * Ride repository pattern for CRUD operation on MongoDB
 */
public interface RideRepository extends MongoRepository<Ride, Long> {
    List<Ride> findAllByAccount(Account account);
    Ride findFirstById(Long id);
}