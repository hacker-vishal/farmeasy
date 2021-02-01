package project.farmease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.farmease.dao.UserRepo;
import project.farmease.pojo.User;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
	@Autowired
	private UserRepo userRepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepo.findById(username);      // retrieve the user by username
           // log.info("Checking whether user is present or not ---- userOptional {}:",userOptional);
        User user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + username));                // if user not found

        return new org.springframework.security
                .core.userdetails.User(user.getEmail(), user.getPassword(),
                user.isEnabled(), true, true,
                 true, 
                 getAuthorities("USER"));              // this is provided by spring framework , which implements the userDetails interface and here we are mapping the user details to the spring core.userdetails.User class 
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));   // we provide authority to the user
    }
}
