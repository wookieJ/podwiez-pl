package pl.podwiez.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.podwiez.exception.EmailExistsException;
import pl.podwiez.model.Account;
import pl.podwiez.repositories.AccountRepository;

@Component
public class SecAccountDetailsService implements UserDetailsService, AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findFirstByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException(email);
        } else {
            UserDetails details = new SecAccountDetails(account);
            return details;
        }
    }

    @Override
    public void save(Account account) throws EmailExistsException {
        if(loadUserByUsername(account.getEmail()) != null)
            throw new EmailExistsException("Account with this email exists in service");
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }
}