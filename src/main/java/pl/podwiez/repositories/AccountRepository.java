package pl.podwiez.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.podwiez.model.Account;

/**
 * Account repository pattern for CRUD operation on MongoDB
 */
public interface AccountRepository extends MongoRepository<Account, Long> {
    Account findFirstByEmail(String email);
}
