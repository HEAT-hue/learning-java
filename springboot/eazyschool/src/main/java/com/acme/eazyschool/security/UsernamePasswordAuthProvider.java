package com.acme.eazyschool.security;

import com.acme.eazyschool.model.Person;
import com.acme.eazyschool.model.Roles;
import com.acme.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/* Implement custom authentication provider */
@Component
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

    // Retrieve details of user in DB to check with current details;
    @Autowired
    PersonRepository personRepository;

    // Match bcrypt encoded password
    @Autowired
    PasswordEncoder passwordEncoder;

    /*
     * Retrieve auth object and extract username and password. If successful, return same auth object
     * Spring uses the auth object and configures it with the roles provided by you.3
     * */
    @Override
    public Authentication authenticate(Authentication authentication) {
        // Get credentials of user
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Get existing person object
        Person person = personRepository.findByEmail(email);

        // Check if person exists in the DB, and validate password
        if (null != person && person.getPersonId() > 0 && passwordEncoder.matches(password, person.getPassword())) {
            return new UsernamePasswordAuthenticationToken(email, null, getGrantedAuthorities(person.getRole()));
        }
        throw new BadCredentialsException("Invalid credentials");
    }

    /*
     * Specify the auth providers here. If multiple for spring to determine the exact auth provider used
     * */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


    //  Return the custom roles for authenticated users for spring to use.
    private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        // _ROLE is prefixed to imitate spring's way of defining roles. isn't compulsory tho.
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getRoleName()));
        return grantedAuthorities;
    }
}