package pl.podwiez.endpoint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.podwiez.model.Ride;
import pl.podwiez.repositories.AccountRepository;
import pl.podwiez.repositories.RideRepository;
import pl.podwiez.service.IdGeneratorService;

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
        if (allRides != null) {
            return ResponseEntity.ok(allRides);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Getting logged user's rides in service
     *
     * @param principal user data
     * @return list of user's rides
     */
    @GetMapping(value = "/myRides")
    public String getUserRides(Principal principal) {
//        String sessionId = "JSESSIONID: " + httpServletRequest.getRequestedSessionId();
//        if (sessionId.equals(cookie)) {
        String email = principal.getName();
        String hello = "Hello " + email;
        List<Ride> userRides = rideRepository.findAllByAccount(accountRepository.findFirstByEmail(email));
//            if (userRides != null)
        return hello;
//        } else
//            return ResponseEntity.status(401).build();
//        return null;
    }

//    /**
//     * Adding new ride to the service
//     *
//     * @param ride ride we want to add
//     * @return ride we added to service
//     */
//    @PostMapping()
//    public ResponseEntity<Ride> addRide(@RequestBody Ride ride, @RequestHeader("Cookie") String cookie, HttpServletRequest httpServletRequest) {
//        String sessionId = "JSESSIONID=" + httpServletRequest.getRequestedSessionId();
//        if (sessionId.equals(cookie)) {
//            String email = httpServletRequest.getUserPrincipal().getName();
//            Account account = accountRepository.findFirstByEmail(email);
//            ride.setAccount(account);
//
//            long newRideIdValue = idGeneratorService.generateRideId();
//            ride.setId(newRideIdValue);
//            rideRepository.save(ride);
//            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newRideIdValue).toUri();
//            return ResponseEntity.created(location).body(ride);
//        }
//
//        return ResponseEntity.status(401).build();
//    }
}
