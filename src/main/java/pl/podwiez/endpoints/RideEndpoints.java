package pl.podwiez.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.podwiez.model.Ride;
import pl.podwiez.repositories.RideRepository;

@RestController
@RequestMapping("/rides")
public class RideEndpoints {
    @Autowired
    private RideRepository rideRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Ride getCity(@RequestBody Ride ride) {
        rideRepository.save(ride);
        return ride;
    }
}
