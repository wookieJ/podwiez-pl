package pl.podwiez.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.podwiez.exception.CannotParticipateTwiceOnRide;
import pl.podwiez.exception.CannotParticipateYourOwnRide;
import pl.podwiez.exception.NoAvailablePlacesInRide;
import pl.podwiez.model.Account;
import pl.podwiez.model.Ride;
import pl.podwiez.repositories.AccountRepository;
import pl.podwiez.repositories.RideRepository;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rides/{id}")
public class MembersEndpoint {
    @Autowired
    RideRepository rideRepository;

    @Autowired
    AccountRepository accountRepository;

    /**
     * Getting all members of ride
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
// TODO - comments (principal)

    /**
     * Adding itself as ride's member
     *
     * @param id id of ride
     * @return list of ride's members
     */
    @PostMapping(value = "/members")
    public ResponseEntity<Account> addMember(@PathVariable(value = "id") Long id, Principal principal) {
        String email = principal.getName();
        Account account = accountRepository.findFirstByEmail(email);
        Ride ride = rideRepository.findFirstById(id);
        try {
            ride.addToMembers(account);
        } catch (NoAvailablePlacesInRide noAvailablePlacesInRide) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        } catch (CannotParticipateYourOwnRide cannotParticipateYourOwnRide) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        } catch (CannotParticipateTwiceOnRide cannotParticipateTwiceOnRide) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
        }
        rideRepository.save(ride);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(account.getId()).toUri();

        return ResponseEntity.created(location).body(account);
    }

    /**
     * Deleting one member of ride
     *
     * @param id       id of ride
     * @param memberId id of member
     * @return member of ride
     */
    @DeleteMapping(value = "/members/{memberId}")
    public ResponseEntity<List<Account>> deleteMemberById(@PathVariable(value = "id") Long id, @PathVariable(value = "memberId") Long memberId) {
        Ride ride = rideRepository.findFirstById(id);
        List<Account> members;
        if (ride != null) {
            members = ride.getMembers();
            members = members.stream().filter(e -> e.getId() != memberId).collect(Collectors.toList());
            ride.setMembers(members);
            rideRepository.save(ride);
            return ResponseEntity.ok(members);
        }

        return ResponseEntity.notFound().build();
    }
}
