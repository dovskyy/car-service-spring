package pl.sda.javagda25.spring.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.javagda25.spring.service.model.Account;
import pl.sda.javagda25.spring.service.repository.AccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findByEmail(email);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            List<String> accountRoles = account.getAccountRoles()
                    .stream()
                    .map(accountRole -> accountRole.getName())
                    .collect(Collectors.toList());
            String[] roles = accountRoles.toArray(new String[accountRoles.size()]);
            return User.builder()
                    .username(account.getEmail())
                    .password(account.getPassword())
                    .roles(roles)
                    .disabled(account.isDisabled())
                    .build();
        }
        throw new UsernameNotFoundException("Username not found.");

    }
}
