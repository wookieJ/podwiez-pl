package pl.podwiez.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.podwiez.model.Ride;
import pl.podwiez.repositories.RideRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideEndpoints {
    @Autowired
    private RideRepository rideRepository;

    private static long generatedValue = 1;

    /**
     * Getting all rides in service
     *
     * @return all rides in service
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
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
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Ride> getCity(@RequestBody Ride ride) {
        ride.setId(generatedValue++);
        rideRepository.save(ride);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(0).toUri();
        return ResponseEntity.created(location).body(ride);
    }
}
