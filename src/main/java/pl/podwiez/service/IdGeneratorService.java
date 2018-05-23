package pl.podwiez.service;

import pl.podwiez.exception.IdGeneratorExistsException;

public interface IdGeneratorService {
    long generateRideId() throws IdGeneratorExistsException;
    void implementIdGenerator();
}
