package pl.podwiez.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.podwiez.exception.CannotParticipateTwiceOnRide;
import pl.podwiez.exception.CannotParticipateYourOwnRide;
import pl.podwiez.exception.NoAvailablePlacesInRide;
import pl.podwiez.model.Account;
import pl.podwiez.model.Ride;
import pl.podwiez.repositories.AccountRepository;
import pl.podwiez.repositories.RideRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rides/{id}")
public class MembersEndpoint {
    @Autowired
    RideRepository rideRepository;

    @Autowired
    AccountRepository accountRepository;

    /**
     * Getting member of ride
     *
     * @param id ride's id
     * @return ride's members
     */
    @GetMapping(value = "/members")
    public ResponseEntity<List<Account>> getAllMembers(@PathVariable(value = "id") Long id) {
        Ride ride = rideRepository.findFirstById(id);
        List<Account> members;
        if (ride != null) {
            members = ride.getMembers();
            return ResponseEntity.ok(members);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Adding itself as ride's member
     *
     * @param id                 id of ride
     * @param cookie             jSession cookie
     * @param httpServletRequest needed to check connection
     * @return list of ride's members
     */
    @PostMapping(value = "/members")
    public ResponseEntity<List<Account>> addMember(@PathVariable(value = "id") Long id, @RequestHeader("Cookie") String cookie, HttpServletRequest httpServletRequest) {
        String sessionId = "JSESSIONID=" + httpServletRequest.getRequestedSessionId();
        if (sessionId.equals(cookie)) {
            String email = httpServletRequest.getUserPrincipal().getName();
            Account account = accountRepository.findFirstByEmail(email);
            Ride ride = rideRepository.findFirstById(id);
            try {
                ride.addToMembers(account);
            } catch (NoAvailablePlacesInRide noAvailablePlacesInRide) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } catch (CannotParticipateYourOwnRide cannotParticipateYourOwnRide) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } catch (CannotParticipateTwiceOnRide cannotParticipateTwiceOnRide) {
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
            }
            rideRepository.save(ride);
            List<Account> members = ride.getMembers();
            return ResponseEntity.ok(members);
        } else
            return ResponseEntity.status(401).build();
    }
}
