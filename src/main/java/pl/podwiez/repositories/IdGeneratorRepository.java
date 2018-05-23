package pl.podwiez.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.podwiez.model.IdGenerator;

public interface IdGeneratorRepository extends MongoRepository<IdGenerator, Long> {
}
