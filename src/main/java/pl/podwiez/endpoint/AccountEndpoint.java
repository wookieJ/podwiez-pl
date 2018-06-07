package pl.podwiez.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.podwiez.model.Account;
import pl.podwiez.repositories.AccountRepository;
import pl.podwiez.service.IdGeneratorService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/accounts")
public class AccountEndpoint {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IdGeneratorService idGeneratorService;

    @GetMapping(value = "/myAccount")
    public ResponseEntity<Account> getAccount(@RequestHeader("Cookie") String cookie, HttpServletRequest httpServletRequest) {
        String sessionId = "JSESSIONID=" + httpServletRequest.getRequestedSessionId();
        if (sessionId.equals(cookie)) {
            String email = httpServletRequest.getUserPrincipal().getName();
            Account existingAccount = accountRepository.findFirstByEmail(email);
//            if (existingAccount != null)
                return ResponseEntity.ok(existingAccount);
//            else
//                return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.status(401).build();
    }

    @PostMapping()
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        if (accountRepository.findFirstByEmail(account.getEmail()) != null) {
            if (account.getEmail() != null && account.getPassword() != null && !account.getEmail().isEmpty() && !account.getPassword().isEmpty()) {
                long newAccountIdValue = idGeneratorService.generateAccountId();
                account.setId(newAccountIdValue);
                accountRepository.save(account);
                URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAccountIdValue).toUri();
                return ResponseEntity.created(location).body(account);
            } else
                return ResponseEntity.unprocessableEntity().build();
        } else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping(value = "/myAccount")
    public ResponseEntity<Account> editAccount(@RequestBody Account account, @RequestHeader("Cookie") String cookie, HttpServletRequest httpServletRequest) {
        String sessionId = "JSESSIONID=" + httpServletRequest.getRequestedSessionId();
        if (sessionId.equals(cookie)) {
            String email = httpServletRequest.getUserPrincipal().getName();
            Account existingAccount = accountRepository.findFirstByEmail(email);
//            if (existingAccount != null) {
                existingAccount.setFirstName(account.getFirstName());
                existingAccount.setLastName(account.getLastName());
                existingAccount.setEmail(account.getEmail());
                existingAccount.setPassword(account.getPassword());
                accountRepository.save(existingAccount);

                return ResponseEntity.ok(existingAccount);
//            } else
//                return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.status(401).build();
    }
}
