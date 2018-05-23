package pl.podwiez.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.podwiez.exception.IdGeneratorExistsException;
import pl.podwiez.model.Ride;
import pl.podwiez.repositories.RideRepository;
import pl.podwiez.service.IdGeneratorService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideEndpoints {
    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private IdGeneratorService idGeneratorService;

    /**
     * Getting all rides in service
     *
     * @return all rides in service
     */
    @GetMapping()
    public ResponseEntity<List<Ride>> getAllRides() {
        List<Ride> allRides = rideRepository.findAll();
        if (!allRides.isEmpty()) {
            return ResponseEntity.ok(allRides);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Adding new ride to the service
     *
     * @param ride ride we want to add
     * @return ride we added to service
     */
    @PostMapping()
    public ResponseEntity<Ride> addRide(@RequestBody Ride ride) {
        long newIdValue = 0;
        try {
            newIdValue = idGeneratorService.generateRideId();
        } catch (IdGeneratorExistsException e) {
            e.printStackTrace();
            idGeneratorService.implementIdGenerator();
        }
        ride.setId(newIdValue);
        rideRepository.save(ride);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newIdValue).toUri();
        return ResponseEntity.created(location).body(ride);
    }
}
