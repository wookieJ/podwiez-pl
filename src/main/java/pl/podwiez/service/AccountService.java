package pl.podwiez.service;

import pl.podwiez.exception.EmailExistsException;
import pl.podwiez.model.Account;

public interface AccountService {
    void save (Account account) throws EmailExistsException;
}
