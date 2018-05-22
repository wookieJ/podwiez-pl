package pl.podwiez.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.podwiez.model.Account;
import pl.podwiez.repositories.AccountRepository;

@Component
public class SecUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = userRepository.findFirstByEmail(email);
        System.out.println(account);
        if (account == null) {
            throw new UsernameNotFoundException(email);
        } else {
            UserDetails details = new SecUserDetails(account);
            System.out.println(details.getPassword());
            System.out.println(details.getPassword().length());
            return details;
        }
    }
}