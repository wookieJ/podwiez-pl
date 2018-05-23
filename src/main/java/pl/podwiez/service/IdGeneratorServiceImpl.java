package pl.podwiez.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.podwiez.exception.IdGeneratorExistsException;
import pl.podwiez.model.IdGenerator;
import pl.podwiez.repositories.IdGeneratorRepository;

import java.util.List;

@Service
public class IdGeneratorServiceImpl implements IdGeneratorService {
    @Autowired
    private IdGeneratorRepository idGeneratorRepository;

    private long updateRideId() throws IdGeneratorExistsException{
        List<IdGenerator> idGeneratorList = idGeneratorRepository.findAll();
        if (idGeneratorList.isEmpty()) {
            throw new IdGeneratorExistsException("There is no id generator in database!");
        }
        IdGenerator idGenerator = idGeneratorList.get(0);
        idGeneratorRepository.deleteAll();
        long newRideIdValue = idGenerator.getRideId() + 1;
        idGenerator.setRideId(newRideIdValue);
        idGeneratorRepository.save(idGenerator);

        return newRideIdValue;
    }

    /**
     * Generating new ride's id value
     *
     * @return new unique ride's id value
     * @throws IdGeneratorExistsException throwing when there is no service's id generator in database
     */
    @Override
    public long generateRideId() {
        long newRideIdValue = 0;
        try {
            newRideIdValue = updateRideId();
        } catch (IdGeneratorExistsException e) {
            implementIdGenerator();
        }

        return newRideIdValue;
    }

    /**
     * Updating account id in id generator
     *
     * @return new unique account id value
     * @throws IdGeneratorExistsException throwing when there is no generator in service database
     */
    private long updateAccountId() throws IdGeneratorExistsException {
        List<IdGenerator> idGeneratorList = idGeneratorRepository.findAll();
        if (idGeneratorList.isEmpty()) {
            throw new IdGeneratorExistsException("There is no id generator in database!");
        }
        IdGenerator idGenerator = idGeneratorList.get(0);
        idGeneratorRepository.deleteAll();
        long newAccountIdValue = idGenerator.getAccountId() + 1;
        idGenerator.setAccountId(newAccountIdValue);
        idGeneratorRepository.save(idGenerator);

        return newAccountIdValue;
    }

    /**
     * Generating new unique account id value
     *
     * @return new unique account id value
     */
    @Override
    public long generateAccountId() {
        long newAccountIdValue = 0;
        try {
            newAccountIdValue = updateAccountId();
        } catch (IdGeneratorExistsException e) {
            implementIdGenerator();
        }

        return newAccountIdValue;
    }

    /**
     * Generating new id generator collection in database
     */
    @Override
    public void implementIdGenerator() {
        idGeneratorRepository.save(new IdGenerator(0));
    }
}
