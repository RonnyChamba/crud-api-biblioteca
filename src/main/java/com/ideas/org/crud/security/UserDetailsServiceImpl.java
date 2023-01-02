package com.ideas.org.crud.security;

import com.ideas.org.crud.entities.User;
import com.ideas.org.crud.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private  final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(email);

        if (userOptional.isEmpty()){
            throw  new UsernameNotFoundException(String.format("User %s not found, try again", email));
        }

        return  UserMain.build(userOptional.orElseThrow());
    }
}
