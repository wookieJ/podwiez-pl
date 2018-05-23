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

    /**
     * Generating new ride's id value
     *
     * @return new unique ride's id value
     * @throws IdGeneratorExistsException throwing when there is no service's id generator in database
     */
    @Override
    public long generateRideId() throws IdGeneratorExistsException {
        List<IdGenerator> idGeneratorList = idGeneratorRepository.findAll();
        if (idGeneratorList.isEmpty()) {
            throw new IdGeneratorExistsException("There is no id generator in database!");
        }
        IdGenerator idGenerator = idGeneratorList.get(0);
        idGeneratorRepository.deleteAll();
        long newIdValue = idGenerator.getRideId() + 1;
        idGenerator.setRideId(newIdValue);
        idGeneratorRepository.save(idGenerator);

        return newIdValue;
    }

    /**
     * Generating new id generator collection in database
     */
    @Override
    public void implementIdGenerator() {
        idGeneratorRepository.save(new IdGenerator(0));
    }
}
