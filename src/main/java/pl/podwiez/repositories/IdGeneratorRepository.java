package pl.podwiez.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.podwiez.model.IdGenerator;

/**
 * IdGenerator repository pattern for CRUD operation on MongoDB
 */
public interface IdGeneratorRepository extends MongoRepository<IdGenerator, Long> {
}
