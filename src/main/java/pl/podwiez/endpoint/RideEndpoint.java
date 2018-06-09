package pl.podwiez.endpoint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.podwiez.model.Account;
import pl.podwiez.model.Ride;
import pl.podwiez.repositories.AccountRepository;
import pl.podwiez.repositories.RideRepository;
import pl.podwiez.service.IdGeneratorService;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideEndpoint {
    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private AccountRepository accountRepository;

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
        return ResponseEntity.ok(allRides);
    }

    /**
     * Getting logged user's rides in service
     *
     * @param principal user data
     * @return list of user's rides
     */
    @GetMapping(value = "/myRides")
    public ResponseEntity<List<Ride>> getUserRides(Principal principal) {
        String email = principal.getName();
        List<Ride> userRides = rideRepository.findAllByAccount(accountRepository.findFirstByEmail(email));

        return ResponseEntity.ok(userRides);
    }

    /**
     * Adding new ride to the service
     *
     * @param ride      ride we want to add
     * @param principal user data
     * @return ride we added to service
     */
    @PostMapping()
    public ResponseEntity<Ride> addRide(@RequestBody Ride ride, Principal principal) {
        String email = principal.getName();
        Account account = accountRepository.findFirstByEmail(email);
        ride.setAccount(account);

        long newRideIdValue = idGeneratorService.generateRideId();
        ride.setId(newRideIdValue);
        rideRepository.save(ride);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newRideIdValue).toUri();
        return ResponseEntity.created(location).body(ride);
    }
}
