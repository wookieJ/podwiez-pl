package pl.podwiez.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.podwiez.model.Account;
import pl.podwiez.repositories.AccountRepository;
import pl.podwiez.service.IdGeneratorService;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
public class AccountEndpoint {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IdGeneratorService idGeneratorService;

    @PostMapping()
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        long newAccountIdValue = idGeneratorService.generateAccountId();
        account.setId(newAccountIdValue);
        accountRepository.save(account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAccountIdValue).toUri();
        return ResponseEntity.created(location).body(account);
    }
}
